package ru.zilberg.hometask3;



import java.util.*;


public class MyArrayList<T> implements List<T> {
    /**
     * Default initial capacity.
     * Начальная емкость по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Shared empty array instance used for empty instances.
     * Общий пустой экземпляр массива, используемый для пустых экземпляров
     *  new MyArrayList(0)
     */
    private static final Object [] EMPTY_ELEMENTDATA = {};
    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     * Общий пустой экземпляр массива, используемый для пустых экземпляров по умолчанию.
     * Мы отличаем это от EMPTY_ELEMENTDATA, чтобы знать, как много раздуваться при добавлении первого элемента.
     * new MyArrayList()
     */
    private static final Object [] DEFUALTCAPACITY_EMPTY_ELEMENTDATA = {};
    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer. Any
     * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
     * Буфер массива, в который хранятся элементы массива ArrayList.
     * Емкость массива ArrayList - это длина этого массива.
     * Любой пустой ArrayList с elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * будет расширен до DEFAULT_CAPACITY, когда будет добавлен первый элемент.
     */
    transient T[] elementData;
    /**
     * The size of the ArrayList
     */
    private int size;
    /**
     * The number of times this list has been <i>structurally modified</i>.
     * Structural modifications are those that change the size of the
     * list, or otherwise perturb it in such a fashion that iterations in
     * progress may yield incorrect results.
     */
    private int modCount = 0;

    public MyArrayList(int initialCapacity){
        if(initialCapacity > 0){
            this.elementData = (T[])new Object[initialCapacity];
        }else if(initialCapacity == 0){
            this.elementData = (T[])EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
    public MyArrayList(){
        this.elementData = (T[])DEFUALTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(Collection<? extends T> c ){
        elementData = (T[])c.toArray();
        if((size = elementData.length) != 0){
            if(elementData.getClass() != Object[].class)
                elementData = (T[])Arrays.copyOf(elementData, size, Object[].class);
        }else{
            this.elementData = (T[])EMPTY_ELEMENTDATA;
        }
    }

    public void trimToSize(){
        modCount++;
        if(size < elementData.length){
            elementData = (size == 0)
                    ? (T[])EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }

    }
    public void ensureCapacity(int minCapacity){
        int minExpand = (elementData != DEFUALTCAPACITY_EMPTY_ELEMENTDATA)
                ? 0
                : DEFAULT_CAPACITY;
        if (minCapacity > minExpand)
            ensureExplicitCapacity(minCapacity);
    }
    private void ensureCapacityInternal(int minCapacity){
        if(elementData == DEFUALTCAPACITY_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity){
        modCount++;
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if(newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity){
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }
    //Object methods

    @Override
    public int hashCode(){
        int hashCode = 1;
        for(T t: this)
            hashCode = 31 * hashCode + (t == null ? 0 : t.hashCode());
        return hashCode;
    }
    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if(!(o instanceof List))
            return false;
        ListIterator<T> t1 = listIterator();
        ListIterator<?> t2 = ((List<?>)o).listIterator();
        while(t1.hasNext() && t2.hasNext()){
            T o1 = t1.next();
            Object o2 = t2.next();
            if(!(o1 == null ? o2 == null: o1.equals(o2)))
                return false;
        }

        return !( t1.hasNext() || t2.hasNext());


    }
    @Override
    public String toString(){
        if ( size == 0)
            return "[]";
        StringBuilder myArrayListStr = new StringBuilder("[");
        for(int i = 0; i < size; ++i) {
            myArrayListStr.append(elementData[i]);
            if(i != size-1)
                myArrayListStr.append(", ");
        }
        return myArrayListStr.append("]").toString();
    }

    //Collection methods

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean contains(Object o){
        return indexOf(o) >=0;
    }
    @Override
    public boolean containsAll(Collection<?> c){
        //See the AbstractCollection
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;

    }
    @Override
    public Object clone(){
        try {
            MyArrayList<T> v = (MyArrayList<T>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        }catch(CloneNotSupportedException e){
            throw new InternalError(e);
        }

    }
    @Override
    public Iterator<T> iterator(){
        return new MyItr();
    }
    @Override
    public Object [] toArray(){
        return Arrays.copyOf(elementData, size);
    }
    @Override
    public <T> T[] toArray(T [] a){
        if(a.length < size){
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0 , a, 0, size);
        if(a.length > size){
            a[size] = null;
        }
        return a;

    }

    private String outOfBoundsMsg(int index){
        return "Index: " + index + ", Size: " + size;
    }
    @Override
    public boolean add(T e){
        ensureCapacityInternal(size+1);
        elementData[size++] = e;
        return true;
    }
    @Override
    public boolean addAll(Collection <? extends T> c){
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;

    }

    @Override
    public boolean remove(Object o){
        if ( o == null)
            for (int i = 0; i < size; i++)
                if (elementData[i] == null){
                    fastRemove(i);
                    return true;
                }
        for(int i = 0; i < size; i++)
            if (elementData[i].equals(o)){
                fastRemove(i);
                return true;
            }

        return false;
    }
    private void fastRemove(int index){
        modCount++;
        int numMoved = size - index -1;
        if(numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        elementData[--size] = null;
    }


    @Override
    public boolean removeAll(Collection<?> c){
        if ( c == null)
            throw new NullPointerException();
        return batchRemove(c, false);

    }
    @Override
    public boolean retainAll(Collection<?> c){
        if (c == null)
            throw new NullPointerException();
        return batchRemove(c, true);
    }
    private boolean batchRemove(Collection<?> c, boolean complement){
        final Object [] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try{
            //Good idea.
            // When remove:
            // It's move element right if the element not contains
            //and count such element w-1(size = w)
            //when retain: ... element contains ...
            for(; r < size; r++){
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
            }
        }finally{
            if(r != size){
                System.arraycopy(elementData, r, elementData, w, size - r);
                w +=size -r;
            }
            if( w !=size){
                for(int i = w; i<size; i++){
                    elementData[i] = null;
                }
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;

    }
    @Override
    public void clear(){
        modCount++;
        for(int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
    }

    //List methods

    @Override
    public T get(int index){
        if (index >= size)
            throw new IndexOutOfBoundsException();
        return (T) elementData[index];
    }
    @Override
    public T set(int index, T element){
        if (index >= size)
            throw new IndexOutOfBoundsException();
        T oldValue = (T)elementData[index];
        elementData[index] = element;
        return oldValue;
    }
    @Override
    public void add(int index, T element){
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index +1, size - index);
        elementData[index] = element;
        size++;
    }
    @Override
    public boolean addAll(int index, Collection<? extends T> c){
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        Object [] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);

        int numMoved = size - index;
        if(numNew > 0)
            System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
        System.arraycopy(a,0, elementData,index,numNew);
        size +=numNew;
        return numNew != 0;
    }
    @Override
    public T remove(int index){
        if( index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

        modCount++;
        T oldValue = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return oldValue;
    }
    @Override
    public int indexOf(Object o){
        if( o == null){
            for (int i = 0; i< size; i++){
                if(elementData[i] == null)
                    return i;
            }
        }else{
            for (int i = 0; i< size; i ++){
                if(o.equals(elementData[i]))
                    return i;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Object o){
        if(o == null){
            for(int i =size - 1; i >= 0; --i){
                if(elementData[i] == null)
                    return i;
            }
        }else{
            for(int i =size - 1; i >= 0; --i){
                if(o.equals(elementData[i]))
                    return i;
            }
        }
        return -1;
    }
    @Override
    public List<T> subList(int fromIndex, int toIndex){
        if (fromIndex < 0 )
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex > toIndex");
        MyArrayList <T> mySubList = new MyArrayList<>();
        for(int i = fromIndex; i <toIndex; i++){
            mySubList.add((T) elementData[i]);
        }

        return mySubList;
    }
    @Override
    public void sort(Comparator<? super T> c){
        final int expectedModCount = modCount;
        Arrays.sort(elementData, 0, size, c);
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
        modCount++;
    }

    //iterators
    @Override
    public ListIterator<T> listIterator(){
        return listIterator(0);
    }
    @Override
    public ListIterator<T> listIterator(int index){
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        return new MyListItr(index);
    }

    private class MyItr implements Iterator<T>{
        int cursor = 0;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext(){return cursor != size();}
        public T next(){
            checkForComodificatoin();
            //try {
            int i = cursor;
            if(i > size)
                throw new NoSuchElementException();
            T [] elementData = MyArrayList.this.elementData;
            if ( i > elementData.length)
                throw new ConcurrentModificationException();
            //T next = get(i);
            //lastRet = i;
            cursor = i + 1;
            return elementData[lastRet = i];
            //}catch(IndexOutOfBoundsException e){
            //  checkForComodificatoin();
            //throw new NoSuchElementException();
            //}
        }
        public void remove(){
            if(lastRet < 0)
                throw new IllegalStateException();
            checkForComodificatoin();

            try{
                MyArrayList.this.remove(lastRet);
//                if(lastRet < cursor)
//                    cursor--;
                cursor = lastRet;
                lastRet = -1;
                checkForComodificatoin();
            }catch (IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodificatoin(){
            if(modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
    private  class MyListItr extends MyItr implements ListIterator<T>{
        MyListItr(int index){  super();  cursor = index;}
        public boolean hasPrevious(){return cursor != 0;}
        public T previous(){
            checkForComodificatoin();
            //try{
            int i = cursor - 1;
            if ( i < 0)
                throw new NoSuchElementException();
            T [] elementData = MyArrayList.this.elementData;
            if(i >= elementData.length)
                throw new ConcurrentModificationException();
            //T previous = get(i);
            //lastRet = cursor = i;
            cursor = i;
            return elementData[lastRet = i];
//            }catch (IndexOutOfBoundsException e){
//                checkForComodificatoin();
//                throw new NoSuchElementException();
//            }
        }
        public int nextIndex(){ return cursor; }
        public int previousIndex(){return cursor - 1;}
        public void set(T t){
            if(lastRet < 0)
                throw new IllegalStateException();
            checkForComodificatoin();
            try{
                MyArrayList.this.set(lastRet, t);
                expectedModCount = modCount;
            }catch(IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }
        public void add(T t){
            checkForComodificatoin();
            try{
                int i = cursor;
                MyArrayList.this.add(i, t);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            }catch (IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }
    }




}

