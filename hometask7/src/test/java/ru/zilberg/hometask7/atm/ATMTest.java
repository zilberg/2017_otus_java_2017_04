package ru.zilberg.hometask7.atm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ATMTest {
    @Test
    public void balanceSimple(){
        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm = atmBuilder
                .setMaxCount(100)
                .addCells(new Cell(5, 10))
                .build();
        Assert.assertEquals(50, atm.getBalance());
    }
    @Test
    public void withdrawSimple(){
        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm = atmBuilder
                .setMaxCount(20)
                .addCells(new Cell(1, 10), new Cell(5, 10), new Cell(10, 10))
                .build();
        int balance = atm.getBalance();

        Assert.assertTrue(atm.withdraw(27));
        Assert.assertEquals(balance - 27, atm.getBalance());
    }
    @Test
    public void withdrawFail(){

        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm = atmBuilder
                .setMaxCount(200)
                .addCells(new Cell(5, 10), new Cell(10, 10) )
                .build();

        int balance = atm.getBalance();

        Assert.assertFalse(atm.withdraw(27));
        Assert.assertEquals(balance, atm.getBalance());
    }
    @Test
    public void testGetCells(){
        Cell [] cells = {new Cell(5, 200), new Cell(10, 200)
        , new Cell(50, 100)};

        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm = atmBuilder
                .setMaxCount(300)
                .addCells(cells)
                .build();
        Assert.assertEquals(3, atm.getCountCells());
    }
    @Test
    public void replenishCellSimple(){
        Cell [] cells = {new Cell(5, 200), new Cell(10, 200)
                , new Cell(50, 100)};

        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm = atmBuilder
                .setMaxCount(300)
                .addCells(cells)
                .build();

        atm.replenishCell(new Cell(5, 100));

        Assert.assertEquals(5*300 + 10 * 200 + 50 * 100, atm.getBalance());
    }
    @Test
    public void initialStateSimple(){
        Cell [] cells = {new Cell(5, 200), new Cell(10, 200)
                , new Cell(50, 100)};

        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm = atmBuilder
                .setMaxCount(300)
                .addCells(cells)
                .build();
        atm.initialState();
        Assert.assertEquals((5 + 10 + 50) * 300, atm.getBalance() );
    }


}
