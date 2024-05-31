package com.thoaldo.literAlura.repository;

import com.thoaldo.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.birth_year <= :year AND (a.death_year IS NULL OR a.death_year >= :year)")
    Optional<Autor> findAuthorAliveInSpecifYear(@Param("year") Integer year);
}
