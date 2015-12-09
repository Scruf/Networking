/**
 * Created by scruf on 12/8/15.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Solicitor {

    public static InetAddress serverName;
    public static int serverPort;
    static Socket sock = null;


    public static void main(String[] args) throws UnknownHostException{

        serverName = InetAddress.getByName(args[0]);
        serverPort = Integer.parseInt(args[1]);
        try {
            Socket s = new Socket(serverName, serverPort);
            sock = s;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String request = null; // the request sent from the client
        // Get I/O stream in/out from the socket;
        PrintWriter out = null;
        BufferedReader in = null;


        try {
            in = new BufferedReader (new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);

            // print the message sent from the client onto the server
            while (in.ready()) {
                request = in.readLine();
                System.out.println(request);
            }

            // send client confirmation of request
            out.println("Request was received");

            // The name of the file to open.
            String fileName = request;

            File file = new File(fileName);

            try {

                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    out.println(sc);
                }
                sc.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // This will reference one line at a time
            String line = null;

            try {
                // FileReader reads text files in the default encoding.
                FileReader fileReader = new FileReader(fileName);

                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }

                System.out.println("The tranfer has been completed.");
                // Always close files.
                bufferedReader.close();
            }
            catch(FileNotFoundException ex) {
                System.out.println("Unable to open file '" + fileName + "'");
                sock.close();
                return;
            }
            catch(IOException ex) {
                System.out.println("Error reading file '" + fileName + "'");
                sock.close();
                return;
                // Or we could just do this:
                // ex.printStackTrace();
            }

            sock.close(); // close this connection, but not the server;
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}