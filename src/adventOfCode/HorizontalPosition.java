package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author deepika
 * https://adventofcode.com/2021/day/7
 */
public class HorizontalPosition {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("data/adventOfCode/Day7");
        Scanner sc = new Scanner(file);
        String[] input = sc.nextLine().split(",");
        List<Integer> positions = new ArrayList<>();
        for(String s: input){
            positions.add(Integer.parseInt(s));
        }
//        Integer[] input1 = {16,1,2,0,4,2,7,1,2,14};
//        positions = Arrays.asList(input1);
        Collections.sort(positions);
        //mid point
        int mid = positions.get(positions.size()/2);
        int dist = 0;
        for(int i:positions){
            dist += Math.abs(mid-i);
        }
        System.out.println(dist);
        //multiplicative distance
        int sum = 0;
        for(int i:positions){
            sum += i;
        }
        double m = (double) sum/ positions.size();
        int d1 = (int) Math.floor( m+0.5);
        int d2 = (int) Math.floor( m-0.5);

        int multiDist = 0;
        int multiDist2 = 0;
        for(int i:positions){
            int diff = Math.abs(d1-i);
            int diff2 = Math.abs(d2-i);
            int cost = (diff * (diff+1))/2;
            int cost2 = (diff2 * (diff2+1))/2;
//            System.out.println(i+ ":"+ cost);
            multiDist += cost;
            multiDist2 += cost2;
        }
        System.out.println(multiDist);
        System.out.println(multiDist2);
    }
}
