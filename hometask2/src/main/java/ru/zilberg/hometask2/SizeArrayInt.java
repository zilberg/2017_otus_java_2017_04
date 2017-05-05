package ru.zilberg.hometask2;


public class SizeArrayInt {
    public static void main(String[] args) {

        for(int i = 1; i <= 10; i++ ) {
            printArraySize(i);
        }

    }
    private static void printArraySize(int sizeArray){
        int count = 1_000_000;

        Object [] objects = new Object[count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Used memory befor create object: " + memoryBefor);

        for(int i =1; i <count; ++i){

            objects[i] = new int[sizeArray];
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Memory after fill empty Objects: " + memoryAfter);

        System.out.println("length: "+ sizeArray + " of array int " + ((memoryAfter - memoryBefor)/count - 16) + " bytes");

        for (int i = 1; i < count; i++){
            objects[i] = null;
        }

        objects = null;

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }
    }
}
