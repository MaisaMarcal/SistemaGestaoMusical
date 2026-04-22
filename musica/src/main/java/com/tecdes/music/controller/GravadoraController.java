package com.tecdes.music.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecdes.music.dto.GravadoraDTO;
import com.tecdes.music.model.Gravadora;
import com.tecdes.music.service.GravadoraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gravadoras")
@RequiredArgsConstructor
public class GravadoraController {

    private final GravadoraService gravadoraService;

    @PostMapping
    public ResponseEntity<Gravadora> salvar(@RequestBody Gravadora gravadora) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gravadoraService.salvar(gravadora));
    }

    @GetMapping
    public ResponseEntity<List<Gravadora>> listar() {
        return ResponseEntity.ok(gravadoraService.listarTodas());
    }
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
    gravadoraService.deletar(id);
    return ResponseEntity.noContent().build();
}

@PutMapping("/{id}")
public ResponseEntity<Gravadora> atualizar(@PathVariable Long id,
                                              @RequestBody GravadoraDTO dto) {
    return ResponseEntity.ok(gravadoraService.atualizar(id, dto));
}

@PatchMapping("/{id}")
public ResponseEntity<Gravadora> atualizarParcial(@PathVariable Long id,
                                                     @RequestBody GravadoraDTO dto) {
    return ResponseEntity.ok(gravadoraService.atualizarParcial(id, dto));
}
}