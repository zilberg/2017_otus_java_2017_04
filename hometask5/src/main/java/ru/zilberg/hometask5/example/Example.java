package ru.zilberg.hometask5.example;

public class Example {
    private int x;
    private int y;
    public Example(){
        this.x = 0;
        this.y = 0;
    }
    public Example(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
    public int sum(){
        return this.x + this.y;
    }
    public int mul(){
        return this.x * this.y;
    }

}
