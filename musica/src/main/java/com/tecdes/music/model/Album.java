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

public class Album {
    private Long Id;
    private String TítuloAlbum;
    private Integer QtdFaixas;
    private Integer AnoLancamento;




}
