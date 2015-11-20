package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mads
 */
public class Connector {
    
    
    
    private static Connector instance = null;
    
    protected Connector(){
        
    };
    
    public static Connector getInstance(){
        if(instance == null) {
            instance = new Connector();
        }
        return instance;
    };
    
    String DB_URL = "jdbc:mysql://localhost/tek_se_gruppe_5";
    String USER = "gruppe5";
    String PASS = "awesome";
    
    
    Connection conn = null;
    Statement stmt = null;
    
    public String login(String email, String password) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT accID FROM Account where email='" + email + "' AND password='" + password + "';"; 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int count = 0;
        String returnString = "Test";
        while (rs.next()){
            count++;
        }
        rs.first();
        if (count == 1){
            returnString = rs.getString(1); 
        }
        else{
            returnString = "-1";
        }
        return returnString;
    }
    
    public String createUser(String name, int phone, String adress, String email, String password) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = "Test";
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
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return "User created";
    }
    
    public String getStable(int accID) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT name, adress, image FROM Stableinfo WHERE accID='" + accID + "';";
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String returnString = new String();
        Boolean emptyString = false;
        
        while(rs.next()) {
            returnString += rs.getString("name") + ",";
            returnString += rs.getString("adress") + ",";
            returnString += rs.getString("image") + ";";
            emptyString = true;
        }
        if (emptyString == false){
            returnString = "-1";
        }       
        System.out.println(returnString);
        return returnString;
        
    }
    
    public String createStable(int accountId, int fee, String description, String adress, int spaces, String name, int zipCode) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = "Test";
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "INSERT INTO Stableinfo (fee, description, adress, spaces, name, zipCode, accID) VALUES (" + fee + ", '" + description + "', '" + adress + "', " + spaces + ", '" + name + "', " + zipCode + ", " + accountId + ");";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return "Stable created";
    }
    
    public String getHorse(int accID) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT name, image FROM Horse where accID='" + accID + "';"; 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String returnString = new String();
        Boolean emptyString = false;
        
        while(rs.next()) {
            returnString += rs.getString("name") + ",";
            returnString += rs.getString("image") + ";";
            emptyString = true;
        } 
        if (emptyString == false){
            returnString = "-1";
        }
        System.out.println(returnString);
        return returnString;
    }
    
    public String addHorse(int accID, String name) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        try {
            String sql = "INSERT INTO Horse (accID, name) VALUES ('" + accID + "', '" + name + "');";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return "User created";
    }
    
    public String findStable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT * FROM Stableinfo ORDER BY zipCode;"; 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String returnString = new String();
        Boolean emptyString = false;
        int count = 0;
        
        while(rs.next()) {
            returnString += rs.getString("ID") + ",";
            returnString += rs.getString("name") + ",";
            returnString += rs.getString("adress") + ",";
            returnString += rs.getString("zipCode") + ";";
            emptyString = true;
            count++;
        } 
        if (emptyString == false){
            returnString = "-1";
        }
        System.out.println(count + ";" + returnString);
        return count + ";" + returnString;
    }
    
    public String showStable(int ID)throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT * FROM Stableinfo where ID='" + ID + "';"; 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String returnString = new String();
        Boolean emptyString = false;
        int count = 0;
        
        rs.next();
        returnString += rs.getString("fee") + "][";
        returnString += rs.getString("description") + "][";
        returnString += rs.getString("adress") + "][";        
        returnString += rs.getString("image") + "][";
        returnString += rs.getString("rating") + "][";
        returnString += rs.getString("spaces") + "][";
        returnString += rs.getString("name") + "][";
        returnString += rs.getString("zipCode") + "][";
        returnString += rs.getString("accID");
        
        sql = "SELECT ID FROM StableReservation WHERE stableID=" + rs.getString("ID") + ";" ;
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rsTwo = stmt.executeQuery(sql);
        
        while(rsTwo.next()) {
            count++;
            emptyString = true;
        }
        if (emptyString == false){
            returnString = "-1";
        }
        System.out.println(returnString);
        return count + "; " + returnString;
        
    }
        
    public String getReservation(int stableID) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT startDate, endDate FROM StableReservation WHERE stableID=" + stableID + ";" ;
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String returnString = new String();
        Boolean emptyString = false;
        int count = 0;
        
         while(rs.next()) {
            returnString += rs.getString("startDate") + ",";
            returnString += rs.getString("endDate") + ";";
            emptyString = true;
            count++;
        } 
        if (emptyString == false){
            returnString = "-1";
        }
        System.out.println(returnString);
        return count + ";" + returnString;
        
    }
    
    public String reserveStable(String startDate, String endDate, int rentAccID, int stableID) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String returnString = new String();
        try {
            String sql = "INSERT INTO StableReservation (startDate, endDate, rentAccID, stableID) VALUES ('" + startDate + "', '" + endDate + "', " + rentAccID + ", " + stableID + ");";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return "Reservation created";
    }
    
    public String getReviewStable(int accID) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT ID, stableID FROM StableReservation WHERE rentAccID=" + accID + " AND rated=0;" ;
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String returnString = new String();
        Boolean emptyString = false;
        int count = 0;
        
        while(rs.next()) {
            sql = "SELECT ID, name FROM Stableinfo WHERE ID=" + rs.getString("stableID") + ";" ;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rsTwo = stmt.executeQuery(sql);
            
            rsTwo.next();
            returnString += rs.getString("ID") + ", ";
            returnString += rsTwo.getString("ID") + ", ";
            returnString += rsTwo.getString("name") + "; ";
            
            emptyString = true;
            count++;
        } 
        if (emptyString == false){
            returnString = "-1";
        }
        System.out.println(returnString);
        return count + "; " + returnString;
    }
    
    public String reviewStable(int stableID, int rating, int reservationID) throws SQLException, ClassNotFoundException{
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
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        try {
            String sql = "UPDATE StableReservation SET rated=1 WHERE ID=" + reservationID +";";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        try {
            String sql = "UPDATE Stableinfo SET rating=" + finalRating + " WHERE ID=" + stableID +";";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            returnString = "1";
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
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
        sql = "SELECT accID, admin FROM Account ORDER BY accID;" ;
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        ResultSet rsTwo = stmt.executeQuery(sql);
        
        while(rs.next()) {
            rsTwo.next();
            returnString += rsTwo.getString("accID") + ", ";
            returnString += rs.getString("name") + ", ";
            returnString += rsTwo.getString("admin") + "; ";
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
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            returnString = "-1";
        }
        return returnString;
    }
    
    
    
    
}
