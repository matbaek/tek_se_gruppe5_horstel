/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;

/**
 *
 * @author Darek
 */
public class Stable implements Serializable {
    private int ID;
    private int fee;
    private String description;
    private String adress;
    private String image;
    private int rating;
    private int spaces;
    private String name;
    private int zipCode;
    private int accID;
    
    
    public Stable (int ID, int fee, String description, String adress, String image, int rating, int spaces, String name, int zipCode, int accID){
        this.ID = ID;
        this.fee = fee;
        this.description = description;
        this.adress = adress;
        this.image = image;
        this.rating = rating;
        this.spaces = spaces;
        this.name = name;
        this.zipCode = zipCode;
        this.accID = accID;
    }

    public int getID() {
        return ID;
    }

    public int getFee() {
        return fee;
    }

    public String getDescription() {
        return description;
    }

    public String getAdress() {
        return adress;
    }
    
    public String getImage(){
        return image;
    }

    public int getRating() {
        return rating;
    }

    public int getSpaces() {
        return spaces;
    }

    public String getName() {
        return name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getAccID() {
        return accID;
    }
}
    

