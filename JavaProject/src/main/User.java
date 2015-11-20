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
public class User {
    
    private String name;
    private int phone;
    private int ID;
    private String adress;
    private String email;
    private String password;
    private int admin;
    private int superUser;
    
    
    public User (int ID, String name, int phone, String adress, String email, String password, int admin, int superUser){
        this.name = name;
        this.phone = phone;
        this.ID = ID;
        this.adress = adress;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.superUser = superUser;
             
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getPhone(){
        return this.phone;
    }
    
    public String getAdress(){
        return this.adress;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public int getAdmin(){
        return this.admin;
    }
    
    public int getSuperUser(){
        return this.superUser;
    }
}
