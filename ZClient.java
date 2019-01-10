import java.util.Scanner;

import org.zeromq.ZMQ;

public class ZClient {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);


        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://localhost:5555");
        Scanner scan = new Scanner(System.in);
        String keyboardIn = scan.next();
        System.out.println("Enter a 4 digit zip");
        //while((keyboardIn = scan.next()) != null)
        while(true)
        {
        
            /*if (keyboardIn.equals("close"))
            {
                requester.close();
            }*/
            //int num = 7;
            //byte[] result = new byte[100];
            //System.arraycopy(string.getBytes(), 0, result, 100 - string.length(), string.length());
            //requester.send(Integer.toString(num));
            requester.send(keyboardIn);
            
            // block until reply is received
            byte[] reply = requester.recv(0);
            
            String replyString = new String(reply, ZMQ.CHARSET);
            
            // Print the message
            System.out.println(
                "Received: Population [" + replyString + "]"
            );
            
            //System.out.println("Reversed: [" + getReverse(replyString) + "]");
            
            System.out.println("Enter a 4 digit zip");
        }
            
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
}