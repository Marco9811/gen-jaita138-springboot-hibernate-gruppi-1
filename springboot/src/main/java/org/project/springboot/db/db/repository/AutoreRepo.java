package org.project.springboot.db.db.repository;

import org.project.springboot.db.db.entity.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoreRepo extends JpaRepository<Autore, Long> {

}
