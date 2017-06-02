import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.*;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by vasvasvlad on 01.06.17.
 */
public class ObjectToJSONString {

    public static JSONObject convertObjectInJSON(Object object) throws ClassNotFoundException,
            NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
        JSONObject jsonObject = new JSONObject();
        Class c = object.getClass();
        String nameObject = c.getSimpleName();
        switch(nameObject){
            case "Boolean":
                jsonObject.put(nameObject, c.getMethod("booleanValue").invoke(object));
                return jsonObject;
            case "Byte":
                jsonObject.put(nameObject, c.getMethod("byteValue").invoke(object));
                return jsonObject;
            case "Short":
                jsonObject.put(nameObject, c.getMethod("shortValue").invoke(object));
                return jsonObject;
            case "Character":
                jsonObject.put(nameObject, c.getMethod("charValue").invoke(object));
                return jsonObject;
            case "Integer":
                jsonObject.put(nameObject, c.getDeclaredMethod("intValue").invoke(object));
                return jsonObject;
            case "Long":
                jsonObject.put(nameObject, c.getDeclaredMethod("longValue").invoke(object));
                return jsonObject;
            case "Float":
                jsonObject.put(nameObject, c.getDeclaredMethod("floatValue").invoke(object));
                return jsonObject;
            case "Double":
                jsonObject.put(nameObject, c.getDeclaredMethod("doubleValue").invoke(object));
                return jsonObject;
            case "String":
                jsonObject.put(nameObject, c.getMethod("toString").invoke(object));
                return jsonObject;
            default:
                JSONArray arrField = new JSONArray();
                for (Field field : c.getFields()){
                    JSONObject newJsonObj = new JSONObject();
                    newJsonObj.put(field.getName(),field.get(object));
                    //System.out.println(newJsonObj);
                    //System.out.println(new JSONObject().put(field.getName(),field.get(object)));
                    arrField.add(newJsonObj);
                }
                jsonObject.put(c.getSimpleName(),arrField);
                return jsonObject;
        }

    }

    public static JSONObject convertArrayInJSON(Object obj) throws NoSuchMethodException{
        JSONArray arr = new JSONArray();
        Class c = obj.getClass();
        int len = Array.getLength(obj);
        for (int i = 0; i<len; ++i){
            arr.add(Array.get(obj,i));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(c.getCanonicalName(),arr);

        return jsonObject;
    }

}
