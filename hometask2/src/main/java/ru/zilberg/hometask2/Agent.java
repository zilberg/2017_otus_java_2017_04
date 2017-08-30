package ru.zilberg.hometask2;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("Class loaded: " + instrumentation.getAllLoadedClasses().length);
        System.out.println("Object size: " + instrumentation.getObjectSize(new Object()));
        System.out.println("String size: " + instrumentation.getObjectSize(new String("test")));
        System.out.println("int[10] size: " + instrumentation.getObjectSize(new int[10]));
    }
}
