import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Deepika Ghodki (dghodki)
 * Minimum heap implementation for input format from stdin:
 * A 300
 * E
 * A 200
 * A 600
 * A 500
 * E
 * A 100
 * E
 * E
 * E
 */
public class MinHeap {

    private static List<Integer> elementList = new ArrayList<>(); //elements of heap
    private static int size=0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String operation = scanner.next();
            switch (operation.toUpperCase()) {
                case "A": {
                    Integer newElement = scanner.nextInt();
                    add(newElement);
                }
                break;
                case "E":
                    extractMin();
                    break;
                default:
                    System.out.println("Invalid argument");
            }
        }
        scanner.close();
    }

    public static void extractMin(){
        System.out.println(elementList.get(0));
        //swapping first with last to avoid arraylist shifting
        swap(0, size-1);
        //removing last element (value of minimum) i.e. deleting the first element
        elementList.remove(size-1);
        size--;
        if(size > 1) {
            heapifyDown(0);
        }
    }

    /**
     * Adds a new element to the min heap and maintains the heap property
     * @param newElement
     */
    public static void add(int newElement){
        elementList.add(newElement);
        heapifyUp(size);
        size++;
    }

    /**
     * Ensures the heap property is maintained by moving elements up
     * if lesser than parent until property is satisfied
     * @param idx - start index
     */
    private static void heapifyUp(int idx){
        if(idx == 0) return;
        int parentIdx;
        if(idx%2 == 0){
            parentIdx = idx/2 -1;
        }else{
            parentIdx = idx/2;
        }
        if(elementList.get(idx).compareTo(elementList.get(parentIdx)) < 0){
            swap(idx, parentIdx);
            heapifyUp(parentIdx);
        }
    }

    /**
     * Ensures the heap property is maintained by moving elements down
     * if greater than any of the child nodes until property is satisfied
     * @param idx - start index
     */
    private static void heapifyDown(int idx){
        // excluding leaf nodes
        if(2 * idx >= size){
            return;
        }
        // base case
        if(size == 2){
            if(elementList.get(0).compareTo(elementList.get(1))  > 0 ){
                swap(0, 1);
                return;
            }
        }
        int child1 = 2* idx + 1;
        int child2 = 2* idx + 2;

        //swapping with the minimum of the two children
        if(child1 < size && child2 < size){
            int min = elementList.get(child1).compareTo(elementList.get(child2)) < 0 ? child1 : child2;
            if(elementList.get(idx).compareTo(elementList.get(min)) > 0){
                swap(idx, min);
                heapifyDown(min);
            }
        }
        //if the node has only one child
        if(child1 < size){
            if(elementList.get(idx).compareTo(elementList.get(child1)) > 0){
                swap(idx, child1);
                heapifyDown(child1);
            }
        }
    }

    /**
     * swap elements at id1 and idx2 with each other
     * @param idx1
     * @param idx2
     */
    private static void swap(int idx1, int idx2){
        int first = elementList.get(idx1);
        int second = elementList.get(idx2);
        elementList.set(idx1, second);
        elementList.set(idx2, first);
    }
}
