package ru.zilberg.hometask7.atm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ATM {
    private final Cell first;
    private List<Cell> cells;
    private int maxCount;

    public ATM(int maxCountNominal, Cell ... cells){
        this.maxCount = maxCountNominal;
        this.cells = Arrays.asList(cells);
        Collections.sort(this.cells);
        first = this.cells.get(0);
        linkCells(this.cells);
    }

    public Cell [] getCells(){
        return cells.toArray(new Cell[cells.size()]);
    }

    public int getCountCells(){return cells.size();}

    public void initialState(){
        for(Cell cell : this.cells){
            cell.setCount(this.maxCount);
        }
    }

    public boolean withdraw(int requested) {return  first.withdraw(requested);}

    public int getBalance(){
        Iterator<Cell> iterator = first.iterator();
        int balance = 0;
        while(iterator.hasNext()){
            balance += iterator.next().getBalance();
        }
        return balance;
    }

    private void linkCells(List<Cell> cells){
        Iterator<Cell> iterator = cells.iterator();
        Cell cellA = iterator.next();
        while(iterator.hasNext()){
            Cell cellB = iterator.next();
            cellA.setNext(cellB);
            cellA = cellB;
        }
    }

    public void replenishCell(Cell cell){
        Iterator<Cell> iterator = cells.iterator();
        boolean newNominal = true;
        while(iterator.hasNext()){
            Cell cellCurrent = iterator.next();
            if(cellCurrent.getNominal() == cell.getNominal() && (cellCurrent.getCount() + cell.getCount()) <= this.maxCount) {
                cellCurrent.addCount(cell.getCount());
                newNominal = false;
            }
        }

        if(newNominal){
            System.out.println("Error: There isn't atm this nominal!");
            System.out.println("Or a lot of count this nominal money. ");
        }
    }
    @Deprecated
    private void addCell(Cell cell){
        cells.add(cell);
        Collections.sort(cells);
        linkCells(cells);
    }
}
