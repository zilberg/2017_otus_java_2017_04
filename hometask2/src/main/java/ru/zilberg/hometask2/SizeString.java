package ru.zilberg.hometask2;

public class SizeString implements ObjectSize {

    @Override
    public int SizeOf() {
        int count = 1_000_000;

        String [] strings = new String [count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();

        for(int i =1; i <count; ++i){
            String string = null;
            string = new String ();
            strings[i] = string;
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        return (int) (memoryAfter - memoryBefor)/count;
    }

    @Override
    public int SizeOf(int arraySize) {
        return -1;
    }
}
