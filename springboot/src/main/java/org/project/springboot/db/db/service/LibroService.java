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
    public LibroRepo libroRepo;

    public void save(Libro libro) {
        libroRepo.save(libro);
    }

    public List<Libro> findAll() {
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
    public List<Libro> findAllWithAutoreWithGenere() {
        List<Libro> libri = libroRepo.findAll();
        for (Libro libro : libri) {
            Hibernate.initialize(libro.getAutore());
            Hibernate.initialize(libro.getGeneri());
        }
        return libri;
    }

    public List<Libro> findByTitoloStartingWithP() {
        return libroRepo.findByTitoloStartingWithIgnoreCase("P");
    }

    public List<Libro> findByISBN() {
        return libroRepo.findByISBNIgnoreCase("978-3-16-148410-0");
    }

}
