package dao;

import entity.Food;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface foodDao extends Remote {
    public boolean addFood(Food food) throws RemoteException;
}
