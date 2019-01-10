
/**
 * Author: Devante Wilson - 100554361
 * Distributed Systems - SOFE 4790U
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

interface RemoteInterface extends Remote
{
    // set the vehicle's fuel economy
    void setVehicleMPG(double fuelEcon) throws RemoteException;

    // add fuel to the vehicle
    void fillUpFuel(double fuel) throws RemoteException;

    // compute remaining fuel after a trip
    void takeTrip(double totalDistance) throws RemoteException;

    // report the amount of fuel remaining in the vehicle
    double reportVehicleFuel() throws RemoteException;

    // report the distance that the vehicle can travel (%)
    double reportTripPercentage(double totalDistance) throws RemoteException;
    
    // write to a text file to log results
    void generateFile() throws RemoteException;
    
    // get name of text file that logs results
    String getFileName() throws RemoteException;
    
    // download text file with results
    byte[] downloadFile(String fileName) throws RemoteException;
}
