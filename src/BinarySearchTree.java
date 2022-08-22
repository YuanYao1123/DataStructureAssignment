import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> implements Container<E> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean addElement(E e){
        return add(e) != null;
    }

    public Node add(E e){
        root = add(root, e);
        return root;
    }

    private Node add(Node node, E e){

        if(node == null){
            size ++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if(e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    @Override
    public E searchBy(E e){
        return searchBy(root, e);
    }

    private E searchBy(Node node, E e){

        if(node == null)
            return null;

        if(e.compareTo(node.e) == 0)
            return node.e;
        else if(e.compareTo(node.e) < 0)
            return searchBy(node.left, e);
        else // e.compareTo(node.e) > 0
            return searchBy(node.right, e);
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){

        if(node == null)
            return;

        System.out.print(node.e+"  ");
        preOrder(node.left);
        preOrder(node.right);
    }


    @Override
    public ArrayList<E> print(){
        ArrayList<E> studentArrayList=new ArrayList<>();
        inOrderAsList(root,studentArrayList);
        return studentArrayList;
    }

    @Override
    public void sort() {
        return;
    }


    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){

        if(node == null)
            return;

        inOrder(node.left);
        System.out.print(node.e+"  ");
        inOrder(node.right);
    }

    private void inOrderAsList(Node node,ArrayList<E> studentArrayList){
        if(node == null)
            return;
        inOrderAsList(node.left,studentArrayList);
        studentArrayList.add(node.e);
        inOrderAsList(node.right,studentArrayList);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){

        if(node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e+"  ");
    }


    public E minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BinarySearchTree is empty!");

        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public E maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BinarySearchTree is empty");

        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right == null)
            return node;

        return maximum(node.right);
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }


    @Override
    public void removeBy(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){

        if( node == null )
            return null;

        if( e.compareTo(node.e) < 0 ){
            node.left = remove(node.left , e);
            return node;
        }
        else if(e.compareTo(node.e) > 0 ){
            node.right = remove(node.right, e);
            return node;
        }
        else{   // e.compareTo(node.e) == 0

            if(node.left == null ){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if(node.right == null ){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }


            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }




    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e +"\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }
}