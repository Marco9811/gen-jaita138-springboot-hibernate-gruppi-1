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
        System.out.println("7. Elimina genere");
        System.out.println("8. Elimina autore");
        System.out.println("9. Elimina libro");
        System.out.println("10. Esci");
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
                deleteGenere();
                break;

            case 8:
                deleteAutore();
                break;

            case 9:
                deleteLibro();
                break;

            case 10:
                System.out.println("Arrivederci");
                return;
            default:
                System.out.println("Operazione non valida");
        }
        printOptions();

    }

    // Funzione libri
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
            libro.setAutore(addAutore());
        } else {
            System.out.println("Seleziona un autore o crea nuovo autore:");
            for (int i = 0; i < autori.size(); i++) {
                System.out.println((i + 1) + ". " + autori.get(i).getNome() + " " + autori.get(i).getCognome());
            }
            System.out.println((autori.size() + 1) + ". Crea nuovo autore: ");
            System.out.println("Inserisci il numero corrispondente:");
            int autoreIndex = sc.nextInt();
            sc.nextLine();

            if (autoreIndex < 1 || autoreIndex > autori.size() + 1) {
                System.out.println("Scelta non valida.");
                return;
            } else if (autoreIndex == autori.size() + 1) {
                libro.setAutore(addAutore());
            } else {
                libro.setAutore(autori.get(autoreIndex - 1));
            }
        }

        // System.out.println("Seleziona un autore:");
        // for (int i = 0; i < autori.size(); i++) {
        // System.out.println((i + 1) + ". " + autori.get(i).getNome() + " " +
        // autori.get(i).getCognome());
        // }
        // System.out.println("Inserisci il numero corrispondente:");
        // int autoreIndex = sc.nextInt();
        // sc.nextLine();

        // if (autoreIndex < 1 || autoreIndex > autori.size()) {
        // System.out.println("Scelta non valida.");
        // return;
        // }
        // libro.setAutore(autori.get(autoreIndex - 1));

        // Selezione generi
        List<Genere> generi = genereService.findAll();
        List<Genere> generiSelezionati = new ArrayList<>();
        boolean continua = true;
        if (generi.isEmpty()) {
            System.out.println("Nessun genere disponibile. Aggiungi un genere prima di inserire un libro.");
            while (continua) {
                generiSelezionati.add(addGenere());
                System.out.println("vuoi aggiungere un'altro genere?: S/N");
                String scelta = sc.nextLine();
                if (scelta.equalsIgnoreCase("N")) {
                    libro.setGeneri(generiSelezionati);
                    continua = false;
                }
            }
        }

    
        while (continua) {
            genereReadAll();
            System.out.println("Il genere è presente nella lista? S/N");
            String scelta = sc.nextLine();

            if (scelta.equalsIgnoreCase("S")) {
                System.out.print("Inserisci il genere da ID: ");
                Genere genere = genereService.findById(sc.nextLong());
                sc.nextLine();
                generiSelezionati.add(genere);
            } else {
                generiSelezionati.add(addGenere());
            }
            System.out.println("Vuoi inserire un altro genere? S/N");
            String nuovaScelta = sc.nextLine();
            if (nuovaScelta.equalsIgnoreCase("n")) {
                continua = false;
            }
        }
        libro.setGeneri(generiSelezionati);
        libroService.save(libro);
        System.out.println("Libro aggiunto con successo!");

        // System.out.println("Seleziona i generi (separati da virgola) o crea nuovo genere: ");
        // for (int i = 0; i < generi.size(); i++) {
        //     System.out.println((i + 1) + ". " + generi.get(i).getNome());
        // }
        // System.out.println("Inserisci i numeri corrispondenti separati da virgola:");
        // String generiInput = sc.nextLine();
        // String[] generiScelti = generiInput.split(",");

        // // List<Genere> generiSelezionati = new ArrayList<>();
        // for (String g : generiScelti) {
        //     try {
        //         int index = Integer.parseInt(g.trim());
        //         if (index >= 1 && index <= generi.size()) {
        //             generiSelezionati.add(generi.get(index - 1));
        //         } else {
        //             System.out.println("Numero " + index + " non valido.");
        //         }
        //     } catch (NumberFormatException e) {
        //         System.out.println("Formato non valido: " + g);
        //     }
        // }

        // if (generiSelezionati.isEmpty()) {
        //     System.out.println("Nessun genere selezionato. Aggiungi almeno un genere.");
        //     return;
        // }
        // libro.setGeneri(generiSelezionati);

        // libroService.save(libro);
        // System.out.println("Libro aggiunto con successo!");
    }

    // Funzioni autore
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

    private Autore addAutore() {
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
        return a;
    }

    // Funzioni Genere
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

    private Genere addGenere() {
        Genere g = new Genere();

        System.out.println("Inserisci il nome del genere");
        String nome = sc.nextLine();
        g.setNome(nome);

        genereService.save(g);
        return g;
    }

    private void deleteGenere() {
        System.out.println("Quale genere vuoi eliminare?");
        List<Genere> generi = genereService.findAll();
        for (int i = 0; i < generi.size(); i++) {
            System.out.println(generi.get(i).getId() + ". " + generi.get(i).getNome());
        }
        System.out.println("Inserisci il numero:");
        long scelta = sc.nextLong();
        sc.nextLine();
        Genere g = genereService.findById(scelta);
        if (g != null) {
            genereService.delete(g);
            System.out.println("Genere eliminato con successo");
        } else {
            System.out.println("Genere non trovato");
        }

    }

    private void deleteAutore() {
        List<Autore> autori = autoreService.findAll();
        for (int i = 0; i < autori.size(); i++) {
            System.out.println(autori.get(i).getId() + ". " + autori.get(i).getNome());
        }
        System.out.println("Inserisci il numero:");
        long scelta = sc.nextLong();
        sc.nextLine();
        Autore a = autoreService.findById(scelta);
        if (a != null) {
            autoreService.delete(a);
            System.out.println("Autore eliminato con successo");
        } else {
            System.out.println("Autore non trovato");
        }

    }

    private void deleteLibro() {
        List<Libro> libri = libroService.findAll();
        for (int i = 0; i < libri.size(); i++) {
            System.out.println(libri.get(i).getId() + ". " + libri.get(i).getTitolo());
        }
        System.out.println("Inserisci il numero:");
        long scelta = sc.nextLong();
        sc.nextLine();
        Libro l = libroService.findById(scelta);
        if (l != null) {
            libroService.delete(l);
            System.out.println("Libro eliminato con successo");
        } else {
            System.out.println("Libro non trovato");
        }

    }

}
