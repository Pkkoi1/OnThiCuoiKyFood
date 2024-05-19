package dao;

import entity.Food;
import entity.Item;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface foodDao extends Remote {
    public boolean addFood(Food food) throws RemoteException;
}
