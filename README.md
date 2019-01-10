# sofe4790

###### Author 
Devante Wilson - 100554361

### Instructions
Here are the instructions for running my application using Java RMI (Remote Method Invocation)

Navigate to the folder that contains the .java files.

**1.** Run the RMI registry in a CMD window with the command "rmiregistry".

**2.** Make sure there is a copy of all the .class files before running the Server.
If there are none, generate them by compiling both the RemoteInterface.java and RemoteInterfaceImpl.java

**3.** Compile the server (MTServer.java) in a CMD window with a policy declared. 
(Ex. java -Djava.security.policy=policy.txt RMIServer).Run the server in the same window.

**4.** Compile the client (Client.java) in another CMD window. Run the client in this window (java RMIClient machineName).

Follow the prompts on the client. The program will run until you decide to terminate (ctrl + c).
 
If the option is chosen, a log file will be downloaded in the client folder with the results appended. "Gas Mileage Results.txt" 

### Description
Provides a client with useful fuel mileage information pertaining to their vehicle and a possible trip they may take. 

The application will ask the user about the fuel economy of their vehicle, along with the distance of the trip, and the amount of fuel in the vehicle. With this information, the server will determine how much fuel will be left inside the vehicle, and the server will also determine how far the vehicle can possibly travel to the destination. Additionally, a small diagram will be shown, demonstrating how far the vehicle will get it to the destination.
