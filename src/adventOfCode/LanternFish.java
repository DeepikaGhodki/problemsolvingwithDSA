package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author deepika
 * https://adventofcode.com/2021/day/6
 */
public class LanternFish {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data/adventOfCode/Day6");
        Scanner sc = new Scanner(file);
        Map<Integer, Long> oldValue = new HashMap<>();
        Map<Integer, Long> newValue = new HashMap<>();
        long count = 0;
//        String[] fish = {"3","4","3","1","2"};
        String[] fish = sc.nextLine().split(",");
        for(int i=0; i<9; i++){
            oldValue.put(i,0l);
            newValue.put(i,0l);
        }
        for(String s: fish){
            int val = Integer.parseInt(s);
            oldValue.computeIfPresent(val, (k, v)->v+1);
            newValue.computeIfPresent(val, (k, v)->v+1);
        }
        int days = 256;
        for(int i=1; i<=days; i++){
            //calculate new from old
            for(int k: oldValue.keySet()){
                newValue.put(k, oldValue.get((k+1)%9));
            }
            if(oldValue.get(0)>0){
                newValue.compute(6, (k,v)-> v + oldValue.get(0));
               // newValue.compute(8, (k,v)-> v + oldValue.get(0));
            }
            //copying new into old
            for(int k: oldValue.keySet()){
                oldValue.put(k, newValue.get(k));
            }
        }
        for(Long k: newValue.values()){
            count+= k;
        }
        System.out.println(count);
    }
}
