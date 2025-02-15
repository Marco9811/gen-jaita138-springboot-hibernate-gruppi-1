package org.project.springboot.db.db.service;

import java.util.List;

import org.project.springboot.db.db.entity.Genere;
import org.project.springboot.db.db.repository.GenereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenereService {

    @Autowired
    private GenereRepo genereRepo;

    public List<Genere> findAll() {
        return genereRepo.findAll();
    }

    public void save(Genere genere) {
        genereRepo.save(genere);
    }

    public void delete(Genere genere){
        genereRepo.delete(genere);
    }

    public Genere findById(Long id) {
      return genereRepo.findById(id).orElse(null);
    }

}
