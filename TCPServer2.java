import java.io.*; 
import java.io.DataInputStream; 
import java.net.*; 

class TCPServer2 
{ 
    @SuppressWarnings("deprecation")
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
	        String textRead = r.readLine();
	        System.out.println("received: " + textRead);
	        
	        // reverse the received text message
	        String textRev = "";
	        for (int a = textRead.length() - 1; a >= 0; a--)
	        {
	        	textRev = textRev + textRead.charAt(a);
	        }
	        
	        // send the reversed text message to the client
	        w.println(textRev);
		}
		catch(IOException e)  
		{
			System.out.println("Connection:"+e.getMessage());
		}
    } 
}
