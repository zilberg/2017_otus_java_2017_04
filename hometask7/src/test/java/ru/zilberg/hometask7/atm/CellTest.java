package ru.zilberg.hometask7.atm;

import org.junit.Assert;
import org.junit.Test;

public class CellTest {
    @Test
    public void withdrawSimple() {
        Cell cell = new Cell(1, 100);
        Assert.assertTrue(cell.withdraw(43));
        Assert.assertEquals(100 - 43, cell.getCount());
    }
    @Test
    public void withdrawDiscrete(){
        Cell cell = new Cell(5, 100);
        Assert.assertFalse(cell.withdraw(43));
    }
    @Test
    public void withdrawTwoCells(){
        Cell cell1 = new Cell(1, 100);
        Cell cell5 = new Cell(5, 100);

        cell5.setNext(cell1);

        Assert.assertTrue(cell5.withdraw(43));
        Assert.assertEquals(100 - 8, cell5.getCount());
        Assert.assertEquals(100 - 3, cell1.getCount());
    }
    @Test
    public void withdrawTwoCellsLittle(){
        Cell cell1 = new Cell(1, 100);
        Cell cell5 = new Cell(5, 100);

        cell5.setNext(cell1);

        Assert.assertTrue(cell5.withdraw(550));
        Assert.assertEquals(0, cell5.getCount());
        Assert.assertEquals(100 - 50, cell1.getCount());
    }
    @Test
    public void testSetCount(){
        Cell cell = new Cell(2, 100);
        cell.setCount(200);
        Assert.assertEquals(200, cell.getCount() );
    }
}
