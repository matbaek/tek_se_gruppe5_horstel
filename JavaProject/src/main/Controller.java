/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Darek
 */
public class Controller {
    
    public static DBController getInstance(){
        return DBController.getInstance();
    };
    
    public static String createUser(User user) throws ClassNotFoundException{
        return DBController.createUser(user);
    }
    
    public static List login(String email, String password) throws ClassNotFoundException{
        return DBController.login(email, password);
    }
    
    public static List getStable(int accID) throws SQLException, ClassNotFoundException{
        return DBController.getStable(accID);  
    }
    
    public static String createStable(Stable stable) throws ClassNotFoundException{
        return DBController.createStable(stable);
    }
    
    public static List getHorse(int accID) throws SQLException, ClassNotFoundException{
        return DBController.getHorse(accID);
    }
    
    public static String addHorse(Horse horse) throws ClassNotFoundException{
        return DBController.addHorse(horse);
    }
    
    public static List findStable() throws ClassNotFoundException, SQLException {
        return DBController.findStable();
    }
    
    public static List showStable(int ID) throws ClassNotFoundException, SQLException {
        return DBController.showStable(ID);
    }
    
    public static int countStableReservation(int stableID) {
        return DBController.countStableReservation(stableID);
    }
    
    public static List getReservation(int stableID) throws ClassNotFoundException, SQLException{
        return DBController.getReservation(stableID);
    }
    
    public static String createReservation(Reservation reservation) throws ClassNotFoundException, SQLException{
         return DBController.createReservation(reservation);
    }
    
    public static List getReviewStable(int accID) throws ClassNotFoundException {
        return DBController.getReviewStable(accID);
    }
    
    public static String getStableName(int ID) {
        return DBController.getStableName(ID);
    }
    
    public static String reviewStable(int stableID, int rating, int reservationID) throws SQLException, ClassNotFoundException {
        return DBController.reviewStable(stableID, rating, reservationID);
    }
    
    
    
}
