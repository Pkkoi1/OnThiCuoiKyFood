package Client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 2951);
            Scanner scanner = new Scanner(System.in);
        ){
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server");
            int choice =0;
            while (true)
            {

            }
        } catch (Exception e) {
            System.out.println("Lỗi h");
        }
    }
}
