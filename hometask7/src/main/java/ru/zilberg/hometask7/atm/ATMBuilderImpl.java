package ru.zilberg.hometask7.atm;

public class ATMBuilderImpl implements ATMBuilder {

    private final int defaultCountCells = 1;
    private final int defaultMaxCount = 100;
    private final Cell [] defualtCells = {new Cell(1, 100)};

    private int countCells = defaultCountCells;
    private int maxCount = defaultMaxCount;
    private Cell [] cells = defualtCells;

    @Override
    public ATMBuilderImpl setMaxCount(int maxCount){
        this.maxCount = maxCount;
        return this;
    }

    @Override
    public ATMBuilderImpl addCells(Cell... cells) {
        this.cells = cells;
        return this;
    }

    @Override
    public ATM build(){
        return new ATM(this.maxCount, this.cells);
    }
}
