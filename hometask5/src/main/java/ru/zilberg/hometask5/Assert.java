package ru.zilberg.hometask5;

public class Assert {
    public static void assertTrue(String message, boolean condition){
        if(condition){
            if (message != null)
                System.out.println(message);
        }
        else throw new AssertError(message);
    }
    public static void assertFalse(String message, boolean condition){
        if(!condition){
            if (message != null)
                System.out.println(message);
        }
        else throw new AssertError(message);
    }
    public static void assertIsNotNull(String message, Object object){
        if(object != null){
            if (message != null)
                    System.out.println(message);
        }
        else throw new AssertError(message);
    }
}
