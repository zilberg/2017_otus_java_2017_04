package ru.zilberg.hometask2;

public class Factory {
    public ObjectSize getObject(String myObject){
        switch (myObject){
            case "object": return new SizeObject();
            case "string": return new SizeString();
            case "long": return new SizeLong();
            case "intarray": return new SizeArrayInt();
            default: return null;
        }
    }
}
