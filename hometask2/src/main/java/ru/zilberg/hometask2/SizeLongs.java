package ru.zilberg.hometask2;

/**
 * Created by vasvasvlad on 04.05.17.
 */
public class SizeLongs {
    public static void main(String[] args) {
        int count = 1_000_000;

        Long [] lings = new Long [count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Used memory befor create object: " + memoryBefor);

        for(int i =1; i <count; ++i){
            Long longer = null;
            longer = new Long(i);
            lings[i] = longer;
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Memory after fill empty Objects: " + memoryAfter);

        System.out.println("Size object Long: " + (memoryAfter - memoryBefor)/count + " bytes");
    }
}
