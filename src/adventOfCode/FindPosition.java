package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author deepika
 * https://adventofcode.com/2021/day/2
 * forward 5
 * down 5
 * forward 8
 * up 3
 * down 8
 * forward 2
 * Output = 150 (15*10)
 *
 * For second case = 900
 *
 * Data input->1459206
 */
public class FindPosition {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data/adventOfCode/Day2");
        Scanner sc = new Scanner(file);
//        List<List<Integer>> list = new ArrayList<>();
        int x_1 =0;
        int y_1 =0;
        int x =0;
        int y =0;
        int aim =0;
        while(sc.hasNext()) {
            String next = sc.next();
            int pos = sc.nextInt();
            if(next.equalsIgnoreCase( "forward")){
                x_1+=pos;
                x+=pos;
                y+=(aim*pos);
            }
            else if(next.equalsIgnoreCase("down")){
                y_1+=pos;
                aim+=pos;
            }
            else if(next.equalsIgnoreCase("up")) {
                y_1-=pos;
                aim-=pos;
            }
        }
        sc.close();
//        System.out.println(x_1*y_1);
        System.out.println(x*y);
    }

}
