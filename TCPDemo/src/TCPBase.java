
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPBase {
    protected Socket sock;
    protected DataInputStream in;
    protected DataOutputStream out;
    
    public void connect(String host, int port) throws IOException {
        sock = new Socket(host, port);
        setupStreams();
    }
    
    public void waiting(int port) throws IOException {
        ServerSocket serverSock = new ServerSocket(port);
        sock = serverSock.accept();
    }
    
    public void sendInt(int x) throws IOException {
        out.writeInt(x);
    }
    
    public int receiveInt() throws IOException {
        return in.readInt();
    }
    
    public void sendMessage(String msg) throws IOException {
        out.writeUTF(msg);
    }
    
    public String receiveMessage() throws IOException {
        return in.readUTF();
    }
    
    public void setupStreams() throws IOException {
        in  = new DataInputStream(sock.getInputStream());
        out = new DataOutputStream(sock.getOutputStream());
    }
    
    public void closeConnection() throws IOException {
        sock.close();
    }
}
