//Name: Selah Rice
//Class: CS 3305/W01
//Term: Fall 2023
//Instructor: Sharon Perry
//Assignment: 7 - Part - 1 - Heaps
import java.util.*;
//example from text titles class as Heap<E extends Comparable<E>> but I just used Main

public class Main {
    //arraylist to take in 5 integers
    //example from text uses ArrayList<E>
    private ArrayList<Integer> list = new ArrayList<Integer>();
    //default constructor
    public Main(){}
    //create heap of int from array
    public Main (Integer[] array) {
        for (int i=0; i<array.length; i++) {
            //call add method
            add(array[i]);
        }
    }

    //add int to heap
    public void add(int newNum) {
        list.add(newNum);
        //index of last node
        int currentIndex = list.size()-1;

        while (currentIndex > 0) {
            int parentIndex = (currentIndex-1)/2;

            //swap if current is less than parent
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) < 0) {
                int temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }
            else {
                break;
            }
            currentIndex = parentIndex;
        }
    }

    //remove method
    public Integer remove() {
        if (list.size() == 0) {
            return null;
        }
        //assign root
        int removedNum = list.get(0);
        //new root
        list.set(0,list.get(list.size()-1));
        //remove last
        list.remove(list.size()-1);

        //adjust tree
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            //create left and right side of tree
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            //find max child
            //if true heap is a tree
            if (leftChildIndex >= list.size()) {
                break;
            }

            int maxIndex = leftChildIndex;

            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) > 0) {
                    maxIndex = rightChildIndex;
                }
            }

            //swap current if greater than maximum
            if (list.get(currentIndex).compareTo(list.get(maxIndex)) > 0) {
                int temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }
            else {
                break;
            }
        }
        return removedNum;
    }

    //to get nodes amount in tree if needed
    public int getSize () {
        return list.size();
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[5];
        Scanner scan = new Scanner(System.in);

        //user input 5 num
        for (int i=0; i < 5; i++) {
            System.out.println("Enter 5 num: ");
            int num = scan.nextInt();
            array[i] = num;
        }

        //print array before heapify
        System.out.println("Array before min-heap: ");
        for (int i=0; i<array.length; i++) {
            System.out.print(array[i]+ " ");
        }

        //make min-heap and add elements from array
        Main heap = new Main(array);

        //remove min-heap elements to show it is sorted
        System.out.println("\nArray after min-heap: ");
        for (int i=0; i < array.length; i++) {
            System.out.print(heap.remove() +" ");
        }
    }
}