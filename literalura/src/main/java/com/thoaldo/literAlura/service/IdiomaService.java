package com.thoaldo.literAlura.service;

import com.thoaldo.literAlura.model.Idioma;
import com.thoaldo.literAlura.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {

    @Autowired
    private IdiomaRepository idiomaRepository;

    public List<Idioma> findByName(String name) {
        return idiomaRepository.findByName(name);
    }

    public List<Idioma> findAll() {
        return idiomaRepository.findAll();
    }
}
