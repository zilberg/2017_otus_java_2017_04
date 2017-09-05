package ru.zilberg.hometask7.atm;

public interface ATMBuilder {

    ATMBuilder setMaxCount(int maxCount);

    ATMBuilder addCells(Cell ... cells);

    ATM build();
}
