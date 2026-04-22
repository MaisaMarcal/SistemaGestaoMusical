package com.tecdes.music.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.music.dto.AlbumDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private static final List<AlbumDTO> banco = new ArrayList<>();
    private Long id = 1L;

    private final ArtistaService artistaService;
    private final GravadoraService gravadoraService;

    // 🔹 CREATE
    public AlbumDTO criarAlbum(AlbumDTO albumDTO) {

        validarArtistaEGravadora(albumDTO);

        AlbumDTO novoAlbum = new AlbumDTO(
                id,
                albumDTO.titulo(),
                albumDTO.anoLancamento(),
                albumDTO.quantidadeFaixas(),
                albumDTO.artistaId(),
                albumDTO.gravadoraId()
        );

        banco.add(novoAlbum);
        id++;

        return novoAlbum;
    }


    public List<AlbumDTO> listarAlbuns() {
        return new ArrayList<>(banco);
    }

    public AlbumDTO buscarPorId(Long id) {
        return banco.stream()
                .filter(a -> a.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));
    }

    public void deletar(Long id) {
        buscarPorId(id); // valida
        banco.removeIf(a -> a.id().equals(id));
    }

    public AlbumDTO atualizar(Long id, AlbumDTO dto) {

        buscarPorId(id); // valida existência
        validarArtistaEGravadora(dto);

        AlbumDTO atualizado = new AlbumDTO(
                id,
                dto.titulo(),
                dto.anoLancamento(),
                dto.quantidadeFaixas(),
                dto.artistaId(),
                dto.gravadoraId()
        );

        deletar(id);
        banco.add(atualizado);

        return atualizado;
    }

    public AlbumDTO atualizarParcial(Long id, AlbumDTO dto) {

        AlbumDTO existente = buscarPorId(id);

        Long artistaId = dto.artistaId() != null ? dto.artistaId() : existente.artistaId();
        Long gravadoraId = dto.gravadoraId() != null ? dto.gravadoraId() : existente.gravadoraId();

        validarArtistaEGravadora(new AlbumDTO(
                id,
                existente.titulo(),
                existente.anoLancamento(),
                existente.quantidadeFaixas(),
                artistaId,
                gravadoraId
        ));

        AlbumDTO atualizado = new AlbumDTO(
                id,
                dto.titulo() != null ? dto.titulo() : existente.titulo(),
                dto.anoLancamento() != null ? dto.anoLancamento() : existente.anoLancamento(),
                dto.quantidadeFaixas() != null ? dto.quantidadeFaixas() : existente.quantidadeFaixas(),
                artistaId,
                gravadoraId
        );

        deletar(id);
        banco.add(atualizado);

        return atualizado;
    }

    private void validarArtistaEGravadora(AlbumDTO dto) {

        artistaService.listarTodosEmMemoria()
                .stream()
                .filter(a -> a.Id().equals(dto.artistaId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        gravadoraService.listarTodas()
                .stream()
                .filter(g -> g.id().equals(dto.gravadoraId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Gravadora não encontrada"));
    }
}