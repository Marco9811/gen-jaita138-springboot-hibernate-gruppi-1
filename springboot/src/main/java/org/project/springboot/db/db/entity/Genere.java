package org.project.springboot.db.db.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity // Indica che questa classe è un'entità JPA

public class Genere {
    @Id // Indica la chiave primaria

    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento dell'id

    private Long id;

    @Column(length = 50) // Definisce la colonna nel database
    private String nome;

    @ManyToMany
    private List<Libro> libri;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Genere [id=" + id + ", nome=" + nome + ", libri=" + "]";
    }
}
