
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPClient extends TCPBase {
    
    
    
    public void run() throws IOException {
        int rounds = 0, wins = 0;
        Scanner sc = new Scanner(System.in);
        // Ask how many rounds
        sendMessage("How many rounds?");
        // receive number of rounds from server
        rounds = receiveInt();
        for (int i = 1; i <= rounds; i++) {
            // Player enters R/S/P
            System.out.print("Choose R/S/P: ");
            String choice = sc.nextLine();
            // Send player's choice to server
            sendMessage(choice);
            // Receive server's R/S/P
            String serverChoice = receiveMessage();
            System.out.println("[Server]: " + serverChoice);
            // Compare player's choice vs server's choice
            // increase / decrease wins
            wins += compare(choice, serverChoice);
        }
        
        if (wins > 0) System.out.println("You win!");
        else if (wins == 0) System.out.println("Draw game!");
        else System.out.println("You loose!");
    }
    
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter server address: ");
        String host = sc.nextLine();
        
        System.out.print("Enter server port: ");
        int port = sc.nextInt();
        
        try {
            client.connect(host, port);
            client.run();
        } catch (IOException ex) {
            System.out.println("Cannot connect to " + host);
            System.out.println("Error: " + ex.getMessage());
        }
        
    }

    private int compare(String choice, String serverChoice) {
        if (!choice.equals(serverChoice)) {
            if ( (choice.equals("R") && serverChoice.equals("S")) ||
                 (choice.equals("S") && serverChoice.equals("P")) ||
                 (choice.equals("P") && serverChoice.equals("R")) ) 
            {
                System.out.println("You win this round!");
                return 1;
            }                
            else {
                System.out.println("You loose this round!");
                return -1;
            }                
        } else {
            System.out.println("You draw this round!");
            return 0;
        }
    }
}