package com.thoaldo.literAlura.service;

import com.thoaldo.literAlura.model.Livro;
import com.thoaldo.literAlura.repository.LivroRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    private ConsumoApi consumo = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();
    private final String ENDERECO = "http://gutendex.com/books/";

    public void saveLivro(String nomeLivro) {
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        ConsumoApiResponse consumoApi = conversor.obterDados(json, ConsumoApiResponse.class);

        if (consumoApi.getCount() > 0) {
            saveLivro(consumoApi.getResults());

            while (consumoApi.getNext() != null) {
                json = consumo.obterDados(consumoApi.getNext());
                consumoApi = conversor.obterDados(json, ConsumoApiResponse.class);
                saveLivro(consumoApi.getResults());
            }
        }
    }

    private void saveLivro(List<Livro> results) {
        livroRepository.saveAll(results);
    }

    public List<Livro> listBooks() {
        return livroRepository.findAll();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConsumoApiResponse {
        private int count;
        private List<Livro> results;
        private String next;

        // Getters e setters
        public int getCount() {

            return count;
        }

        public void setCount(int count) {

            this.count = count;
        }

        public List<Livro> getResults() {

            return results;
        }

        public void setResults(List<Livro> results) {

            this.results = results;
        }

        public String getNext() {

            return next;
        }

        public void setNext(String next) {

            this.next = next;
        }
    }
}
