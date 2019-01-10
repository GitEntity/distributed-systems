
/**
 * Author: Devante Wilson - 100554361
 * Distributed Systems - SOFE 4790U
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.rmi.*;

public class RMIClient
{
    // define instance variables
    public static String myDiagram[] = new String[50];

    // draw diagram of possible trip distance percentage
    public static void drawDiagram() throws InterruptedException
    {
        for (int a = 0; a < myDiagram.length; a++)
        {
            if (myDiagram[a] == null)
            {
                myDiagram[a] = "_";
            }
            System.out.print(myDiagram[a]);
            // simulate diagram drawing on screen
            /*try
            {
                Thread.sleep(100);
            } catch (InterruptedException ex)
            {
                System.err.println("Interrupted Exception." + ex.getMessage());
                System.exit(1);
            }*/
        }
        Arrays.fill(myDiagram, null);
    }

    public static void main(String[] args)
    {
        // check for supplied arguments
        if (args.length != 1)
        {
            System.out.println("Usage: java RMIClient machineName");
            System.exit(0);
        }

        try
        {
            // set reference name
            String name = "//" + args[0] + "/RMIServer";
            // obtain remote object reference
            RemoteInterface ri = (RemoteInterface) Naming.lookup(name);
            // define input stream for user input
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(System.in));
            BufferedOutputStream output = null;
            Scanner scan = null;

            // prompt user for vehicle's fuel economy
            System.out.println("What is the vehicle's fuel economy? (US MPG):");
            String userInput;

            while ((userInput = stdIn.readLine()) != null)
            {
                // parse and store fuel economy
                double clientMPG = Double.parseDouble(userInput);
                // send fuel economy to remote server
                ri.setVehicleMPG(clientMPG);

                // prompt user for amount of fuel in the gas tank
                System.out.println(
                        "How much fuel will be in the gas tank? (Gallons):");
                // read user input
                Double clientFuel = Double.parseDouble(stdIn.readLine());
                // send fuel amount to remote server
                ri.fillUpFuel(clientFuel);

                // prompt user for distance of the trip
                System.out.println("Distance of the trip? (Miles):");
                // read user input
                Double clientDistance = Double.parseDouble(stdIn.readLine());
                // send trip distance to remote server
                ri.takeTrip(clientDistance);

                // report the fuel remaining in the vehicle (from remote server)
                double fuelRemaining = ri.reportVehicleFuel();

                // report the distance that the vehicle can travel (%) (from
                // remote server)
                double tripPercentage = ri.reportTripPercentage(clientDistance);

                // display remaining fuel
                System.out.printf("Fuel left: %.2f gal.%n", fuelRemaining);

                // display possible travel percentage
                System.out.printf("Possible trip percentage: %.2f %%%n",
                        tripPercentage);

                // draw car
                String car = "[o__o]>";
                // define increment for drawing car
                double increment;

                // determine where to draw the car on the diagram
                if (tripPercentage >= 0 && tripPercentage <= 10)
                {
                    increment = 0;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 10 && tripPercentage <= 20)
                {
                    increment = 0.1;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 20 && tripPercentage <= 30)
                {
                    increment = 0.2;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 30 && tripPercentage <= 40)
                {
                    increment = 0.3;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 40 && tripPercentage <= 50)
                {
                    increment = 0.4;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 50 && tripPercentage <= 60)
                {
                    increment = 0.5;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 60 && tripPercentage <= 70)
                {
                    increment = 0.6;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 70 && tripPercentage <= 80)
                {
                    increment = 0.7;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 80 && tripPercentage <= 90)
                {
                    increment = 0.8;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (tripPercentage > 90 && tripPercentage <= 100)
                {
                    increment = 0.9;
                    myDiagram[(int) (myDiagram.length * increment)] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else
                { // tripPercentage > 100
                    increment = 1;
                    myDiagram[(int) (myDiagram.length * increment) - 1] = car;
                    try
                    {
                        drawDiagram();
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                // prompt user for file writing
                System.out.println(
                        "\nWould you like to download the results? (y/n)");
                // define scanner object
                scan = new Scanner(System.in);
                // receive character input
                char userVerdict = scan.next().trim().charAt(0);
                // set flag
                boolean flag;

                do
                {
                    if (userVerdict == 'y' || userVerdict == 'Y')
                    {
                        // set flag
                        flag = false;
                        // write to the file (remote method)
                        ri.generateFile();
                        // notify user that file has been written to
                        System.out.println(
                                "RESULTS DOWNLOADED: " + ri.getFileName());
                        // download the file to the client
                        byte[] filedata = ri.downloadFile(ri.getFileName());
                        File file = new File(ri.getFileName());
                        output = new
                        BufferedOutputStream(new FileOutputStream(file.getName()));
                        output.write(filedata,0,filedata.length);
                        output.flush();
                    } else if (userVerdict == 'n' || userVerdict == 'N')
                    {
                        // set flag
                        flag = false;
                    } else
                    {
                        // set flag
                        flag = true;
                        // prompt user to retry verdict
                        System.out.println("Please retry (y/n)");
                        userVerdict = scan.next().trim().charAt(0);
                    }
                } while (flag == true);

                // prompt user for vehicle's fuel economy
                System.out.println(
                        "What is the vehicle's fuel economy? (US MPG):");
            }
            // close streams
            scan.close();
            output.close();
        } catch (Exception e)
        {
            System.err.println("RMIClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
