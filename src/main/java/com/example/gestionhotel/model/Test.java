package com.example.gestionhotel.model;

public class Test {
    public static void main(String[] args) {
        new DbConnector();
        System.out.println( DbConnector.getWorkerPassword("eb416362") );
        Receptionist wrk = new Receptionist();
        wrk.login("eb416362", "Enchantress***912");
        System.out.println(wrk.getFunction());
    }
}
