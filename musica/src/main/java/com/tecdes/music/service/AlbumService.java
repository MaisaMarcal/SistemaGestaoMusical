package com.tecdes.music.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.music.dto.AlbumDTO;
import com.tecdes.music.dto.ArtistaDTO;
import com.tecdes.music.dto.GravadoraDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private static final List<AlbumDTO> banco = new ArrayList<>();
    private Long id = 1L;

    private final ArtistaService artistaService;
    private final GravadoraService gravadoraService;

    public AlbumDTO criarAlbum(AlbumDTO albumDTO) {

        ArtistaDTO artista = artistaService.listarTodosEmMemoria()
                .stream()
                .filter(a -> a.getId().equals(albumDTO.artistaId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));


        GravadoraDTO gravadora = gravadoraService.listarTodasEmMemoria()
                .stream()
                .filter(g -> g.getId().equals(albumDTO.gravadoraId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Gravadora não encontrada"));


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
        return banco;
    }
}