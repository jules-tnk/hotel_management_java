package com.example.gestionhotel.model.test;

import com.example.gestionhotel.model.DbConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTEST {
    {

    }
    public static void main(String[] args) {
        new DbConnector();
        ResultSet resultSet = DbConnector.executeQueryRequest("SELECT DATEDIFF(CURDATE(), date) AS duration FROM transaction WHERE idTransaction=5");
        //resultSet = DbConnector.executeQueryRequest("SELECT date AS duration FROM transaction WHERE idTransaction=");
        try {
            if (resultSet.next()){
                System.out.println( resultSet.getInt("duration") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
