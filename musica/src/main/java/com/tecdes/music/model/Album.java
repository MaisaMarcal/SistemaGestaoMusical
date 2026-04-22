package com.tecdes.music.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {

    private Long id;
    private String titulo;
    private Integer quantidadeFaixas;
    private Integer anoLancamento;

    // 🔗 relacionamento (mesmo sem JPA)
    private Artista artista;
    private Gravadora gravadora;
}