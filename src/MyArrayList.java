import java.util.ArrayList;

public class MyArrayList<E extends Comparable<E>> implements Container<E> {

    private E[] data;
    private int size;
    public MyArrayList(int capacity){
        data =(E[]) new Comparable[capacity];
        size = 0;
    }

    // Parameterless constructor, the capacity of the default array is capacity=10
    public MyArrayList(){
        this(10);
    }

    public int getCapacity(){
        return data.length;
    }

    @Override
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if(size == data.length)
            resize(2 * data.length);

        for(int i = size - 1; i >= index ; i --)
            data[i + 1] = data[i];

        data[index] = e;

        size ++;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    @Override
    public boolean addElement(E e){
        addLast(e);
        return true; // if no any exception, it will reach here and return true
    }

    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    public boolean contains(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    @Override
    public E searchBy(E e){
        for(int i = 0 ; i < size ; i ++){
            if(e.compareTo(data[i])==0)
                return data[i];
        }
        return null;
    }


    // Find the index of element e in the array. If element e does not exist, return -1
    public int find(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for(int i = index + 1 ; i < size ; i ++)
            data[i - 1] = data[i];
        size --;
        data[size] = null; // loitering objects != memory leak

        if(size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public void removeBy(E e){
        int index=-1;
        for(int i = 0 ; i < size ; i ++){
            if(e.compareTo(data[i])==0){
                index=i;
                break;
            }
        }

        if(index != -1)
            remove(index);
    }

    @Override
    public ArrayList<E> print(){
        ArrayList<E> studentArrayList=new ArrayList<>();
        for(int i = 0 ; i < size ; i ++) {
            studentArrayList.add(data[i]);
        }
        return studentArrayList;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("MyArrayList: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapacity){

        E[] newData =(E[]) new Comparable[newCapacity];
        for(int i = 0 ; i < size ; i ++)
            newData[i] = data[i];
        data = newData;
    }

    @Override
    public void sort(){
        quickSort(data, 0, size-1);
    }

    public void quickSort(E[] arr,int low,int high){
        int i,j;
        E temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //First look on the right, and then decrease to the left
            while ( temp.compareTo(arr[j])<=0 &&i<j ) {
                j--;
            }
            //Look on the left side and increase to the right in turn
            while (temp.compareTo(arr[i])>=0 &&i<j) {
                i++;
            }
            //Exchange if conditions are met
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        arr[low] = arr[i];
        arr[i] = temp;

        quickSort(arr, low, j-1);
        quickSort(arr, j+1, high);
    }

}