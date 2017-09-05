package ru.zilberg.hometask7.department;

import ru.zilberg.hometask7.atm.ATM;

import java.util.ArrayList;
import java.util.List;

public class ATMDepartment implements Department {
    private final List<ATM> atms = new ArrayList<>();

    @Override
    public void addATM(ATM atm){
        atms.add(atm);
    }

    @Override
    public int remainingAmount(){
        int remainingSum = 0;
        for(ATM atm: atms) remainingSum += atm.getBalance();

        return remainingSum;
    }

    @Override
    public void restoreState(){
        atms.forEach(atm -> atm.initialState());
    }

    public int getCountATM(){
        return atms.size();
    }
}
