package dao;

import entity.Item;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface itemDao extends Remote {
    public List<Item> listItems(String supplier) throws RemoteException;

}
