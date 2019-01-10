import java.io.*; 
import java.rmi.*;


public class FileClient{
   public static void main(String argv[]) {
      
	  if(argv.length != 2) {
        System.out.println("Usage: java FileClient fileName machineName");
        System.exit(0);
      }
      /*try {
         String name = "//" + argv[1] + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(name);
         byte[] filedata = fi.downloadFile(argv[0]);
         File file = new File(argv[0]);
         BufferedOutputStream output = new
           BufferedOutputStream(new FileOutputStream(file.getName()));
         output.write(filedata,0,filedata.length);
         output.flush();
         output.close();
		 System.out.println("File downloaded...");
      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }*/
	  
	  // new code
	  try {
		 String name = argv[1];
         FileInterface fi = (FileInterface) Naming.lookup(name);
         File file = new File(argv[0]);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(argv[0]));
         input.read(buffer,0,buffer.length);
		 fi.uploadFile(argv[0],buffer);
         input.close();
      } catch(Exception e){
         System.out.println("FileImpl: "+e.getMessage());
         e.printStackTrace();
         
      }
   }
}