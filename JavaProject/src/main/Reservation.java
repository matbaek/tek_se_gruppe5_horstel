/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Darek
 */
public class Reservation {
    private int ID;
    private String startDate;
    private String endDate;
    private int rentAccID;
    private int stableID;
    
    
    public Reservation(int ID, String startDate, String endDate, int rentAccID, int stableID) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentAccID = rentAccID;
        this.stableID = stableID;
    }

    public int getID() {
        return ID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getRentAccID() {
        return rentAccID;
    }

    public int getStableID() {
        return stableID;
    }
}
