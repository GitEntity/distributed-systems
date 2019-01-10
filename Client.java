/**
 * Author: Devante Wilson - 100554361
 * Distributed Systems - SOFE 4790U
 */

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
 
public class Client {
	// define instance variables
	public static String myDiagram[] = new String[50];
	
	// draw diagram of possible trip distance percentage
	public static void drawDiagram() throws InterruptedException {
		for(int a = 0; a < myDiagram.length; a++) {
			if (myDiagram[a] == null)
			{
				myDiagram[a] = "_";
			}
			System.out.print(myDiagram[a]);
			// simulate diagram drawing on screen
			/*try {
			Thread.sleep(250);
			}catch(InterruptedException ex) {
				System.err.println("Interrupted Exception." + ex.getMessage());
				System.exit(1);
			}*/
		}
		Arrays.fill(myDiagram, null);
	}
	
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 123;
        
        try {
            Socket mySocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(mySocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(mySocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
            
            // prompt user for vehicle's fuel economy
            System.out.println("What is the vehicle's fuel economy? (US MPG):");
            String userInput;
            
            while ((userInput = stdIn.readLine()) != null) {
            	// send fuel economy to server
            	out.println(userInput);
            	
            	// prompt user for amount of fuel in the gas tank
            	System.out.println("How much fuel will be in the gas tank? (Gallons):");
            	// read user input
            	String clientFuel = stdIn.readLine();
            	// send fuel amount to server
            	out.println(clientFuel);
            	
            	// prompt user for distance of the trip
            	System.out.println("Distance of the trip? (Miles):");
            	// read user input
            	String clientDistance = stdIn.readLine();
            	// send trip distance to server
            	out.println(clientDistance);
            	
            	// accept response from server
            	String serverResp = in.readLine();
            	String serverResp2 = in.readLine();
            	
            	// parse string into double
            	double fuelLeft = Double.parseDouble(serverResp);
            	// display remaining fuel
            	System.out.printf("Fuel left: %.2f gal.%n", fuelLeft);
            	
            	//parse string into double
            	double tripPercentage = Double.parseDouble(serverResp2);
            	
            	// display possible travel percentage
            	System.out.printf("Possible trip percentage: %.2f %%%n", tripPercentage);
            	
            	// draw car
            	String car = "[o__o]>";
            	// define increment for drawing car
            	double increment;
            	
            	// determine where to draw the car on the diagram
        		if (tripPercentage >= 0 && tripPercentage <= 10) {
        			increment = 0;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 10 && tripPercentage <= 20) {
        			increment = 0.1;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 20 && tripPercentage <= 30) {
        			increment = 0.2;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 30 && tripPercentage <= 40) {
        			increment = 0.3;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 40 && tripPercentage <= 50) {
        			increment = 0.4;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 50 && tripPercentage <= 60) {
        			increment = 0.5;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 60 && tripPercentage <= 70) {
        			increment = 0.6;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 70 && tripPercentage <= 80) {
        			increment = 0.7;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 80 && tripPercentage <= 90) {
        			increment = 0.8;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else if (tripPercentage > 90 && tripPercentage <= 100) {
        			increment = 0.9;
	        		myDiagram[(int) (myDiagram.length * increment)] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else { // tripPercentage > 100
        			increment = 1;
	        		myDiagram[(int) (myDiagram.length * increment) - 1] = car;
	        		try {
						drawDiagram();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}	
            	
            	// prompt user for file writing 
            	System.out.println("\nWould you like to save the results? (y/n)");
            	// define scanner object
            	Scanner scan = new Scanner(System.in);
            	// receive character input
            	char userVerdict = scan.next().trim().charAt(0);
            	// set flag
            	boolean flag;
            	
            	do {      	
	            	if (userVerdict == 'y' || userVerdict == 'Y') {
	            		// set flag
	            		flag = false;
	            		// write results to a text file
	            		FileWriter fw = new FileWriter("Gas Mileage Results.txt", true);
	              	    BufferedWriter bw = new BufferedWriter(fw);
	            		PrintWriter writer = 
	            				new PrintWriter(bw);
	            		DateTimeFormatter dtf = 
	            				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	            		LocalDateTime time = LocalDateTime.now();
	            		writer.println("---- Results: " + 
	            				mySocket.getRemoteSocketAddress() + 
	            				" @ " + dtf.format(time) + " ----");
	            		writer.println("MPG:           " + userInput + " mpg");
	            		writer.println("Starting Fuel: " + clientFuel + " gal.");
	            		writer.println("Trip Distance: " + clientDistance + " mi.");
	            		writer.printf("Fuel Left:     %.2f gal.%n", fuelLeft);
	            		writer.printf("Trip Percentage: %.2f %%%n", tripPercentage);
	            		writer.close();
	            		System.out.println("RESULTS SAVED.");
	            	}
	            	else if (userVerdict == 'n' || userVerdict == 'N') {
	            		// set flag
	            		flag = false;
	            	}
	            	else {
	            		// set flag
	            		flag = true;
	            		// prompt user to retry verdict
	            		System.out.println("Please retry (y/n)");
	                	userVerdict = scan.next().trim().charAt(0);
	                }
            	}
            	while (flag == true);
            	// prompt user for vehicle's fuel economy
                System.out.println("What is the vehicle's fuel economy? (US MPG):");
            }
            } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}