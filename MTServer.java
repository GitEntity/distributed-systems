/**
 * Author: Devante Wilson - 100554361
 * Distributed Systems - SOFE 4790U
 */

import java.net.*;
import java.io.*;

public class MTServer {
    public static void main(String[] args) {
        int portNumber = 123;
        MTServer myServer = new MTServer();
        myServer.startServer(portNumber);
     }
    
    // start a new socket connection
    public void startServer(int portNumber) {
    	try {
    		ServerSocket serverSocket = new ServerSocket(portNumber);
    		while(true) {
    			Socket client = serverSocket.accept();
                Connection cc = new Connection(client);
           }
        } 
    	catch(Exception e) {
    		System.out.println("Exception: "+e);
        }
    }
}

class Connection extends Thread {
	// define instance variables
    Socket client;
    PrintWriter out;
    BufferedReader in;
    private double mpg, newFuel, gallons = 0;

    // constructor
    public Connection(Socket s) {
       client = s;
       
       try {
           out = new PrintWriter(client.getOutputStream(), true);                   
           in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
       } catch (IOException e) {
           try {
             client.close();
           } catch (IOException ex) {
             System.out.println("Error while getting socket streams.."+ex);
           }
           return;
       }
        this.start(); // Thread starts here...this start() will call run()
    }
    
    // set the vehicle's fuel economy
    public void setVehicleMPG(double fuelEcon){ 
    	mpg = fuelEcon; 
    }
    
    // add fuel to the vehicle
	public void fillUpFuel(double fuel){ 
		newFuel = fuel; 
	}
	
	// compute remaining fuel after a trip
	public void takeTrip(double totalDistance){ 
		gallons = newFuel - (totalDistance/mpg); 
	}
	
	// report the amount of fuel remaining in the vehicle
	public double reportVehicleFuel() { 
		return gallons; 
	}
	
	// report the distance that the vehicle can travel (%)
	public double reportTripPercentage(double totalDistance) {
		double distanceTravelled = mpg * newFuel;
		double tripPercentage = (distanceTravelled/totalDistance) * 100;
		return tripPercentage;
	}
	
    public void run() {
    	try {
    		String clientInput;
    		while ((clientInput = in.readLine()) != null) {
	            System.out.println("Received from: "+ client.getRemoteSocketAddress());
	         
	            // parse and store fuel economy
    			double clientMPG = Double.parseDouble(clientInput);
    			System.out.println("MPG: " + clientMPG);
    			// parse and store fuel amount
    			double clientFuel = Double.parseDouble(in.readLine());
    			System.out.println("Fuel: " + clientFuel);
    			// parse and store trip distance
    			double clientDistance = Double.parseDouble(in.readLine());
    			System.out.println("Distance: " + clientDistance);
    			
    			// invoke necessary functions
    			setVehicleMPG(clientMPG);
    			fillUpFuel(clientFuel);
    			takeTrip(clientDistance);
    			reportTripPercentage(clientDistance);
    			
    			// report the fuel remaining in the vehicle
    			double fuelRemaining = reportVehicleFuel();
    			
    			// report the distance that the vehicle can travel (%)
    			double tripPercentage = reportTripPercentage(clientDistance);
    			
    			// send results back to client
    			out.println(fuelRemaining);
    			out.println(tripPercentage);
    			
    		}
	     client.close();
    	 } catch (IOException e) {
	       System.out.println("Exception caught..." + e.getMessage());
	       System.out.println(e.getMessage());
    	 }
    }
}