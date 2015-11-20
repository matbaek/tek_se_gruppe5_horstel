/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darek
 */
public class DBController {
    
    private static DBController instance = null;

public static DBController getInstance(){
        if(instance == null) {
            instance = new DBController();
        }
        return instance;
    };
    
    static String DB_URL = "jdbc:mysql://localhost/tek_se_gruppe_5";
    static String USER = "gruppe5";
    static String PASS = "awesome";
    
    
    static Connection conn = null;
    static Statement stmt = null;
    
    public static String createUser(User user) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = "-1";
        String name = user.getName();
        int phone = user.getPhone();
        String adress = user.getAdress();
        String email = user.getEmail();
        String password = user.getPassword();
        
        try {
            String sql = "INSERT INTO Account (email, password) VALUES ('" + email + "', '" + password + "');";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Contactinfo (name, phoneNr, adress) VALUES ('" + name + "', " + phone + ", '" + adress + "');";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        
        return returnString;
    }
    
    public static List login(String email, String password) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = "-1";
        int accID = 0;
        int admin = 0;
        int superUser = 0;
        List<User> list = new ArrayList<User>();
        
        try {
            String sql = "SELECT accID, admin, superUser FROM Account where email='" + email + "' AND password='" + password + "';"; 
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()){
                accID = rs.getInt("accID"); 
                admin= rs.getInt("admin"); 
                superUser = rs.getInt("superUser"); 
                User user = new User(accID, "", 0, "", "", "", admin, superUser);
                list.add(user);
            } else {
                returnString = "-1";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static List getStable(int accID) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        String name = "";
        String adress = "";
        String image = "";
        List<Stable> list = new ArrayList<Stable>();
        
        try {
            String sql = "SELECT name, adress, image FROM Stableinfo WHERE accID='" + accID + "';";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                name = rs.getString("name");
                adress = rs.getString("adress");
                image = rs.getString("image");
                Stable stable = new Stable(0, 0, "", adress, image, 0, 0, name, 0, 0);
                list.add(stable);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static String createStable(Stable stable) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        int fee = stable.getFee();
        String adress = stable.getAdress();
        String description = stable.getDescription();
        int spaces = stable.getSpaces();
        String name = stable.getName();
        int zipCode = stable.getZipCode();
        int accountID = stable.getAccID();
        
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "INSERT INTO Stableinfo (fee, description, adress, spaces, name, zipCode, accID) VALUES (" + fee + ", '" + description + "', '" + adress + "', " + spaces + ", '" + name + "', " + zipCode + ", " + accountID + ");";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        
        return returnString;
    }
    
    public static List getHorse(int accID) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        String name = "";
        String image = "";
        List<Horse> list = new ArrayList<Horse>();
        
        try {
            String sql = "SELECT name, image FROM Horse where accID='" + accID + "';"; 
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                name = rs.getString("name");
                image = rs.getString("image");
                Horse horse = new Horse(0, accID, "", "");
                list.add(horse);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static String addHorse(Horse horse) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        
        int accID = horse.getUserID();
        String name = horse.getName();
        
        try {
            String sql = "INSERT INTO Horse (accID, name) VALUES ('" + accID + "', '" + name + "');";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return returnString;
    }
    
    public static List findStable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        Boolean emptyString = false;
        int ID = 0;
        String name = "";
        String adress = "";
        int zipCode = 0;
        List<Stable> list = new ArrayList<Stable>();
        
        try {
            String sql = "SELECT * FROM Stableinfo ORDER BY zipCode;"; 
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                ID = rs.getInt("ID");
                name = rs.getString("name");
                adress = rs.getString("adress");
                zipCode = rs.getInt("zipCode");
                emptyString = true;
                Stable stable = new Stable(ID, 0, "", adress, "", 0, 0, name, zipCode, 0);
                list.add(stable);
            } 
            if (emptyString == false){
                returnString = "-1";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static List showStable(int ID)throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        int fee = 0;
        String description = new String();
        String adress = new String();
        String image = new String();
        int rating = 0;
        int spaces = 0;
        String name = new String();
        int zipCode = 0;
        int accID = 0;
        List<Stable> list = new ArrayList<Stable>();
        
        try {
            String sql = "SELECT * FROM Stableinfo where ID='" + ID + "';"; 
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            rs.next();
            fee = rs.getInt("fee");
            description = rs.getString("description");
            adress = rs.getString("adress");        
            image = rs.getString("image");
            rating = rs.getInt("rating");
            spaces = rs.getInt("spaces");
            name = rs.getString("name");
            zipCode = rs.getInt("zipCode");
            accID = rs.getInt("accID");
            
            Stable stable = new Stable(0, fee, description, adress, image, rating, spaces, name, zipCode, accID);
            list.add(stable);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }
        
    public static int countStableReservation(int stableID) {
        int count = 0;
        
        try {
            String sql = "SELECT ID FROM StableReservation WHERE stableID=" + stableID + " AND rated=1;" ;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    public static List getReservation(int stableID) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String startDate = new String();
        String endDate = new String();
        List<Reservation> list = new ArrayList<Reservation>();
        
        try {
            String sql = "SELECT startDate, endDate FROM StableReservation WHERE stableID=" + stableID + ";" ;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                startDate = rs.getString("startDate");
                endDate = rs.getString("endDate");
                Reservation reservation = new Reservation(0, startDate, endDate, 0, stableID);
                list.add(reservation);   
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static String createReservation(Reservation reservation) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        String startDate =  reservation.getStartDate();
        String endDate =  reservation.getEndDate();
        int rentAccID =  reservation.getRentAccID();
        int stableID =  reservation.getStableID();
        
        try {
            String sql = "INSERT INTO StableReservation (startDate, endDate, rentAccID, stableID) VALUES ('" + startDate + "', '" + endDate + "', " + rentAccID + ", " + stableID + ");";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return returnString;
    }
    
    public static List getReviewStable(int accID) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        int ID = 0;
        String startDate = new String();
        String endDate = new String();
        int stableID = 0;
        List<Reservation> list = new ArrayList<Reservation>();
        
        try {
            String sql = "SELECT * FROM StableReservation WHERE rentAccID=" + accID + " AND rated=0;" ;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                ID = rs.getInt("ID");
                startDate = rs.getString("startDate");
                endDate = rs.getString("endDate");
                stableID = rs.getInt("stableID");
                Reservation reservation = new Reservation(ID, startDate, endDate, accID, stableID);
                list.add(reservation);   
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static String getStableName(int ID) {
        String name = new String();
        
        try {
            String sql = "SELECT name FROM Stableinfo WHERE ID=" + ID + ";" ;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            rs.next();
            name = rs.getString("name");
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return name;
    }
    
    public static String reviewStable(int stableID, int rating, int reservationID) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        int finalRating = 0;
        try {
            String sql = "SELECT rating FROM Stableinfo WHERE ID=" + stableID + ";";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            finalRating = rs.getInt("rating")+rating;
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        try {
            String sql = "UPDATE StableReservation SET rated=1 WHERE ID=" + reservationID +";";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        try {
            String sql = "UPDATE Stableinfo SET rating=" + finalRating + " WHERE ID=" + stableID +";";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        
        return returnString;
    }
    
    
    public String getUser() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT name FROM Contactinfo ORDER BY accID"; 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String returnString = new String();
        Boolean emptyString = false;
        int count = 0;
        sql = "SELECT accID, active, admin FROM Account ORDER BY accID;" ;
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rsTwo = stmt.executeQuery(sql);
        
        while(rs.next()) {
            rsTwo.next();
            returnString += rsTwo.getString("accID") + ", ";
            returnString += rs.getString("name") + ", ";
            returnString += rsTwo.getString("admin") + ", ";
            returnString += rsTwo.getString("active") + "; ";
            emptyString = true;
            count++;
        }
        if (emptyString == false){
            returnString = "-1";
        }
        
        System.out.println(returnString);
        return count + "; " + returnString;
    }
    
    public String createAdmin(int accID) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        try {
            String sql = "UPDATE Account SET admin=1 WHERE accID=" + accID +";";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return returnString;
    }
    
    public String alterUser(int accID, int active) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        try {
            String sql = "UPDATE Account SET active= "+ active +" WHERE accID=" + accID +";";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return returnString;
        
        
    }
    
}
