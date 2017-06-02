import java.lang.reflect.InvocationTargetException;

/**
 * Created by vasvasvlad on 01.06.17.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean boo = true;
        System.out.println(ObjectToJSONString.convertObjectInJSON(boo));
        byte b = 25;
        System.out.println(ObjectToJSONString.convertObjectInJSON(b));
        short s = 111;
        System.out.println(ObjectToJSONString.convertObjectInJSON(s));
        char ch = 'a';
        System.out.println(ObjectToJSONString.convertObjectInJSON(ch));
        int t =10;
        System.out.println(ObjectToJSONString.convertObjectInJSON(t));
        long l = 1000000000;
        System.out.println(ObjectToJSONString.convertObjectInJSON(l));
        float f = (float)123.2324;
        System.out.println(ObjectToJSONString.convertObjectInJSON(f));
        double d = 123.2324;
        System.out.println(ObjectToJSONString.convertObjectInJSON(d));
        String str = "Hello world";
        System.out.println(ObjectToJSONString.convertObjectInJSON(str));

        MyClass myClass = new MyClass(100,200);
        System.out.println(ObjectToJSONString.convertObjectInJSON(myClass));

        byte [] byteArr = new byte[]{-1,0,1,2};
        System.out.println(ObjectToJSONString.convertArrayInJSON(byteArr));
        short [] shortArr = new short[]{10,11,12,13,14};
        System.out.println(ObjectToJSONString.convertArrayInJSON(shortArr));
        char [] charArr = new char[]{'a','b','c','d'};
        System.out.println(ObjectToJSONString.convertArrayInJSON(charArr));
        int [] intArr = new int[]{1,2,3,4};
        System.out.println(ObjectToJSONString.convertArrayInJSON(intArr));
        long [] longArr = new long[]{1000,2000,3000,400};
        System.out.println(ObjectToJSONString.convertArrayInJSON(longArr));
        float [] floatArr = new float[]{111.22f,35.234f, 34.232f};
        System.out.println(ObjectToJSONString.convertArrayInJSON(floatArr));
        double [] doubleArr = new double[]{123.434, 3434.45, 45.34};
        System.out.println(ObjectToJSONString.convertArrayInJSON(doubleArr));
//        String [] stringArr = new String[]{"one", "two", "three"};
//        System.out.println(ObjectToJSONString.convertArrayInJSON(stringArr));

    }


}
class MyClass{
    public int var1;
    public int var2;
    public String str = "This's text";
    public char thisChar = '*';
    public MyClass(int v1, int v2){
        this.var1 = v1;
        this.var2 = v2;
    }
}
