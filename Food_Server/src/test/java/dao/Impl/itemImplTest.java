package dao.Impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import dao.itemDao;

import java.rmi.RemoteException;

class itemImplTest {

    static itemDao itemDao;

    @BeforeAll
    static void setUp() throws RemoteException {
        itemDao = new itemImpl();
    }
    @AfterAll
    static void tearDown() {
        itemDao = null;
    }

    @Test
    void testCauB1() throws RemoteException {
        itemDao.listItems("Anna Food Distributors").forEach(System.out::println);
//        assertLinesMatch(itemDao.listItems("anna food distributors").toString().lines(), itemDao.listItems("Anna Food Distributors").toString().lines());
    }


    @Test
    void testCauB2() throws RemoteException {
//        itemDao.listItems("anna food distributors").forEach(System.out::println);
        assertNull(itemDao.listItems("anna food distributors"));
    }

    @Test
    void testCauB3() throws RemoteException {
        assertNotNull(itemDao.listItems("Anna Food Distributrs"));
    }

}