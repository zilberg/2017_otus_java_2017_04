package ru.zilberg.hometask3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMyArrayList {
    MyArrayList<Integer> myArrList;
    ArrayList<Integer> arrList;
    @Before
    public void setUp(){
        Integer [] arr = {1,2,3,4,5,6,7,8,9,0,-1,-2,-3,-4,-5,-6,-8,-9};
        //It's my List
        myArrList = new MyArrayList<>();
        myArrList.addAll(Arrays.asList(arr));
        //It's ArrayList
        arrList = new ArrayList<>(Arrays.asList(arr));
    }
    @Test
    public void testAddAllOfCollection(){
        assertTrue(Collections.addAll(myArrList, 10, 23, 45, 23));
        assertTrue(Collections.addAll(arrList, 10,23,45,23));
        Assert.assertEquals(myArrList.hashCode(), arrList.hashCode());
        Assert.assertArrayEquals(myArrList.toArray(),arrList.toArray());
    }
    @Test
    public void testSortOfCollection(){
        Collections.sort(myArrList);
        Collections.sort(arrList);
        Assert.assertArrayEquals("Sorted arrays aren't equals", myArrList.toArray(), arrList.toArray());
    }
    @Test
    public void testCopyOfCollection(){
        List<Integer> addList = new ArrayList<>();
        addList.add(100);
        addList.add(1000);
        Collections.copy(myArrList, addList);
        Collections.copy(arrList, addList);
        Assert.assertArrayEquals(myArrList.toArray(), arrList.toArray());
    }

}

