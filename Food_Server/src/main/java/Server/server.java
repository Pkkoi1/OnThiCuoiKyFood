package Server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import Enum.Type;
import dao.Impl.foodImpl;
import dao.Impl.ingredientImpl;
import dao.Impl.itemImpl;
import dao.foodDao;
import dao.ingredientDao;
import dao.itemDao;
import entity.Food;
import entity.Item;

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
                foodDao foodDao = new foodImpl();
                itemDao itemDao = new itemImpl();
                ingredientDao ingredientDao = new ingredientImpl();
                int choice = 0;
                while (true)
                {
                    try{
                        choice = dis.readInt();

                    }catch (SocketException | EOFException e){
                        System.out.println("Client disconnected");
                        break;
                    }
                    switch (choice)
                    {
                        case 1:
                            System.out.println("Client requested list all items");
                            System.out.println("Câu a:");
                            String foodID = dis.readUTF();
                            String foodName = dis.readUTF();
                            double foodPrice = dis.readDouble();
                            String foodDescription = dis.readUTF();
                            boolean foodOnSpecial = dis.readBoolean();
                            Type foodType = Type.valueOf(dis.readUTF());
                            int foodPreparationTime = dis.readInt();
                            int foodServingTime = dis.readInt();

                            Food food = new Food(foodID, foodName, foodPrice, foodDescription, foodOnSpecial, foodType, foodPreparationTime, foodServingTime);
                            boolean result = foodDao.addFood(food);
                            oos.writeBoolean(result);
                            oos.flush();


                            break;
                        case 2:
                            String supplierName = dis.readUTF();
                            List<Item> list = itemDao.listItems(supplierName);
                            oos.writeObject(list);

                            break;
                        case 3:
                            Map<Food, Double> foodAndCost = ingredientDao.listFoodAndCost();
                            oos.writeObject(foodAndCost);

                            break;
                        case 4:
                            System.out.println("Client disconnected");
                            break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
