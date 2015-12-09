/**
 * Created by Egor Kozitski on 12/8/15.
 */
import java.io.*;
import java.net.*;
public class MyWebServer {
    static Socket sock;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(0);
            server.setSoTimeout(100000);
            System.out.println("Server " + server.getInetAddress().getHostAddress() + " listening on port-> " + server.getLocalPort());
            while (true) {
                sock = server.accept();
                Servant servant = new Servant(sock);
                System.out.println(servant.toString());
                servant.start();
            }
        } catch (SocketTimeoutException socket) {
            socket.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();

        }

    }
}