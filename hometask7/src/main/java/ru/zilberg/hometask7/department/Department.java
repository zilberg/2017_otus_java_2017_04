package ru.zilberg.hometask7.department;

import ru.zilberg.hometask7.atm.ATM;

public interface Department {

    void addATM(ATM atm);

    int remainingAmount();

    void restoreState();
}
