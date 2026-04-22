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

    // POST /api/artistas
    @PostMapping
    public ResponseEntity<ArtistaDTO> salvar(@RequestBody ArtistaDTO artistaDTO) {
        ArtistaDTO artistaCriado = artistaService.salvarTemporariamente(artistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaCriado);
    }

    // GET /api/artistas
    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> listar() {
        return ResponseEntity.ok(artistaService.listarTodosEmMemoria());
    }
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
    artistaService.deletar(id);
    return ResponseEntity.noContent().build();
}

@PutMapping("/{id}")
public ResponseEntity<ArtistaDTO> atualizar(@PathVariable Long id,
                                            @RequestBody ArtistaDTO dto) {
    return ResponseEntity.ok(artistaService.atualizar(id, dto));
}

@PatchMapping("/{id}")
public ResponseEntity<ArtistaDTO> atualizarParcial(@PathVariable Long id,
                                                   @RequestBody ArtistaDTO dto) {
    return ResponseEntity.ok(artistaService.atualizarParcial(id, dto));
}
}