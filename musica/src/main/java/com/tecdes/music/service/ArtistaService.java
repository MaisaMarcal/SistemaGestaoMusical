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
        // 1. define o ID
        artistaDTO.setId(id);

        // 2. salva na lista
        banco.add(artistaDTO);

        // 3. incrementa o ID
        id++;

        // 4. retorna o artista salvo
        return artistaDTO;
    }

    public List<ArtistaDTO> listarTodosEmMemoria() {
        return banco;
    }
}