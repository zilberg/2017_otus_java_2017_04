package ru.zilberg.hometask2;

import java.lang.management.ManagementFactory;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //-Xms524m -Xmx524m
        System.out.println("Pid: " + ManagementFactory.getRuntimeMXBean().getName());

        //Used memory to create variables
        final long usedMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used Memory = " + usedMem1+ "bit" );

        // Create empty int value
        int a;
        final long usedMem2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used Memory create int = " + usedMem2 + "bit");

        // Value not empty
        a = 123456789;
        final long usedMem3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("How many changes memory after assignment the value" + (usedMem2-usedMem3) + "bit");

        //Create empty String
        String stroka;
        final long usedMem4 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("How many changes memory after create empty String: " + (usedMem3-usedMem4)+ "bit" );

        //String not empty
        stroka ="12345678955555555555555555555555555555555555555555555555555555555555555555555555555";
        final long usedMem5 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("How many changes memory after create String: " + (usedMem4-usedMem5) + "bit");


        int count = 100_000;
        int i = 1;
        while(i < count){
            stroka+="text";
            i++;
        }

        final long usedMem6 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("How many chagnes memery after create " + count +" string: " + (usedMem5 - usedMem6) + "bit");

        //Create empty array

        Object [] arr = new Object[i];

        while (i == 0){
            arr[i] = new String("ky");
            i--;
        }

        final long usedMem7 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("How many changes memory after create array " + count + " " + (usedMem6 - usedMem7) + "bit" );




    }

}
