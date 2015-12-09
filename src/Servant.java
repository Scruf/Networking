/**
 * Created by scruf on 12/8/15.
 */
import java.io.*;
import java.net.*;
public class Servant extends Thread {
  public  Socket clientSocket;
   InetAddress address;
    int portNumber;
    String hostName;
    public Servant(Socket client){
        this.clientSocket=client;
        this.address = client.getInetAddress();
        this.portNumber = client.getLocalPort();
        this.hostName = client.getInetAddress().getHostName();
    }
    @Override
    public void run(){
        String str=" ";
        BufferedReader buffer= null;
        PrintWriter printer = null;
        try{
            buffer = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            printer  = new PrintWriter(this.clientSocket.getOutputStream(),true);
            while(buffer.ready()){
                str=buffer.readLine();
                System.out.println(str);
            }
            printer.println("Request was received");
            String file = str;
            String temp = " ";
            try{
              BufferedReader buf = new BufferedReader(new FileReader(file));
                while((temp = buf.readLine())!=null){
                    printer.println(temp);
                }
                System.out.println("The transfer has been completed");
                buf.close();
            }catch (FileNotFoundException fil){
                fil.printStackTrace();
                this.clientSocket.close();
            }

        }catch(IOException ie){
            ie.printStackTrace();

        }
        try{
            this.clientSocket.close();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }



}
