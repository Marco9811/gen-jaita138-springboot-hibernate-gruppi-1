package org.project.springboot.db.db.cli;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.project.springboot.db.db.entity.Autore;
import org.project.springboot.db.db.entity.Genere;
import org.project.springboot.db.db.entity.Libro;
import org.project.springboot.db.db.service.AutoreService;
import org.project.springboot.db.db.service.GenereService;
import org.project.springboot.db.db.service.LibroService;

public class CliManager {
    public Scanner sc;
    public LibroService libroService;
    public GenereService genereService;
    public AutoreService autoreService;

    public CliManager(
            LibroService libroService,
            GenereService genereService,
            AutoreService autoreService) {
        this.libroService = libroService;
        this.autoreService = autoreService;
        this.genereService = genereService;

        printOptions();
    }

    private void printOptions() {
        sc = new Scanner(System.in);
        System.out.println("Operazioni:");
        System.out.println("1. Visualizza tutti i libri");
        System.out.println("2. Inserisci un nuovo libro");
        System.out.println("3. Visualizza tutti i generi");
        System.out.println("4. Inserisci un nuovo genere");
        System.out.println("5. Visualizza tutti gli autori");
        System.out.println("6. Inserisci un nuovo autore");
        System.out.println("7. Esci");
        System.out.println("Inserisci il valore che vuoi");

        int strValue = sc.nextInt();
        sc.nextLine();

        switch (strValue) {
            case 1:
                libriReadAll();
                break;
            case 2:
                addLibri();
                break;
            case 3:
                genereReadAll();
                break;
            case 4:
                addGenere();
                break;
            case 5:
                autoreReadAll();
                break;
            case 6:
                addAutore();
                break;
            case 7:
                System.out.println("Arrivederci");
                return;
            default:
                System.out.println("Operazione non valida");
        }
        printOptions();

    }

    public void libriReadAll() {
        List<Libro> libri = libroService.findAll();

        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato.");
        } else {
            System.out.println("Lista dei libri disponibili:");
            System.out.println("-------------------------------------");

            for (Libro libro : libri) {
                System.out.println("Titolo: " + libro.getTitolo());
                System.out.println("Autore: " + libro.getAutore().getNome() + " " + libro.getAutore().getCognome());
                System.out.println("Anno Pubblicazione: " + libro.getDataPubblicazione());
                System.out.println("ISBN: " + libro.getISBN());
                System.out.println("-------------------------------------");
            }
        }
    }

    private void addLibri() {
        Libro libro = new Libro();

        System.out.println("Inserisci il titolo del libro:");
        String titolo = sc.nextLine();
        libro.setTitolo(titolo);

        System.out.println("Inserisci la data di pubblicazione (YYYY-MM-DD):");
        String dataInput = sc.nextLine();
        try {
            libro.setDataPubblicazione(LocalDate.parse(dataInput));
        } catch (DateTimeParseException e) {
            System.out.println("Formato data non valido! Riprova.");
            return;
        }

        System.out.println("Inserisci il codice ISBN:");
        String isbn = sc.nextLine();
        libro.setISBN(isbn);

        // Selezione autore
        List<Autore> autori = autoreService.findAll();
        if (autori.isEmpty()) {
            System.out.println("Nessun autore disponibile. Aggiungi un autore prima di inserire un libro.");
            return;
        }

        System.out.println("Seleziona un autore:");
        for (int i = 0; i < autori.size(); i++) {
            System.out.println((i + 1) + ". " + autori.get(i).getNome() + " " + autori.get(i).getCognome());
        }
        System.out.println("Inserisci il numero corrispondente:");
        int autoreIndex = sc.nextInt();
        sc.nextLine(); 

        if (autoreIndex < 1 || autoreIndex > autori.size()) {
            System.out.println("Scelta non valida.");
            return;
        }
        libro.setAutore(autori.get(autoreIndex - 1));

        // Selezione generi
        List<Genere> generi = genereService.findAll();
        if (generi.isEmpty()) {
            System.out.println("Nessun genere disponibile. Aggiungi un genere prima di inserire un libro.");
            return;
        }

        System.out.println("Seleziona i generi (separati da virgola):");
        for (int i = 0; i < generi.size(); i++) {
            System.out.println((i + 1) + ". " + generi.get(i).getNome());
        }
        System.out.println("Inserisci i numeri corrispondenti separati da virgola:");
        String generiInput = sc.nextLine();
        String[] generiScelti = generiInput.split(",");

        List<Genere> generiSelezionati = new ArrayList<>();
        for (String g : generiScelti) {
            try {
                int index = Integer.parseInt(g.trim());
                if (index >= 1 && index <= generi.size()) {
                    generiSelezionati.add(generi.get(index - 1));
                } else {
                    System.out.println("Numero " + index + " non valido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato non valido: " + g);
            }
        }

        if (generiSelezionati.isEmpty()) {
            System.out.println("Nessun genere selezionato. Aggiungi almeno un genere.");
            return;
        }
        libro.setGeneri(generiSelezionati);


        libroService.save(libro);
        System.out.println("Libro aggiunto con successo!");
    }

    public void autoreReadAll() {
        List<Autore> autori = autoreService.findAll();

        if (autori.isEmpty() || autori == null) {
            System.out.println("Nessun autore trovato");
        } else {
            System.out.println("Lista autori:");
            System.out.println("-------------------------------------");

            for (Autore autore : autori) {
                System.out.println("Nome: " + autore.getNome());
                System.out.println("Cognome: " + autore.getCognome());
                System.out.println("Nazionalità: " + autore.getNazionalita());
                System.out.println("-------------------------------------");
            }
        }
    }

    private void addAutore() {
        Autore a = new Autore();

        System.out.println("Inserisci il nome");
        String nome = sc.nextLine();
        a.setNome(nome);

        System.out.println("Inserisci il cognome");
        String cognome = sc.nextLine();
        a.setCognome(cognome);

        System.out.println("Inserisci la nazionalità");
        String nazionalita = sc.nextLine();
        a.setNazionalita(nazionalita);

        autoreService.save(a);
    }

    public void genereReadAll() {
        List<Genere> generi = genereService.findAll();

        if (generi.isEmpty() || generi == null) {
            System.out.println("Nessun genere trovato");
        } else {
            System.out.println("Lista generi:");
            System.out.println("-------------------------------------");

            for (Genere genere : generi) {
                System.out.println("Nome: " + genere.getNome());

                System.out.println("-------------------------------------");
            }
        }
    }

    private void addGenere() {
        Genere g = new Genere();

        System.out.println("Inserisci il nome del genere");
        String nome = sc.nextLine();
        g.setNome(nome);

        genereService.save(g);
    }

}
