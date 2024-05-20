package dao.Impl;

import dao.ingredientDao;
import entity.Food;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import javax.swing.text.html.parser.Entity;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import Enum.Type;

public class ingredientImpl extends UnicastRemoteObject implements ingredientDao {

    EntityManager em;

    public ingredientImpl() throws RemoteException {
        super();
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    @Override
    public Map<Food, Double> listFoodAndCost() throws RemoteException {
        Map<Food, Double> result = new LinkedHashMap<>();
        String query = "SELECT f.id, f.name, f.price, f.description, f.onSpecial, f.type, f.preparationTime, f.servingTime, SUM(i.quantity * i.price) + (f.preparationTime + f.servingTime) * 0.2 " +
                "FROM Food f INNER JOIN f.ingredients i " +
                "INNER JOIN i.items it " +
                "GROUP BY f.id, f.name, f.price, f.description, f.onSpecial, f.type, f.preparationTime, f.servingTime";

        List<Object[]> queryResult = em.createQuery(query).getResultList();
        queryResult.forEach(o -> {
            Food food = new Food();
            food.setId((String) o[0]);
            food.setName((String) o[1]);
            food.setPrice((Double) o[2]);
            food.setDescription((String) o[3]);
            food.setOnSpecial((Boolean) o[4]);
            food.setType((Type) o[5]);
            food.setPreparationTime((Integer) o[6]);
            food.setServingTime((Integer) o[7]);
            Double cost = (Double) o[8];
            result.put(food, cost);
        });
        return result;
    }
}
