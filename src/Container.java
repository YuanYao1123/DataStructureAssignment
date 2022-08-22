import java.util.ArrayList;

public interface Container <E>{

    /**
     * add a student
     * param: student object
     * return: whether add succeeded
     */
    boolean addElement(E e);

    /**
     * delete by studentName
     * @param e
     * return the deleted student
     */
    void removeBy(E e);   //by student name

    /**
     * search a student by studentName
     * @param e
     * return the information of the student
     */
    E searchBy(E e);      //by student name

    /**
     * print all students' info
     * return a string contained all students' info
     */
    ArrayList<E> print();

    /**
     * sort student by student name
     */
    void sort();

    int getSize();

}
