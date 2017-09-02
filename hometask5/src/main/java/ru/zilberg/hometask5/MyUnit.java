package ru.zilberg.hometask5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyUnit {
    public static void runClasses(Class ... classes){
        for(Class cl: classes){
            Method [] methods = ReflectionHelper.getMethod(cl);
            MyUnit.startTestMethods(methods, cl);
        }
    }

    private static <T> void startTestMethods(Method [] methods, Class<T> cl){
        try{
            for(Method method : ReflectionHelper.getMethodWithAnnotationTest(methods)) {
                Object obj = ReflectionHelper.instantiate(cl);
                ReflectionHelper.getMethodWithAnnotationBefor(methods).invoke(obj);
                method.invoke(obj);
                ReflectionHelper.getMethodWithAnnotationAfter(methods).invoke(obj);
            }
        }catch(IllegalAccessException | InvocationTargetException e1){
            e1.printStackTrace();
            //System.out.println("I'm not understanding: @Test annotation be, but have problem");
        }catch(NullPointerException e){
            System.out.println("@Test annotation don't use. It isn't normal)");
        }
    }
    @Deprecated
    private static <T> void startBeforMethod(Method [] methods, Class<T> cl){
        try {
            ReflectionHelper.getMethodWithAnnotationBefor(methods).invoke(ReflectionHelper.instantiate(cl));
        }catch(IllegalAccessException | InvocationTargetException e1){
            System.out.println("I'm not understanding: @Test annotation be, but have problem");
        }catch (NullPointerException e){
            System.out.println("@Befor annotation don't use. It's normal)");
        }
    }
    @Deprecated
    private static <T> void startAfterMethods(Method [] methods, Class cl){
        try {
            ReflectionHelper.getMethodWithAnnotationAfter(methods).invoke(ReflectionHelper.instantiate(cl));
        }catch(IllegalAccessException | InvocationTargetException e1){
            System.out.println("I'm not understanding: @After annotation be, but have problem");
        }catch (NullPointerException e){
            System.out.println("@After annotation don't use. It's normal)");
        }
    }

}
