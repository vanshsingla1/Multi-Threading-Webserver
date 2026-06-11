import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
// A ServerSocket is a fundamental Java class (found in the java.net package) used to implement the server side of a classic TCP/IP network connection
public class Server {
    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(20000);
        while(true) {
            System.out.println("Server is listening on Port : "+port);
            Socket acceptedConnection = socket.accept();
            System.out.println("Connection accepted from Client"+acceptedConnection.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
            // PrintWriter is a character-stream class in the java.io package designed to print formatted representations of objects and primitive data types to a text-output stream. It is highly favored in console applications, file writing, and network programming (like network sockets) because of its developer-friendly syntax.
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            toClient.println("Hello from the Server");
            toClient.close();
            fromClient.close();
            acceptedConnection.close();
        }
    }
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch(Exception exp) {
            exp.printStackTrace();
        }
    }
}