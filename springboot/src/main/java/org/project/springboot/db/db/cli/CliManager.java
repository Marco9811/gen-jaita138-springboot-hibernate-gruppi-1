package org.project.springboot.db.db.cli;

import java.util.Scanner;

import org.project.springboot.db.db.service.AutoreService;
import org.project.springboot.db.db.service.GenereService;
import org.project.springboot.db.db.service.LibroService;

public class CliManager {
    private Scanner sc;
    private LibroService libroService;
    private GenereService genereService;
    private AutoreService autoreService;

    public CliManager(
        LibroService libroService,
        GenereService genereService,
        AutoreService autoreService
    ){
        this.libroService=libroService;
        this.autoreService=autoreService;
        this.genereService=genereService;

        sc=new Scanner(System.in);

        printOptions();
    }
    private void printOptions() {

        System.out.println("Operazioni:");
        System.out.println("1. Visualizza tutte le auto");
        System.out.println("2. Inserisci nuova auto");
        System.out.println("3. Modifica un auto esistente");
        System.out.println("4. Elimina un auto esistente");
        System.out.println("5. Stampa dettagli auto");
        System.out.println("6. Esci");
        System.out.println("");

        int strValue= sc.nextInt();
        sc.nextInt();

        switch (strValue) {

            case 1:
                readAll();
                
                break;

            case 2:


            case 3:


            case 4:
        
            default:
                break;
        }
        
    }




}
