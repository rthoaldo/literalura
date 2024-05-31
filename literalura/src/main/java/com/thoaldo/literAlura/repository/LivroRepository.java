package com.thoaldo.literAlura.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoaldo.literAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @JsonIgnoreProperties(ignoreUnknown = true)
    Optional<Livro> findById(Long id);
}
