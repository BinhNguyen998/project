
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServerThread extends TCPBase implements Runnable {

    public TCPServerThread(Socket sock) throws IOException {
        this.sock = sock;
        setupStreams();
    }
    
    @Override
    public void run() {
        try {
            // 1. Nhan message tu client, hien thi ra
            String clientIP = sock.getInetAddress().getHostAddress();
            String msg = receiveMessage();
            System.out.println("[" + clientIP + "]: " + msg);
            // 2. Sinh 1 so ngau nhien n (5-10), gui cho client
            Random rnd = new Random();
            int n = rnd.nextInt(6) + 5;
            sendInt(n);
            // 3. Lap n lan
            for (int i = 0; i < n; i++) {
                //    3.1 Sinh 1 so ngau nhien (0-1-2)
                int choice = rnd.nextInt(3);
                //    3.2 Nhan (R/S/P) tu client
                msg = receiveMessage();
                System.out.println("[" + clientIP + "]: " + msg);
                //    3.3 Gui kq (R/S/P) cho client
                switch (choice) {
                    case 0: sendMessage("R"); break;
                    case 1: sendMessage("S"); break;
                    case 2: sendMessage("P"); break;
                }
                
            }
            // 4. Close connection
            this.closeConnection();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    
}
