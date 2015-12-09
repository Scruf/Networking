import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * Created by darren on 12/9/15.
 */
/**
 * Created by scruf on 12/8/15.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Solicitor {




    public static void main(String[] args) throws IOException
    {

        Socket sock=null;
        String serverName = " ";
        int serverPort=0;
        BufferedReader buffer = null;
        PrintWriter printer = null;
        String str ,line;

        try{
            if(args.length<2)
                System.err.println("Invalid Number oof arguments");
            else{
                serverName = args[0];
                serverPort=Integer.parseInt(args[1]);
            }
        }catch(Exception ex){
            System.err.println("Invalid input");
        }

        try
        {
            sock = new Socket(serverName, serverPort);
            printer = new PrintWriter(sock.getOutputStream(), true);
            buffer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }
        catch (UnknownHostException ex)
        {
            ex.printStackTrace();
            System.exit(1);
        }
        catch (IOException ie)
        {
            ie.printStackTrace();
            System.exit(1);
        }
        try{
            System.out.println(buffer.readLine());
           Scanner scan = new Scanner(System.in);
            str = scan.nextLine();
            printer.println(str);
            while ((line = buffer.readLine()) != null)
            {
                System.out.println(line);
                Thread.sleep(2000);
            }
            printer.close();
            buffer.close();
            sock.close();
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
            System.exit(1);
        }
    }
}