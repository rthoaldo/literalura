package com.thoaldo.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroData(@JsonAlias("title")String Livro,
                       @JsonAlias("download_count") Integer qtdDownloads,
                       @JsonAlias("languages") List<String> idioma) {
}