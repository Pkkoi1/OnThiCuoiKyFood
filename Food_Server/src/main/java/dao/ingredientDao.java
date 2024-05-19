package dao;

import entity.Food;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ingredientDao extends Remote {
    public Map<Food, Double> listFoodAndCost() throws RemoteException;
}
