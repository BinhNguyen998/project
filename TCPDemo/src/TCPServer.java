
import com.sun.webkit.ThemeClient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer {
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSock = new ServerSocket(11919);
            while (true) {
                Socket clientSock = serverSock.accept();
                System.out.println("Client connected from " 
                          + clientSock.getInetAddress().getHostAddress());
                
                TCPServerThread client = new TCPServerThread(clientSock);
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
