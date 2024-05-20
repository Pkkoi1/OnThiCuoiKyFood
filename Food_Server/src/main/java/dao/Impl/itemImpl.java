package dao.Impl;

import dao.itemDao;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class itemImpl extends UnicastRemoteObject implements itemDao {
    EntityManager em;

    public itemImpl() throws RemoteException {
        super();
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    @Override
    public List<Item> listItems(String supplier) throws RemoteException {
            return em.createNamedQuery("Item.findById", Item.class)
                    .setParameter("supplierName", supplier)
                    .getResultList();
    }
}
