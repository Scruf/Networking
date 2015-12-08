/**
 * Created by scruf on 12/8/15.
 */
import java.net.*;
import java.io.*;

/**
 * Example of a thread class that will echo whatever comes
 * in through the socket.
 *
 * @author rwd
 */
public class Echo extends Thread
{
    private Socket socket ;

    Echo(Socket socket)
    {
        this.socket = socket ;

    }

    public void run()
    {
        BufferedReader input ;
        PrintWriter output ;
        String inputLine ;
        String outputLine ;

        try
        {
            input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream())) ;
            output = new PrintWriter( socket.getOutputStream(), true) ;

        }
        catch(IOException e)
        {
            System.err.println(e) ;
            return ;
        }

        try
        {
            outputLine = input.readLine() ;
            output.println(outputLine) ;
            while((inputLine = input.readLine()) != null)
            {
                outputLine = inputLine ;
                output.println(outputLine) ;
            }

            output.close() ;
            input.close() ;
            socket.close() ;
        }
        catch(SocketException e)
        {
            System.err.println("Connection reset by peer") ;
        }
        catch(IOException e)
        {
            System.err.println(e) ;
        }

    } // run()

}   // Echo