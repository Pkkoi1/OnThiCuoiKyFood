package Server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;

public class server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2951)) {
            System.out.println("Server is running...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                System.out.println("Client IP: " + socket.getInetAddress().getHostAddress());
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
    class ClientHandler implements Runnable {
        private Socket socket;



        public ClientHandler(Socket socket) throws RemoteException {
            this.socket = socket;


        }

        @Override
        public void run() {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                int choice = 0;
                while (true)
                {

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
