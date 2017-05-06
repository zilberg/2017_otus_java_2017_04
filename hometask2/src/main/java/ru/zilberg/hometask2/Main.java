package ru.zilberg.hometask2;

/**
 * Created by vasvasvlad on 06.05.17.
 */
public class Main {
    public static void main(String ... args) {

        String nameObject = args[0];
        Factory factory = new Factory();

        if(nameObject.contains("array"))
            System.out.println("Size of " + nameObject + " = " + factory.getObject(nameObject).SizeOf(Integer.parseInt(args[1])) );
        else
        System.out.println("Size of " + nameObject + " = " + factory.getObject(nameObject).SizeOf() );
    }
}
