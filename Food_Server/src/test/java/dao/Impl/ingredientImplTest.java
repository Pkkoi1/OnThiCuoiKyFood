package dao.Impl;

import static org.junit.jupiter.api.Assertions.*;
import dao.ingredientDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

class ingredientImplTest {

    static ingredientDao ingredientDao;

    @BeforeAll
    static void setUp() throws RemoteException {
        ingredientDao = new ingredientImpl();
    }

    @AfterAll
    static void tearDown() {
        ingredientDao = null;
    }

    @Test
    void testListFoodAndCost() throws RemoteException {
        ingredientDao.listFoodAndCost().forEach((k, v) -> System.out.println(k + " : " + v));
    }

}