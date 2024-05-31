package com.thoaldo.literAlura.service;

public interface IConverterDados {
    <T> T  obterDados(String json, Class<T> classe);
}
