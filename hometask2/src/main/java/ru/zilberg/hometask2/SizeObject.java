package ru.zilberg.hometask2;

public class SizeObject implements ObjectSize {
    @Override
    public int SizeOf() {

        int count = 1_000_000;

        Object [] objects = new Object[count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();

        for(int i =1; i <count; ++i){

            objects[i] = new Object();
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        return (int)(memoryAfter - memoryBefor)/count;
    }

    @Override
    public int SizeOf(int arraySize) {
        return -1;
    }
}
