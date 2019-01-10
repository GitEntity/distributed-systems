
/**
 * Author: Devante Wilson - 100554361
 * Distributed Systems - SOFE 4790U
 */

import java.rmi.*;

public class RMIServer
{
    @SuppressWarnings("deprecation")
    public static void main(String args[])
    {
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new RMISecurityManager());
        }
        try
        {
            RemoteInterface ri = new RemoteInterfaceImpl();
            Naming.rebind("//127.0.0.1/RMIServer", ri);
        } catch (Exception e)
        {
            System.out.println("RMIServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
