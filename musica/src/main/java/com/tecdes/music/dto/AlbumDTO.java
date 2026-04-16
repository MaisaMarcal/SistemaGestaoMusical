package com.tecdes.music.dto;

public record AlbumDTO (

    Long id,
    String titulo,
    Integer anoLancamento,
    Integer quantidadeFaixas,
    Long artistaId,
    Long gravadoraId

){}
