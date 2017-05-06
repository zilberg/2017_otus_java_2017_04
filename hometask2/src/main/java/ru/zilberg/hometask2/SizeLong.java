package ru.zilberg.hometask2;

/**
 * Created by vasvasvlad on 04.05.17.
 */
public class SizeLong implements ObjectSize {
    @Override
    public int SizeOf() {
        int count = 1_000_000;

        Long [] lings = new Long [count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();

        for(int i =1; i <count; ++i){
            Long longer = null;
            longer = new Long(i);
            lings[i] = longer;
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        return  (int) (memoryAfter - memoryBefor)/count;
    }

    @Override
    public int SizeOf(int arraySize) {
        return -1;
    }
}
