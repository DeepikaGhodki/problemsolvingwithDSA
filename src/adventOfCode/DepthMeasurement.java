package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author deepika
 * https://adventofcode.com/2021/day/1
 */
public class DepthMeasurement {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data/adventOfCode/Day1");
        Scanner sc = new Scanner(file);
        ArrayList<Integer> list = new ArrayList<>();
        while(sc.hasNext()) {
                list.add(sc.nextInt());
            }
        sc.close();
        Integer[] arr = {199, 200, 208, 210, 200, 207, 240, 269, 260, 263};
        List<Integer> list1 = Arrays.asList(arr);
        System.out.println(getDepthSlidingWindow(list));
        }

    public static int getDepth(List<Integer> list){
        int count =0;
        for(int i=1; i<list.size(); i++){
            if(list.get(i-1) < list.get(i)) count++;
        }
        return count;
    }

    public static int getDepthSlidingWindow(List<Integer> list){
        int count =0;
        int sum = list.get(0) + list.get(1) + list.get(2);
        for(int i=3; i<list.size(); i++){
            int newSum = sum - list.get(i-3) + list.get(i);
            if(newSum > sum)
                count++;
            sum = newSum;
        }
        return count;
    }
}
