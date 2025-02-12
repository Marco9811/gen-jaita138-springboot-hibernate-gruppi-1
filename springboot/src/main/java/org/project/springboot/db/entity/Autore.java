package org.project.springboot.db.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity // Indica che questa classe è un'entità JPA

public class Autore {
	@Id // Indica la chiave primaria
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento dell'id
	
	private Long id;

	@Column(length = 50) // Definisce la colonna nel database 
	private String nome;

	@Column(length = 50) // Definisce la colonna nel database 
	private String cognome;

	@Column(length = 50) // Definisce la colonna nel database 
	private String nazionalita;

    @OneToMany(mappedBy = "autore")
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }
    

    @Override
    public String toString() {
        return "Autore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", nazionalita=" + nazionalita
                + ", libri=" + libri + "]";
    }

    
    


}
