public interface Container <E>{


    int getSize();

    boolean addElement(E e);

    /**
     * delete by studentID
     * @param e
     */
    void removeBy(E e);   //by index

    /**
     * delete by studentID
     * @param e
     * @return
     */
    E searchBy(E e);      //by index

    String print();

    void sort();

}
