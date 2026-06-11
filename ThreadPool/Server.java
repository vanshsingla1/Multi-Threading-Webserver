package ThreadPool;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;
    public Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }
    public void handleClient(Socket clientSocket) {
        try {
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
            toClient.println("Hello From the server");
            toClient.close();
            clientSocket.close();
        } catch(Exception exp) {
            exp.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int port = 8010;
        int poolSize = 100; // Number of threads in the pool
        Server server = new Server(poolSize);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is litening on Port : "+port);
            
            while(true) {
                Socket clientSocket = serverSocket.accept(); // new Socket is created , create a thread for it
                System.out.println("Connection accepted from Client"+clientSocket.getRemoteSocketAddress());
                server.threadPool.execute(() -> server.handleClient(clientSocket));
            }
        } catch(Exception exp) {
            exp.printStackTrace();
        } finally {
            server.threadPool.shutdown();
        }
    }
}
