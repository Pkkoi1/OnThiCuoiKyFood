package dao.Impl;

import dao.foodDao;
import entity.Food;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class foodImpl extends UnicastRemoteObject implements foodDao {

    EntityManager em;

    public foodImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    @Override
    public boolean addFood(Food food) throws RemoteException {
        try {
//            a) (1.5 điểm) Thêm một món ăn mới. Trong đó, mã số của món phải bắt đầu là F và theo sau ít
//            nhất 3 ký số.
            if (!food.getId().matches("F\\d{3,}")) {
                return false;
            }
            em.getTransaction().begin();

            em.persist(food);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }



}
