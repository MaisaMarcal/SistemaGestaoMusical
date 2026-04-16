package com.tecdes.music.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecdes.music.dto.ArtistaDTO;
import com.tecdes.music.service.ArtistaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/artistas")
@RequiredArgsConstructor
public class ArtistaController {
    
    private final ArtistaService artistaService;

    @PostMapping("/salvar")
    public ResponseEntity<ArtistaDTO> postSalvarArtista(@RequestBody ArtistaDTO artistaDTO) {
        ArtistaDTO artistaCriado = artistaService.salvarTemporariamente(artistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaCriado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ArtistaDTO>> getListarTudo() {
        List<ArtistaDTO> artistas = artistaService.listarTodosEmMemoria();
        return ResponseEntity.ok(artistas);
    }
}