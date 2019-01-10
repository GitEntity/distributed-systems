import java.io.*; 
import java.io.DataInputStream; 
import java.net.*; 

class TCPServer 
{ 
    public static void main(String[] arg)throws Exception 
    { 
        ServerSocket server;
		DataInputStream in;
		PrintStream w;
		DataInputStream r;
        
		try
		{
			server=new ServerSocket(7896);  
	        Socket client=server.accept(); 
	        r=new DataInputStream(client.getInputStream()); 
	        w=new PrintStream(client.getOutputStream()); 
	        in=new DataInputStream(System.in); 
	        
	        // receive Client message
	        int num = r.read();
	        System.out.println("received the number: " + num);
	        
	        int numSum = 0;
	        // compute the summation from 0 to num
	        for (int a = 0; a <= num; a++)
	        {
	        	numSum = numSum + a;
	        }
	        
	        // compute the average of the summation
	        int numAvg = numSum/num;
	        
	        // send the computed average to the client
	        w.write(numAvg);
		}
		catch(IOException e)  
		{
			System.out.println("Connection:"+e.getMessage());
		}
    } 
}
