package com.thoaldo.literAlura;

import com.thoaldo.literAlura.principal.Principal;
import com.thoaldo.literAlura.service.LivroService;
import com.thoaldo.literAlura.service.AutorService;
import com.thoaldo.literAlura.service.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private LivroService livroService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private IdiomaService idiomaService;
    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(livroService, autorService, idiomaService);
        principal.exibeMenu();
    }
}
