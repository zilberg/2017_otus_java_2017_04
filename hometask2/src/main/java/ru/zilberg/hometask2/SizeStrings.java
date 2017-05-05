package ru.zilberg.hometask2;

public class SizeStrings {
    public static void main(String[] args) {
        int count = 1_000_000;

        String [] strings = new String [count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Used memory befor create object: " + memoryBefor);

        for(int i =1; i <count; ++i){
            String string = null;
            string = new String ();
            strings[i] = string;
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Memory after fill empty Objects: " + memoryAfter);

        System.out.println("Size object String: " + (memoryAfter - memoryBefor)/count + " bytes");
    }
}
