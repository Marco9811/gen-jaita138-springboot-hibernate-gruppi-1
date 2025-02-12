package org.project.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.project.springboot.db.entity.Libro;



@Repository
public interface LibroRepo extends JpaRepository<Libro, Long>{

}