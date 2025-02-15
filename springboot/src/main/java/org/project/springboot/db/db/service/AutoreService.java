package org.project.springboot.db.db.service;

import java.util.List;
import org.project.springboot.db.db.entity.Autore;
import org.project.springboot.db.db.repository.AutoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepo autoreRepo;

    public List<Autore> findAll() {
        return autoreRepo.findAll();
    }

    public void save(Autore autore) {
        autoreRepo.save(autore);
    }

    public void delete(Autore autore){
        autoreRepo.delete(autore);
    }

    public Autore findById(Long id) {
      return autoreRepo.findById(id).orElse(null);
    }
    

}
