package org.project.springboot.db.db.repository;

import org.project.springboot.db.db.entity.Genere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenereRepo extends JpaRepository<Genere, Long> {

    }
