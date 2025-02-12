package org.project.springboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import db.entity.Libro;


@Repository
public interface LibroRepo extends JpaRepository<Libro, Long>{
    List<Libro> findByTitoloStartingWithIgnoreCase(String lettera);
    List<Libro> findByAnnoPublicazioneBetween(int inizioAnno,int fineAnno);
    List<Libro> findByIBSNIgnoreCase(String iBSN);

}