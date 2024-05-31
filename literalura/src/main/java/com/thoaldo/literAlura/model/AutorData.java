package com.thoaldo.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorData(@JsonAlias("name")String nomeAutor,
                         @JsonAlias("birth_year") Integer Nascimento,
                         @JsonAlias("death_year") Integer Falecimento) {
}