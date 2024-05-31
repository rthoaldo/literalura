package com.thoaldo.literAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ElementCollection
    private List<String> subjects;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livros_idiomas",
            joinColumns = @JoinColumn(name = "livros_id"),
            inverseJoinColumns = @JoinColumn(name = "idiomas_id"))
    private List<Idioma> idiomas;  // Deve corresponder ao 'mappedBy' na classe Idioma

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livros_pessoas",
            joinColumns = @JoinColumn(name = "livros"),
            inverseJoinColumns = @JoinColumn(name = "autores"))
    private List<Autor> name;

    private Integer download_count;

    public Livro() {
    }

    public Livro(String title, List<Idioma> language, Integer download_count) {
        this.title = title;
        this.idiomas = language;
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        return String.format("Livro:\nTítulo: %s\nIdiomas: %s\nQtd Downloads: %d",
                title, idiomas.stream().map(Idioma::getName).collect(Collectors.joining(", ")), download_count);
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloadCount() {
        return download_count;
    }

    public void setDownloadCount(Integer download_count) {
        this.download_count = download_count;
    }
}
