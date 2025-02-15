package org.project.springboot.db.db.repository;
import org.project.springboot.db.db.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface LibroRepo extends JpaRepository<Libro, Long>{

    List<Libro> findByTitoloStartingWithIgnoreCase(String string);

    // List<Libro> findByAnnoBetween(int i, int j);

    List<Libro> findByISBNIgnoreCase(String string);
}