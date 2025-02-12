package org.project.springboot.db.db.service;
import java.util.List;

import org.hibernate.Hibernate;
import org.project.springboot.db.db.entity.Libro;
import org.project.springboot.db.db.repository.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class LibroService {
    @Autowired
    private LibroRepo libroRepo;

    public List <Libro> findAll(){
        return libroRepo.findAll();
    }

    @Transactional
    public List<Libro> findAllWithAutore() {
        List<Libro> libri = libroRepo.findAll();
        for (Libro libro : libri) {
            Hibernate.initialize(libro.getAutore());
        }
        return libri;
    }

    @Transactional
    public List<Libro> findAllWithAutoreWithGenere(){
        List<Libro> libri = libroRepo.findAll();
        for (Libro libro : libri) {
            Hibernate.initialize(libro.getAutore());
        }
                return libri;
    }

}
