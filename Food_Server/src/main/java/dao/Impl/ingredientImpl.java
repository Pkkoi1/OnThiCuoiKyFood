package dao.Impl;

import dao.ingredientDao;
import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import javax.swing.text.html.parser.Entity;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ingredientImpl extends UnicastRemoteObject implements ingredientDao {

    EntityManager em;

    public ingredientImpl() throws RemoteException {
        super();
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    @Override
    public Map<Food, Double> listFoodAndCost() throws RemoteException {
        Map<Food, Double> result = new LinkedHashMap<>();
        String query = "SELECT f, SUM(i.quantity * i.price) + (f.preparationTime + f.servingTime) * 0.2 " +
                "FROM Food f INNER JOIN f.ingredients i " +
                "GROUP BY f.id, f.name, f.description, f.onSpecial, f.price, f.preparationTime, f.servingTime, f.type";

        List<Object[]> queryResult = em.createQuery(query).getResultList();
        queryResult.forEach(o -> {
            Food food = (Food) o[0];
            Double cost = (Double) o[1];
            result.put(food, cost);
        });
        return result;
    }
}
