package com.thoaldo.literAlura.service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoaldo.literAlura.model.Autor;
import com.thoaldo.literAlura.model.Idioma;
import com.thoaldo.literAlura.model.Livro;
import com.thoaldo.literAlura.repository.AutorRepository;
import com.thoaldo.literAlura.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;


    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverterDados conversor = new ConverterDados();
    private final String ENDERECO = "http://gutendex.com/books/";

    public void saveLivro(String nomeLivro) {
        String searchUrl = ENDERECO + "?search=" + nomeLivro.replace(" ", "%20");
        boolean livroSalvo = false;

        do {
            String json = consumoApi.obterDados(searchUrl);
            ConsumoApiResponse consumoApiResponse = conversor.obterDados(json, ConsumoApiResponse.class);

            if (consumoApiResponse != null && consumoApiResponse.getResults() != null) {
                for (Livro livro : consumoApiResponse.getResults()) {
                    if (livroSalvo) {
                        break;
                    }

                    if (!livroRepository.existsByTitle(livro.getTitle())) {
                        List<Autor> autores = new ArrayList<>();
                        for (Autor autor : livro.getAuthors()) {
                            Autor autorExistente = autorRepository.findByNameAndBirthYearAndDeathYear(
                                    autor.getName(), autor.getBirthYear(), autor.getDeathYear()
                            ).orElse(autor);
                            autores.add(autorExistente);
                        }
                        autorRepository.saveAll(autores);
                        livro.setAuthors(autores);

                        List<Idioma> idiomas = new ArrayList<>();
                        for (Idioma idioma : livro.getIdiomas()) {
                            Idioma idiomaExistente = new Idioma(idioma.getName());
                            idiomas.add(idiomaExistente);
                        }
                        livro.setIdiomas(idiomas);

                        livroRepository.save(livro);
                        livroSalvo = true;  // Marca que um livro foi salvo
                    }
                }
                searchUrl = consumoApiResponse.getNext();
            } else {
                searchUrl = null;
            }
        } while (searchUrl != null && !livroSalvo);  // Interrompe o loop se um livro foi salvo
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
