package com.thoaldo.literAlura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "idiomas")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "idiomas")  // 'idiomas' deve corresponder ao nome da lista na classe Livro
    private List<Livro> livros;

    public Idioma() {}

    public Idioma(String name) {
        this.name = name;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
