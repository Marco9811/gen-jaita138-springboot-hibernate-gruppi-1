package org.project.springboot.repository;

import org.project.springboot.db.entity.Genere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenereRepo extends JpaRepository<Genere, Long> {

    }
