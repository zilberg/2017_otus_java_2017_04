package ru.zilberg.hometask3;

import java.util.*;
import java.util.logging.*;
/**
 * Created by vasvasvlad on 11.07.17.
 */
public class Main {
    static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        LOGGER.log(Level.INFO,"Create MyArrayList");
        List<Integer> integerMyArrayList = new MyArrayList<>();
        LOGGER.log(Level.INFO, "Add  first element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(-189), integerMyArrayList.get(0)});
        LOGGER.log(Level.INFO, "Add  second element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(3456), integerMyArrayList.get(1)});
        LOGGER.log(Level.INFO, "Add  third element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(345), integerMyArrayList.get(2)});
        LOGGER.log(Level.INFO, "Add  fourth element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(-1449), integerMyArrayList.get(3)});
        LOGGER.log(Level.INFO, "Add  fifth element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(0), integerMyArrayList.get(4)});
        LOGGER.log(Level.INFO, "Add  sixth element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(1), integerMyArrayList.get(5)});
        LOGGER.log(Level.INFO, "Add  seventh element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(-99), integerMyArrayList.get(6)});
        LOGGER.log(Level.INFO, "Add  eighth element's {1}. Result is {0}",
                new Object [] {integerMyArrayList.add(99), integerMyArrayList.get(7)});
        LOGGER.log(Level.INFO, "Add  ninth element's {1}. Result is {0}", new Object [] {integerMyArrayList.add(null), integerMyArrayList.get(8)});
        LOGGER.log(Level.INFO, "MyArrayList is {0} ", integerMyArrayList.toString());
        LOGGER.log(Level.INFO, "MyArrayList size is {0}", integerMyArrayList.size());
        LOGGER.log(Level.INFO, "Checking index of an 0 is {0}", integerMyArrayList.indexOf(0));
        LOGGER.log(Level.INFO, "Checking index of not exist element -999 is {0}", integerMyArrayList.indexOf(-999));
        LOGGER.log(Level.INFO, "Checking index of null element is {0}", integerMyArrayList.indexOf(null));
        LOGGER.log(Level.INFO, "Checking contains null element use method 'contains()'. Result is {0}", integerMyArrayList.contains(null));
        LOGGER.log(Level.INFO, " 2000 isn't to contains in the MyArrayList. Result is {0}", integerMyArrayList.contains(200));
        LOGGER.log(Level.INFO, "Result is method isEmpty() equal {0}", integerMyArrayList.isEmpty());
        LOGGER.log(Level.INFO, "Remove null element. Result is {0}", integerMyArrayList.remove(null));
        LOGGER.log(Level.INFO, "Remove first element from the MyArrayList. This is {0}", integerMyArrayList.remove(0));
        List<Integer> copyIntegerMyArrayList = new MyArrayList<>();
        copyIntegerMyArrayList.addAll(integerMyArrayList);
        LOGGER.log(Level.INFO, "Copy this MyArrayList {0}", copyIntegerMyArrayList.toString());
        LOGGER.log(Level.INFO, "Replace the first element in copyMyArrayList {0} to 1000", copyIntegerMyArrayList.set(0, 1000));
        copyIntegerMyArrayList.clear();
        LOGGER.log(Level.INFO, "Remove all elements from copyMyArrayList. Size is {0}", copyIntegerMyArrayList.size() );
        Integer [] integerArray = new Integer[3];
        integerArray = integerMyArrayList.toArray(integerArray);
        LOGGER.log(Level.INFO,"Array length is 3 fill from MyArrayList : {0}", integerArray);
        for(Integer i : integerArray) System.out.print(i + " ");
        System.out.println();
        Integer [] integerArray2 = new Integer[13];
        integerArray = integerMyArrayList.toArray(integerArray2);
        LOGGER.log(Level.INFO,"Array length is 13 fill from MyArrayList :");
        for(Integer i : integerArray) System.out.print(i + " ");
        System.out.println();
        List myList = Collections.synchronizedList(new MyArrayList<>(integerMyArrayList));

        Iterator<Integer> itr = myList.iterator();
        while(itr.hasNext())
            System.out.print(itr.next() + " ");
        ListIterator<Integer> litItr = myList.listIterator();
        Collections.sort(myList, (x, y) -> x.equals(y) ? 1 : -1);
        System.out.println("------");
        System.out.println(myList);




//        List<Integer> lst = new MyArrayList<>(Arrays.asList(1,34,-12,32,77,23,67,1000,-123));
//        System.out.println(selectionSort(lst));
    }

    /*public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> symmetricDifference = new HashSet<>(set1);
        symmetricDifference.addAll(set2);
        Set<? extends T> temp = new HashSet<>(set1);
        temp.retainAll(set2);
        symmetricDifference.removeAll(temp);
        return symmetricDifference;
    }
    public static <T extends Integer> int findSmallest(List<T> arr, Comparator<T> c ){
        Iterator<T> iter = arr.iterator();
        T smallest = iter.next();
        int i = 0;
        int smallest_index = i;
        while(iter.hasNext()){
            T current = iter.next();
            ++i;
            if( c.compare(current, smallest) < 0){
                smallest = current;
                smallest_index = i;
            }
        }
        return smallest_index;
    }
    public static <T extends Integer> List<T> selectionSort(List<T> arr){
        List<T> newArr = new ArrayList<>(arr.size());
        int old_size = arr.size();
        for (int i = 0; i < old_size; i++){
            int smallest = findSmallest(arr, (x , y) -> (x.intValue() -y.intValue()) > 0 ? 1 : -1);
            newArr.add(arr.get(smallest));
            arr.remove(smallest);
        }
        return newArr;
    }*/
}

