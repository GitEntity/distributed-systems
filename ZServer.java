import java.util.Random;

import org.zeromq.ZMQ;

public class ZServer
{

    public static void main(String[] args) throws Exception
    {
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket responder = context.socket(ZMQ.REP);
        responder.bind("tcp://*:5555");

        while (!Thread.currentThread().isInterrupted())
        {
            while(true)
            {
                // Block until a message is received
                byte[] reply = responder.recv(0);
                String replyString = new String(reply, ZMQ.CHARSET);
                int zip = Integer.parseInt(replyString);
                int pop = generatePopulation(zip);
                String response = Integer.toString(pop);
                /*if (replyString.equals("close"))
                {
                    responder.close();
                }*/
                
                //int replyInt = Integer.parseInt(replyString);
                // Print the message
                System.out.println(
                        "Received: [" + replyString + "]");
                
                //String response = getReverse(replyString);
                //String response = getPrimeNumbers(replyInt);
                
                // Send a response
                //String response = "Hello, world!";
                responder.send(response.getBytes(ZMQ.CHARSET), 0);
            }
        }

        responder.close();
        context.term();
    }
    
    public static int generatePopulation(int clientZip)
    {
        Random random = new Random();
        int minZip = 1000;
        int maxZip = 10000;
        int generatedZip;
        int minPop = 100;
        int maxPop = 1000000;
        int generatedPop;
        
        do {
            generatedZip = random.nextInt((maxZip - minZip) + 1) + minZip;
        }while(generatedZip != clientZip);
        
        return generatedPop = random.nextInt((maxPop - minPop) + 1) + minPop;
    }
    
    public static String getReverse(String textRead)
    {
        // reverse the received text message
        String textRev = "";
        for (int a = textRead.length() - 1; a >= 0; a--)
        {
            textRev = textRev + textRead.charAt(a);
        }
        return textRev;
    }

    public static String getPrimeNumbers(int num)
    {
        // Empty String
        String primeNumbers = "";

        for (int i = 1; i <= num; i++)
        {
            int counter = 0;
            for (int b = i; b >= 1; b--)
            {
                if (i % b == 0)
                {
                    counter = counter + 1;
                }
            }
            if (counter == 2)
            {
                // Appended the Prime number to the String
                primeNumbers = primeNumbers + i + " ";
            }
        }
        return primeNumbers;
    }
}