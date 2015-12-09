/**
 * Created by scruf on 12/8/15.
 */
import java.io.*;
import java.net.*;
public class MyWebServer extends Thread{
   public static void main(String []args){
       try{
           ServerSocket server = new ServerSocket(0);
           System.out.println("Server "+server.getInetAddress().getHostAddress()
           +" listening on port->"+server.getLocalPort());
           while(true){
               new Servant(server.accept());
               System.out.println(server.toString());
               server.close();
           }
       }catch (IOException ie){
           ie.printStackTrace();
           System.exit(0);
       }
   }
}
