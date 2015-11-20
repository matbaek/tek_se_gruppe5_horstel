package main;

import java.net.*;
import java.io.*;
import java.sql.*;

public class Server {
    private static ServerSocket socket;
    
    private static Connector con = Connector.getInstance();

    private static Socket connection;
    private static String command;
    private static String responseStr;

    private static int port = 4242;

    public static void main(String args[]) throws SQLException, ClassNotFoundException  {
        System.out.println("Signal Server is running.");

        try  {
            socket = new ServerSocket(port);

            while (true)  {
                connection = socket.accept();

                InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
                OutputStreamWriter response = new OutputStreamWriter(connection.getOutputStream());
                BufferedReader input = new BufferedReader(inputStream);
                
                command = input.readLine();
                System.out.println("The input is: " + command);
                
                String[] option = command.split("\\]\\[");
                String [] arguments;
                switch(option[0]){
                    case "CreateUser":
                        System.out.println("Create user option engaged!");
                        arguments = option[1].split("; ");
                        responseStr = con.createUser(arguments[0], Integer.parseInt(arguments[1]), arguments[2], arguments[3], arguments[4]);
                        break;
                    case "Login":
                        System.out.println("Login option engaged!");
                        arguments = option[1].split("; ");
                        responseStr = con.login(arguments[0], arguments[1]);
                        break;
                    case "GetStable":
                        System.out.println("Get Stable option engaged: " + option[1]);
                        arguments = option[1].split("; ");
                        responseStr = con.getStable(Integer.parseInt(option[1]));
                        break;
                    case "CreateStable":
                        System.out.println("Create Stable option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.createStable(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]), arguments[2], arguments[3], Integer.parseInt(arguments[4]), arguments[5], Integer.parseInt(arguments[6]));
                        break;
                    case "GetHorse":
                        System.out.println("Get Horse option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.getHorse(Integer.parseInt(arguments[0]));
                        break;
                    case "AddHorse":
                        System.out.println("Add Horse option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.addHorse(Integer.parseInt(arguments[0]), arguments[1]);
                        break;
                    case "FindStable":
                        System.out.println("Get All Stable option engaged");
                        responseStr = con.findStable();
                        break;
                    case "ShowStable":
                        System.out.print("Show Stable option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.showStable(Integer.parseInt(arguments[0]));
                        break;
                    case "GetReservation":
                        System.out.println("Get Reservation option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.getReservation(Integer.parseInt(arguments[0]));                        
                        break;
                    case "CreateReservation":
                        System.out.println("Create Reservation option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.reserveStable(arguments[0], arguments[1], Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]));                        
                        break;
                    case "GetReviewStable":
                        System.out.println("Get Review Stable option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.getReviewStable(Integer.parseInt(arguments[0]));   
                        break;
                    case "ReviewStable":
                        System.out.println("Review Stable option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.reviewStable(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]));   
                        break;
                    case "GetUser":
                        System.out.println("Get User option engaged");
                        responseStr = con.getUser();   
                        break;
                    case "CreateAdmin":
                        System.out.println("Create Admin option engaged");
                        arguments = option[1].split("; ");
                        responseStr = con.createAdmin(Integer.parseInt(arguments[0]));   
                        break;    
                }
                response.write(responseStr);
                response.flush();
                response.close();

                System.out.println("Running");
            }
        } catch (IOException e)  {
            System.out.println("Fail!: " + e.toString());
        }

        System.out.println("Closing...");
    }
}