package dao.Impl;

import entity.Food;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import dao.foodDao;
import Enum.Type;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class foodImplTest {

    static foodDao foodDao;

    @BeforeAll
    static void setUp() throws RemoteException {
        foodDao = new foodImpl();
    }

    @AfterAll
    static void tearDown() {
        foodDao = null;
    }

    @Test
    void addFood() throws RemoteException {
        Food f = new Food("F100", "Banh Mi", 10000, "Banh Mi thit ngon", true, Type.MAIN_COURSE, 15, 15);
        assertTrue(foodDao.addFood(f));
    }
    @Test
    void addFood2() throws RemoteException {
        Food f = new Food("F10", "Banh Mi", 10000, "Banh Mi thit ngon", true, Type.MAIN_COURSE, 15, 15);
        assertFalse(foodDao.addFood(f));
    }


}