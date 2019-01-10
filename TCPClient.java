import java.io.*; 
import java.io.DataInputStream; 
import java.net.*; 

class TCPClient 
{ 
	@SuppressWarnings("deprecation")
	public static void main(String arg[])throws Exception 
    { 
        try
        {
	        Socket client=new Socket("127.0.0.1",7896); 
	        DataInputStream r=new DataInputStream(client.getInputStream()); 
	        PrintStream w=new PrintStream(client.getOutputStream()); 
	        DataInputStream in=new DataInputStream(System.in); 
	        
	        // prompt user and read input from the input stream
	        System.out.println("Please enter a number.");
	        String numStr = in.readLine();
	        int num = Integer.parseInt(numStr);
	        
	        // send the message to the server
	        w.write(num);
	        
	        // receive the message from the server and display it
	        int numAvg = r.read();
	        System.out.println("Computed Average: " + numAvg);
		}
        catch (UnknownHostException e)
        {
			System.out.println("Sock:"+e.getMessage()); 
		}
        catch (EOFException e)
        {
			System.out.println("EOF:"+e.getMessage());
		}
        catch (IOException e)
        {
			System.out.println("IO:"+e.getMessage());
		}
    } 
}
