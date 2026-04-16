package com.tecdes.music.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class Artista {


    private Long Id;
    private String NomeArtista;
    private String GeneroMusical;
    private String PaisOrigem;
    private Integer AnoFormacao;




}


