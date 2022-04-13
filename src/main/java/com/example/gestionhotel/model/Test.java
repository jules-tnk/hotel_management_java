package com.example.gestionhotel.model;

public class Test {
    public static void main(String[] args) {
        new DbConnector();
        System.out.println( DbConnector.getWorkerPassword("eb416362") );
        Receptionist wrk = new Receptionist();
        System.out.println(wrk.getFunction());
    }
}
