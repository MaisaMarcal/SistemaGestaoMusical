package com.tecdes.music.controller;

import org.springframework.web.bind.annotation.*;

import com.tecdes.music.dto.AlbumDTO;
import com.tecdes.music.service.AlbumService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/albuns")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    
    // salvar
    @PostMapping
    public ResponseEntity<AlbumDTO> postSalvarAlbum(@RequestBody AlbumDTO albumDTO) {
        AlbumDTO albumCriado = albumService.criarAlbum(albumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(albumCriado);
    }

    // listar tudo
    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getListarTudo() {
        List<AlbumDTO> albuns = albumService.listarAlbuns();
        return ResponseEntity.ok(albuns);
    }
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Long id) {
    albumService.deletar(id);
    return ResponseEntity.noContent().build();
}

@PutMapping("/{id}")
public ResponseEntity<AlbumDTO> atualizar(@PathVariable Long id,
                                          @RequestBody AlbumDTO dto) {
    return ResponseEntity.ok(albumService.atualizar(id, dto));
}

@PatchMapping("/{id}")
public ResponseEntity<AlbumDTO> atualizarParcial(@PathVariable Long id,
                                                 @RequestBody AlbumDTO dto) {
    return ResponseEntity.ok(albumService.atualizarParcial(id, dto));
}
}