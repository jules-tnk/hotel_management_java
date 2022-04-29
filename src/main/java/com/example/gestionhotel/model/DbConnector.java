package com.example.gestionhotel.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbConnector {
    //ATTRIBUTES
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/hotelmanagementdb";

    private static final String databaseUser = "root";
    private static final String databasePassword = "";

    private static Connection connection = null;
    private static Statement statement = null;
    private static String request;
    private static ResultSet resultSet;

    public static ResultSet executeQueryRequest(String request) {
        ResultSet result;
        try {
            result = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static int executeUpdateRequest(String request) {
        int result;
        try {
            result = statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return result;
    }

    public static void connectDatabase(){
        if ( connection == null ){
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(DATABASE_URL, databaseUser, databasePassword);
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connected...");
    }

    public static void disconnectDatabase(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //MANAGE WORKERS
    public static boolean addReceptionist(Receptionist receptionist) {
        request = String.format("INSERT INTO worker (id, firstName, lastName, phoneNumber, email, birthDate, function, password) VALUES(\"%s\",\"%s\",\"%s\",%s,\"%s\",\"%s\", \"%s\", \"%s\")",
                receptionist.getId(), receptionist.getFirstName(), receptionist.getLastName(), receptionist.getPhoneNumber(), receptionist.getEmail(), receptionist.getBirthDate(), receptionist.getFunction(), receptionist.getPassword());
        int result = executeUpdateRequest(request);

        return result == 1;
    }

    public static boolean removeReceptionist(String idReceptionist) {
        request = String.format("DELETE FROM worker where id=\"%s\"", idReceptionist);
        int result = executeUpdateRequest(request);
        return result == 1;
    }

    public static Admin getAdmin(String adminId) {
        request = String.format("SELECT * FROM worker WHERE id=\"%s\"", adminId);
        resultSet = executeQueryRequest(request);
        Admin admin = new Admin();
        try {
            while (resultSet.next()) {
                admin.setLastName(resultSet.getString("lastName"));
                admin.setFirstName(resultSet.getString("firstName"));
                admin.setFunction(resultSet.getString("function"));
                admin.setId(resultSet.getString("id"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPhoneNumber(resultSet.getInt("phoneNumber"));
                admin.setBirthDate(resultSet.getDate("birthDate"));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Receptionist getReceptionist(String receptionistId) {
        request = String.format("SELECT * FROM worker WHERE id=\"%s\"", receptionistId);
        resultSet = executeQueryRequest(request);
        Receptionist receptionist = new Receptionist();
        try {
            while (resultSet.next()) {
                receptionist.setLastName(resultSet.getString("lastName"));
                receptionist.setFirstName(resultSet.getString("firstName"));
                receptionist.setFunction(resultSet.getString("function"));
                receptionist.setId(resultSet.getString("id"));
                receptionist.setEmail(resultSet.getString("email"));
                receptionist.setPhoneNumber(resultSet.getInt("phoneNumber"));
                receptionist.setBirthDate(resultSet.getDate("birthDate"));
            }
            return receptionist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isWorkerRegistered(String workerId) {
        String request = String.format("SELECT * FROM worker WHERE id=\"%s\"", workerId);
        ResultSetMetaData metaData;
        resultSet = executeQueryRequest(request);
        try {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getWorkerPassword(String workerId) {
        String request = String.format("SELECT password FROM worker WHERE id=\"%s\"", workerId);
        String password = "";
        resultSet = executeQueryRequest(request);
        try {
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static Worker getWorker(String workerId) {
        request = String.format("SELECT * FROM receptionist WHERE id=\"%s\"", workerId);
        resultSet = executeQueryRequest(request);
        Receptionist receptionist = new Receptionist();
        try {
            while (resultSet.next()) {
                receptionist.setLastName(resultSet.getString("lastName"));
                receptionist.setFirstName(resultSet.getString("firstName"));
                receptionist.setFunction(resultSet.getString("function"));
                receptionist.setId(resultSet.getString("id"));
                receptionist.setEmail(resultSet.getString("email"));
                receptionist.setPhoneNumber(resultSet.getInt("phoneNumber"));
                receptionist.setBirthDate(resultSet.getDate("birthDate"));
            }
            return receptionist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getWorkerFunction(String workerId) {
        request = String.format("SELECT function FROM worker WHERE id=\"%s\"", workerId);
        resultSet = executeQueryRequest(request);
        String function;
        try {
            if (resultSet.next()) {
                function = resultSet.getString("function");
                return function;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //MANAGE CLIENTS
    public static boolean addClient(Client client) {
        request = String.format("INSERT INTO client (id, firstName, lastName, phoneNumber, email, birthDate) VALUES(\"%s\",\"%s\",\"%s\",%s,\"%s\",\"%s\")",
                client.getId(), client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail(), client.getBirthDate());
        int result = executeUpdateRequest(request);

        return result == 1;
    }

    public static boolean isClientRegistered(String clientId) {
        request = String.format("SELECT * FROM client WHERE id=\"%s\"", clientId);
        resultSet = executeQueryRequest(request);
        try {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeClient(String idClient) {
        request = String.format("DELETE FROM client where id=\"%s\"", idClient);
        int result = executeUpdateRequest(request);
        return result == 1;
    }

    public static ObservableList<Client> getClientsById(String clientId) {
        request = String.format("SELECT * FROM client WHERE id=\"%s\"", clientId);
        return getClients();
    }

    public static ObservableList<Client> getClientsByFirstName(String firstName) {
        request = String.format("SELECT * FROM client WHERE firstName like '%s'", firstName);
        return getClients();
    }

    public static ObservableList<Client> getClientsByLastName(String lastName) {
        request = String.format("SELECT * FROM client WHERE lastName like '%s'", lastName);
        return getClients();
    }

    public static ObservableList<Client> getClientsByEmail(String email) {
        request = String.format("SELECT * FROM client WHERE email like '%s'", email);
        return getClients();
    }

    private static ObservableList<Client> getClients() {
        resultSet = executeQueryRequest(request);
        try {
            ObservableList<Client> clientList = FXCollections.observableArrayList();
            while ( resultSet.next() ){
                Client newClient = new Client();
                newClient.setFirstName( resultSet.getString("firstName") );
                newClient.setLastName( resultSet.getString("lastName") );
                newClient.setId( resultSet.getString("id") );
                newClient.setEmail( resultSet.getString("email") );
                newClient.setPhoneNumber( resultSet.getInt("phoneNumber") );
                newClient.setBirthDate( resultSet.getDate("birthDate") );
                clientList.add(newClient);
            }
            return clientList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //MANAGE ROOMS
    public static boolean isRoomRegistered(String roomID) {
        request = String.format("SELECT * FROM room WHERE id=\"%s\"", roomID);
        resultSet = executeQueryRequest(request);
        try {
            if ( resultSet.next() ){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isRoomAvailable(String roomId) {
        request = String.format("SELECT available FROM room WHERE id=\"%s\"", roomId);
        resultSet = executeQueryRequest(request);
        boolean isAvailable;
        try {
            if (resultSet.next()) {
                isAvailable = resultSet.getBoolean("available");
                return isAvailable;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setRoomAvailability(String roomId, boolean availability) {
        int isAvailable = (availability) ? 1 : 0;
        request = String.format("UPDATE room SET available=\"%s\" WHERE room.id = \"%s\"", isAvailable, roomId);
        int result = executeUpdateRequest(request);
        return result == 1;
    }

    public static ObservableList<Room> getRoomsByTags(double minPrice, double maxPrice, String type, Boolean isAvailable) {
        int isAvailableInt = ( isAvailable )? 1 : 0;
        request = String.format("SELECT * FROM room WHERE price>=%s AND price<=%s AND type=\"%s\" AND available=%s",
                minPrice, maxPrice, type, isAvailableInt);
        resultSet = executeQueryRequest(request);
        String id; double price;
        try {
            ObservableList<Room> roomList = FXCollections.observableArrayList();
            while ( resultSet.next() ){
                id = resultSet.getString("id");
                price = resultSet.getDouble("price");
                Room newRoom = new Room(id, price, type, isAvailable);
                roomList.add(newRoom);
            }
            return roomList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static double getRoomPrice(String roomId) {
        request = String.format("SELECT price FROM room WHERE id=\"%s\"", roomId);
        resultSet = executeQueryRequest(request);
        try {
            if (resultSet.next()){
                return resultSet.getDouble("price");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //MANAGE TRANSACTIONS
    public static boolean addReservation(Transaction reservation) {
        //add reservation
        request = String.format("INSERT INTO transaction (idClient, idReceptionist, idRoom, nature) VALUES(\"%s\",\"%s\",\"%s\",\"%s\")",
                reservation.getIdClient(), reservation.getIdReceptionist(), reservation.getIdRoom(), reservation.getNature());
        int result = executeUpdateRequest(request);

        if (result == 1){
            //update room availability
            request = String.format("UPDATE room SET available=0 WHERE room.id = \"%s\"", reservation.getIdRoom());
            result = executeUpdateRequest(request);
        }

        return result == 1;
    }

    public static boolean addLiberation(Transaction liberation) {
        //add liberation
        request = String.format("INSERT INTO transaction (idClient, idReceptionist, idRoom, nature, price) VALUES(\"%s\",\"%s\",\"%s\",\"%s\",%s)",
                liberation.getIdClient(), liberation.getIdReceptionist(), liberation.getIdRoom(), liberation.getNature(), liberation.getPrice() );
        int result = executeUpdateRequest(request);

        if (result == 1){
            //update room availability
            request = String.format("UPDATE room SET available=1 WHERE room.id = \"%s\"", liberation.getIdRoom());
            result = executeUpdateRequest(request);
        }

        return result == 1;
    }

    public static Transaction getClientLastTransaction(String clientId) {
        request = String.format("SELECT * FROM transaction WHERE " +
                " idTransaction=(SELECT MAX(idTransaction) from transaction WHERE idClient=\"%s\")", clientId, clientId);
        resultSet = executeQueryRequest(request);
        Transaction transaction = new Transaction();
        try {
            if (resultSet.next()){
                transaction.setIdRoom(resultSet.getString("idRoom"));
                transaction.setIdTransaction(resultSet.getInt("idTransaction"));
                //other fields
            }
            return transaction;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static int getReservationDuration(int idTransaction) {
        request = String.format("SELECT DATEDIFF(CURDATE(), date) AS duration FROM transaction WHERE idTransaction=%s", idTransaction);
        resultSet = executeQueryRequest(request);
        try {
            if (resultSet.next()){
                return resultSet.getInt("duration");
            }
            return -2;
        } catch (SQLException e) {
            e.printStackTrace();
            return -3;
        }
    }

    public static ObservableList<Transaction> getTransactionsByDate(Date dateInf, Date dateSup) {
        String dateStart = String.valueOf(dateInf);
        String dateEnd = String.valueOf(dateSup);
        request = String.format("SELECT * FROM transaction WHERE date>='%s' and date <='%s'", dateStart, dateEnd);

        return getTransactions();
    }

    public static ObservableList<Transaction> getTransactionsByPrice(double minPrice, double maxPrice) {
        request = String.format("SELECT * FROM transaction WHERE price<=%s AND price>=%s", maxPrice, minPrice);
        return getTransactions();
    }

    public static ObservableList<Transaction> getTransactionsByClientId(String clientId) {
        request = String.format("SELECT * FROM transaction WHERE idClient=\"%s\"", clientId);
        return getTransactions();
    }

    public static ObservableList<Transaction> getTransactionsByRoomId(String roomId) {
        request = String.format("SELECT * FROM transaction WHERE idRoom=\"%s\"", roomId);
        return getTransactions();
    }

    private static ObservableList<Transaction> getTransactions() {
        resultSet = executeQueryRequest(request);

        try {
            int idTransaction ;
            String idReceptionist;
            String idClient;
            String idRoom;
            String nature;
            double price;
            Date date;
            ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
            while (resultSet.next()){
                idTransaction = resultSet.getInt("idTransaction");
                idReceptionist = resultSet.getString("idReceptionist");
                idClient =resultSet.getString("idClient");
                idRoom = resultSet.getString("idRoom");
                nature = resultSet.getString("nature");
                price = resultSet.getDouble("price");
                date = resultSet.getDate("date");
                Transaction transaction = new Transaction(idTransaction, idClient, idReceptionist, idRoom, nature, date, price);
                transactionList.add(transaction);
            }
            return transactionList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
