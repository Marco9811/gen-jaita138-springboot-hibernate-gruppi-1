package org.project.springboot.db.db.service;

import java.util.List;
import org.project.springboot.db.db.entity.Autore;
import org.project.springboot.db.db.repository.AutoreRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class AutoreService {

    @Autowired
    private AutoreRepo autoreRepo;

    public List<Autore> findAll(){
        return autoreRepo.findAll();
    }

}
