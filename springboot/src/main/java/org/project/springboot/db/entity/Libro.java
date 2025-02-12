package org.project.springboot.db.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;

@Entity // Indica che questa classe è un'entità JPA

public class Libro {
	@Id // Indica la chiave primaria
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento dell'id
	
	private Long id;

	@Column(length = 50) // Definisce la colonna nel database 
	private String titolo;

	@Column(length = 50) // Definisce la colonna nel database 
	private LocalDate dataPubblicazione;

	@Column(length = 50) // Definisce la colonna nel database 
	private String ISBN;

	 @ManyToOne
	 @JoinTable(
		name="libro_genere",
		joinColumns = @JoinColumn(name="libro_id"),
		inverseJoinColumns = @JoinColumn(name="genere_id")
	 )
	 private List<Genere> generi;

	// Costruttore vuoto (obbligatorio per JPA) 
	// public Libro() { 
	// }

	// public Libro(String titolo, LocalDate dataPubblicazione, String iSBN) {
	// 	this.titolo = titolo;
	// 	this.dataPubblicazione = dataPubblicazione;
	// 	ISBN = iSBN;
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	

	

}