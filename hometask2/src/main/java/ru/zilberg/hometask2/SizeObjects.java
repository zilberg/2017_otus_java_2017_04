package ru.zilberg.hometask2;


import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.lang.management.ManagementFactory;
public class SizeObjects {
    public static void main(String[] args) throws Exception {
        //-Xms524m -Xmx524m
        //System.out.println("Pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int count = 1_000_000;

        Object [] objects = new Object[count];

        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i <= 100; i++) {
            runtime.gc();
        }

        final long  memoryBefor = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Used memory befor create object: " + memoryBefor);

        for(int i =1; i <count; ++i){

            objects[i] = new Object();
        }
        runtime.gc();
        final long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        //System.out.println("Memory after fill empty Objects: " + memoryAfter);

        System.out.println("Size object Object: " + (memoryAfter - memoryBefor)/count + " bytes");

    }










}
