package ru.zilberg.hometask2;


public class SizeArrayInt implements ObjectSize {
    @Override
    public int SizeOf() {
        return getArraySize(1);
    }

    @Override
    public int SizeOf(int arraySize) {
        return getArraySize(arraySize);
    }


    private static int getArraySize(int sizeArray){
        int count = 1_000_000;

        Object [] objects = new Object[count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();

        for(int i =1; i <count; ++i){

            objects[i] = new int[sizeArray];
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        int arrSize = (int) (memoryAfter - memoryBefor)/count - 16;

        for (int i = 1; i < count; i++){
            objects[i] = null;
        }

        objects = null;

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        return arrSize;
    }
}
