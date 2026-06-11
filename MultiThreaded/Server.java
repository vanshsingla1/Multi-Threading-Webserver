package MultiThreaded;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
    return (clientSocket) -> {
        try {
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
            toClient.println("Hello From the server");
            toClient.close();
            clientSocket.close();
        } catch(Exception exp) {
            exp.printStackTrace();
        }
    };
}
    public static void main(String[] args)  {
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is litening on Port : "+port);
            while(true) {
                Socket acceptedSocket = serverSocket.accept(); // new Socket is created , create a thread for it
                System.out.println("Connection accepted from Client"+acceptedSocket.getRemoteSocketAddress());
                Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch(Exception exp) {
            exp.printStackTrace();
        }
        
    }
}
