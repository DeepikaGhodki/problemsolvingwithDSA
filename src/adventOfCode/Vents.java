package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author deepika
 * 0,9 -> 5,9
 * 8,0 -> 0,8
 * 9,4 -> 3,4
 * 2,2 -> 2,1
 * 7,0 -> 7,4
 * 6,4 -> 2,0
 * 0,9 -> 2,9
 * 3,4 -> 1,4
 * 0,0 -> 8,8
 * 5,5 -> 8,2
 */
public class Vents {

    public static void main(String[] args) throws FileNotFoundException {

        Map<Integer, List<Integer>> grid = new HashMap();
        List<String> points = new ArrayList<>();
        File file = new File("data/adventOfCode/Day5");
        File file1 = new File("data/adventOfCode/Day5_2");
        Scanner sc = new Scanner(file1);
        int count =0;
        while(sc.hasNext()){
            String[] point1 = sc.next().split(",");
            int x1 = Integer.parseInt(point1[0]);
            int y1 = Integer.parseInt(point1[1]);
            sc.next();
            String[] point2 = sc.next().split(",");
            int x2 = Integer.parseInt(point2[0]);
            int y2 = Integer.parseInt(point2[1]);
            if(x1==x2){
                //add all in same list
                int max = Math.max(y1,y2);
                int min = Math.min(y1,y2);
                List<Integer> row;
                if(grid.containsKey(x1))
                    row = grid.get(x1);
                else
                    row = new ArrayList<>();
                for(int i=min; i<=max; i++){
                    String point = x1+","+i;
                    if(row.contains(i)){
                        if(!points.contains(point)){
                           points.add(point); //count on second occurrence
                           count++;
                        }
                    }else{
                        row.add(i);
                    }
                    grid.put(x1,row);

                }
            }else if(y1==y2){
                int max = Math.max(x1,x2);
                int min = Math.min(x1,x2);

                for(int i=min; i<=max; i++){
                    String point = i+","+y1;
                    List<Integer> row;
                    if(grid.containsKey(i))
                        row = grid.get(i);
                    else
                        row = new ArrayList<>();
                    if(row.contains(y1)){
                        if(!points.contains(point)){
                            points.add(point); //count on second occurrence
                            count++;
                        }
                    }else{
                        row.add(y1);
                    }
                    grid.put(i, row);
                }
            }
            else if(Math.abs(x1-x2) == Math.abs(y1-y2)){
                if(x1-x2 == y1-y2) {
                    //x1>x2 && y1>y2
                    if (x2 > x1 && y2 > y1) {
                        int tempx = x1;
                        x1 = x2;
                        x2 = tempx;
                        int tempy = y1;
                        y1 = y2;
                        y2 = tempy;
                    }

                    while (x1 >= x2 && y1 >= y2) {
                        count = getCount(grid, points, count, x1, y1);
                        x1--;
                        y1--;
                    }
                }else{
                    if (x1 > x2 && y2 > y1) {
                        int tempx = x1;
                        x1 = x2;
                        x2 = tempx;
                        int tempy = y1;
                        y1 = y2;
                        y2 = tempy;
                    }
                    //x1<x2, y1>y2
                    while(x1<=x2 && y1>=y2){
                        count = getCount(grid, points, count, x1, y1);
                        x1++; y1--;
                    }
                }

            }

        }
        System.out.println(count);

    }

    private static int getCount(Map<Integer, List<Integer>> grid, List<String> points, int count, int x1, int y1) {
        String point = x1 + "," + y1;
        List<Integer> row;
        if (grid.containsKey(x1))
            row = grid.get(x1);
        else
            row = new ArrayList<>();
        if (row.contains(y1)) {
            if (!points.contains(point)) {
                points.add(point); //count on second occurrence
                count++;
            }
        } else {
            row.add(y1);
        }
        grid.put(x1, row);
        return count;
    }
}
