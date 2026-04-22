package com.tecdes.music.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.music.dto.ArtistaDTO;

@Service
public class ArtistaService {

    private static final List<ArtistaDTO> banco = new ArrayList<>();
    private Long id = 1L;


    public ArtistaDTO salvarTemporariamente(ArtistaDTO artistaDTO) {

        ArtistaDTO novoArtista = new ArtistaDTO(
                id,
                artistaDTO.NomeArtista(),
                artistaDTO.GeneroMusical(),
                artistaDTO.PaisOrigem(),
                artistaDTO.AnoFormacao()
        );

        banco.add(novoArtista);
        id++;

        return novoArtista;
    }
    public List<ArtistaDTO> listarTodosEmMemoria() {
        return new ArrayList<>(banco);
    }

    public ArtistaDTO buscarPorId(Long id) {
        return banco.stream()
                .filter(a -> a.Id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));
    }


    public void deletar(Long id) {
        buscarPorId(id);
        banco.removeIf(a -> a.Id().equals(id));
    }

    public ArtistaDTO atualizar(Long id, ArtistaDTO dto) {

        buscarPorId(id);

        ArtistaDTO atualizado = new ArtistaDTO(
                id,
                dto.NomeArtista(),
                dto.GeneroMusical(),
                dto.PaisOrigem(),
                dto.AnoFormacao()
        );

        deletar(id);
        banco.add(atualizado);

        return atualizado;
    }

    public ArtistaDTO atualizarParcial(Long id, ArtistaDTO dto) {

        ArtistaDTO existente = buscarPorId(id);

        ArtistaDTO atualizado = new ArtistaDTO(
                id,
                dto.NomeArtista()!= null ? dto.NomeArtista() : existente.NomeArtista(),
                dto.GeneroMusical() != null ? dto.GeneroMusical() : existente.GeneroMusical(),
                dto.PaisOrigem()!= null ? dto.PaisOrigem() : existente.PaisOrigem(),
                dto.AnoFormacao()!= null ? dto.AnoFormacao() : existente.AnoFormacao()
        );

        deletar(id);
        banco.add(atualizado);

        return atualizado;
    }
}