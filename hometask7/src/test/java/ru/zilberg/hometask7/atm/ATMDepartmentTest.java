package ru.zilberg.hometask7.atm;

import org.junit.Assert;
import org.junit.Test;
import ru.zilberg.hometask7.department.ATMDepartment;
import ru.zilberg.hometask7.department.Department;

public class ATMDepartmentTest {
    @Test
    public void addATMSimple(){
        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm1 = atmBuilder.setMaxCount(10)
                .addCells(new Cell(2, 10), new Cell(4, 8), new Cell(5, 3))
                .build();
        ATM atm2 = atmBuilder.setMaxCount(20)
                .addCells(new Cell(5, 15), new Cell(10, 20), new Cell(15, 3))
                .build();
        Department department = new ATMDepartment();

        department.addATM(atm1);
        department.addATM(atm2);

        Assert.assertEquals(2, ((ATMDepartment)department).getCountATM());

    }
    @Test
    public void remainingAmountTest(){
        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm1 = atmBuilder.setMaxCount(10)
                .addCells(new Cell(2, 10), new Cell(4, 8), new Cell(5, 3))
                .build();
        ATM atm2 = atmBuilder.setMaxCount(20)
                .addCells(new Cell(5, 15), new Cell(10, 20), new Cell(20, 3))
                .build();
        Department department = new ATMDepartment();
        department.addATM(atm1);
        department.addATM(atm2);

        atm1.withdraw(15);
        atm2.withdraw(5);

        Assert.assertEquals(atm1.getBalance() + atm2.getBalance(), department.remainingAmount());

        atm1.replenishCell(new Cell(4,2));
        atm2.replenishCell(new Cell(20, 7));

        Assert.assertEquals(atm1.getBalance() + atm2.getBalance(), department.remainingAmount());
    }

    @Test
    public void restoreStateTest(){
        ATMBuilder atmBuilder = new ATMBuilderImpl();
        ATM atm1 = atmBuilder.setMaxCount(10)
                .addCells(new Cell(2, 10), new Cell(4, 8), new Cell(5, 3))
                .build();
        ATM atm2 = atmBuilder.setMaxCount(20)
                .addCells(new Cell(5, 15), new Cell(10, 20), new Cell(20, 3))
                .build();
        Department department = new ATMDepartment();
        department.addATM(atm1);
        department.addATM(atm2);

        department.restoreState();

        Assert.assertEquals((2 + 4 +5) * 10 + (5 + 10 + 20) * 20, department.remainingAmount());
        Assert.assertEquals((2 + 4 +5) * 10 + (5 + 10 + 20) * 20, atm1.getBalance() + atm2.getBalance());
        Assert.assertEquals(department.remainingAmount(), atm1.getBalance() + atm2.getBalance());
    }
}
