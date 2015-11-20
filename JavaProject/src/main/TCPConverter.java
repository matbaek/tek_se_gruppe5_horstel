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
public class TCPConverter {
    
    public static DBController getInstance(){
         return Controller.getInstance();
    }; 
    
    public static String createUser(int ID, String name, int phone, String adress, String email, String password) throws ClassNotFoundException{
        User user = new User(ID, name, phone, adress, email, password, 0, 0);
        return Controller.createUser(user);
    }
    
    public static String login(String email, String password) throws ClassNotFoundException{
        List<User> list = Controller.login(email, password);
        String returnString = "";
        for(int i = 0; i<list.size();i++){
            User user = list.get(i);
            int ID = user.getID();
            int admin = user.getAdmin();
            int superUser = user.getSuperUser();
            returnString += ID + ", ";
            returnString += admin + ", ";
            returnString += superUser + ";";
        }
        return returnString;
    }
    
    public static String getStable(int accID) throws SQLException, ClassNotFoundException{
        List<Stable> list = Controller.getStable(accID);
        String returnString = "";
        for(int i = 0; i<list.size();i++){
            Stable stable = list.get(i);
            String name = stable.getName();
            String adress = stable.getAdress();
            String image = stable.getAdress();
            returnString += name + ",";
            returnString += adress + ",";
            returnString += image + ";";
        }
        return list.size() + ";" + returnString;
    }
        
    public static String createStable(int ID, int fee, String description, String adress, String image, int rating, int spaces, String name, int zipCode, int accID) throws ClassNotFoundException{
        Stable stable = new Stable(ID, fee, description, adress, image, rating, spaces, name, zipCode, accID);
        return Controller.createStable(stable);
    }
    
    public static String getHorse(int accID) throws SQLException, ClassNotFoundException{
        List<Horse> list = Controller.getHorse(accID);
        String returnString = "";
        for(int i = 0; i<list.size();i++){
            Horse horse = list.get(i);
            String name = horse.getName();
            String image = horse.getImage();
        }
        return list.size() + ";" + returnString;  
    }
    
    public static String addHorse(int accID, String name) throws ClassNotFoundException{
        Horse horse = new Horse(0, accID, name, "");
        return Controller.addHorse(horse);
    }
    
    public static String findStable() throws ClassNotFoundException, SQLException {
        List<Stable> list = Controller.findStable();
        String returnString = "";
        for(int i = 0; i<list.size();i++){
            Stable stable = list.get(i);
            returnString += stable.getID() + ", ";
            returnString += stable.getName() + ", ";
            returnString += stable.getAdress() + ", ";
            returnString += stable.getZipCode() + ";";
        }
        System.out.println(list.size() + ";" + returnString);
        return list.size() + ";" + returnString; 
    }
    
    public static String showStable(int ID) throws ClassNotFoundException, SQLException {
        List<Stable> list = Controller.showStable(ID);
        String returnString = "";
        for(int i = 0; i<list.size();i++){
            Stable stable = list.get(i);
            returnString += stable.getFee() + "][";
            returnString += stable.getDescription() + "][";
            returnString += stable.getAdress() + "][";
            returnString += stable.getImage() + "][";
            returnString += stable.getRating() + "][";
            returnString += stable.getSpaces() + "][";
            returnString += stable.getName() + "][";
            returnString += stable.getZipCode() + "][";
            returnString += stable.getAccID();
        }
        int count = Controller.countStableReservation(ID);
        
        return count + "; " + returnString;
    }
    
    public static String getReservation(int stableID) throws ClassNotFoundException, SQLException{
        List<Reservation> list = Controller.getReservation(stableID);
        String returnString = "";
        for(int i = 0; i<list.size();i++){
            Reservation reservation = list.get(i);
            returnString += reservation.getStartDate() + ", ";
            returnString += reservation.getEndDate() + ";";
        }
        return list.size() + ";" + returnString;
    }
    
    public static String createReservation(String startDate, String endDate, int rentAccID, int stableID) throws ClassNotFoundException, SQLException{
        Reservation reservation = new Reservation(0, startDate, endDate, rentAccID, stableID);
        return Controller.createReservation(reservation);
    }
    
    public static String getReviewStable(int accID) throws ClassNotFoundException {
        List<Reservation> list = Controller.getReviewStable(accID);
        String returnString = new String();
        for(int i = 0; i<list.size();i++){
            Reservation reservation = list.get(i);
            returnString += reservation.getStableID()+ ", ";
            returnString += reservation.getID() + ", ";
            returnString += reservation.getStartDate() + ", ";
            returnString += reservation.getEndDate() + ", ";
            returnString += Controller.getStableName(reservation.getStableID()) + "; ";
        }
        return list.size() + "; " + returnString;
    }
    
    public static String reviewStable(int stableID, int rating, int reservationID) throws SQLException, ClassNotFoundException {
        return Controller.reviewStable(stableID, rating, reservationID);
    }
}
