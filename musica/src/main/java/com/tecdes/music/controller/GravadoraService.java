package com.tecdes.music.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.music.dto.GravadoraDTO;

@Service
public class GravadoraService {

    private static final List<GravadoraDTO> banco = new ArrayList<>();
    private Long id = 1L;

    public GravadoraDTO salvarTemporariamente(GravadoraDTO gravadoraDTO) {


        if (gravadoraDTO.getFaturamentoAnual() < 0) {
            throw new RuntimeException("Faturamento não pode ser negativo");
        }

        // define ID
        gravadoraDTO.setId(id);

        // salva na lista
        banco.add(gravadoraDTO);

        // incrementa ID
        id++;

        return gravadoraDTO;
    }

    public List<GravadoraDTO> listarTodasEmMemoria() {
        return banco;
    }
}