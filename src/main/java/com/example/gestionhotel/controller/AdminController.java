package com.example.gestionhotel.controller;

import com.example.gestionhotel.model.Admin;
import com.example.gestionhotel.model.Receptionist;

public class AdminController {
    static Admin admin;

    //GETTER
    public Admin getAdmin() { return admin; }

    //SETTER
    public static void setAdmin(Admin newAdmin) { admin = newAdmin; }

}
