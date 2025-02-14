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
        System.out.println("1. Visualizza tutti i libri");
        System.out.println("2. Visualizza tutti i libri con autore");
        System.out.println("3. Visualizza tutti i libri con autore e generi");
        System.out.println("4. Visualizza tutti gli autori");
        System.out.println("5. Visualizza tutti i generi");
        System.out.println("6. Esci");
        System.out.println("");

        int strValue= sc.nextInt();
        sc.nextInt();

        switch (strValue) {

            case 1:
                
                
                break;

            case 2:


            case 3:


            case 4:
        
            default:
                break;
        }
        
    }




}
