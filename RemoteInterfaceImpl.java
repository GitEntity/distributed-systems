
/**
 * Author: Devante Wilson - 100554361
 * Distributed Systems - SOFE 4790U
 */

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

@SuppressWarnings("serial")
public class RemoteInterfaceImpl extends UnicastRemoteObject
        implements RemoteInterface
{
    private double mpg, newFuel, gallons, distance, tripPercentage;
    private String fileName;

    protected RemoteInterfaceImpl() throws RemoteException
    {
        super();
        mpg = 0;
        newFuel = 0;
        gallons = 0;
    }

    // set the vehicle's fuel economy
    public void setVehicleMPG(double fuelEcon)
    {
        mpg = fuelEcon;
    }

    // add fuel to the vehicle
    public void fillUpFuel(double fuel)
    {
        newFuel = fuel;
    }

    // compute remaining fuel after a trip
    public void takeTrip(double totalDistance)
    {
        gallons = newFuel - (totalDistance / mpg);
        // set distance (used for file writing method)
        distance = totalDistance;
    }

    // report the amount of fuel remaining in the vehicle
    public double reportVehicleFuel()
    {
        return gallons;
    }

    // report the distance that the vehicle can travel (%)
    public double reportTripPercentage(double totalDistance)
    {
        double distanceTravelled = mpg * newFuel;
        double tripPercentage = (distanceTravelled / totalDistance) * 100;
        // set trip percentage (used in the file writing)
        this.tripPercentage = tripPercentage;
        return tripPercentage;
    }

    // write to a text file to log results
    public void generateFile()
    {
        String fileName = "Gas Mileage Results.txt";
        this.fileName = fileName;
        // write results to a text file
        FileWriter fw = null;
        try
        {
            fw = new FileWriter(fileName, true);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter writer = new PrintWriter(bw);
        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        try
        {
            writer.println("---- Results: " + RemoteServer.getClientHost()
                    + " @ " + dtf.format(time) + " ----");
        } catch (ServerNotActiveException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        writer.println("MPG:           " + mpg + " mpg");
        writer.println("Starting Fuel: " + newFuel + " gal.");
        writer.println("Trip Distance: " + distance + " mi.");
        writer.printf("Fuel Left:     %.2f gal.%n", gallons);
        writer.printf("Trip Percentage: %.2f %%%n", tripPercentage);
        writer.close();
    }
    
    // get name of text file that logs results
    public String getFileName()
    {
        return fileName;
    }
    
    // download text file with results
    public byte[] downloadFile(String fileName)
    {
        try
        {
            File file = new File(fileName);
            byte buffer[] = new byte[(int) file.length()];
            BufferedInputStream input = new BufferedInputStream(
                    new FileInputStream(fileName));
            input.read(buffer, 0, buffer.length);
            input.close();
            return (buffer);
        } catch (Exception e)
        {
            System.out.println("RemoteInterfaceImpl: " + e.getMessage());
            e.printStackTrace();
            return (null);
        }
    }
}
