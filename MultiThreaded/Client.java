package MultiThreaded;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {
        Client client = new Client();
        for(int i=0; i<100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch(Exception exp) {
                exp.printStackTrace();
            }
        }
    }

    private Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                int port = 8010;
                try (Socket socket = new Socket("localhost", port)) {
                    PrintWriter toSocket = new PrintWriter((socket.getOutputStream()));
                    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    toSocket.println("Hello from the Client");
                    String line = fromSocket.readLine();
                    System.out.println("Response from the socket is :" + line);
                    toSocket.close();
                    fromSocket.close();
                    socket.close();
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            } 
        };
    }
}
