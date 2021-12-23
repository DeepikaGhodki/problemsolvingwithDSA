package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author deepika
 * https://adventofcode.com/2021/day/8
 */
public class SevenSegmentDisplay {
    static Map<Integer, String> map = new HashMap<>();
    static Map<Character, Character> mapping = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        List<String> codes = new ArrayList<>();
        File file = new File("data/adventOfCode/Day8_2");
        Scanner sc = new Scanner(new File(file.getAbsolutePath()));
        int count=0;

        map.put(0, "abcefg");
        map.put(1, "cf");
        map.put(2, "acdeg");
        map.put(3, "acdfg");
        map.put(4, "bcdf");
        map.put(5, "abdfg");
        map.put(6, "abdefg");
        map.put(7, "acf");
        map.put(8, "abcdefg");
        map.put(9, "abcdfg");

        while(sc.hasNext()){
            String line = sc.nextLine();
            String arr[] = getArray(line);
            count += count1478(arr);
            codes = Arrays.asList(arr);
        }
        sc.close();
        System.out.println(count);
    }

    static  String[] getArray(String line){
        String in = line.substring(line.indexOf("|")+1);
        String[] arr = in.split(" ");
        return arr;
    }
    static int count1478(String[] in){
        Integer[] len = {2, 3, 4, 7};
        List<Integer> lenList = Arrays.asList(len);
        int count =0;
        for(String s:in){
            if(lenList.contains(s.length())) {
                if(s.length()==2){
                    String original = map.get(1);
                    mapping.put(original.charAt(0), s.charAt(0));
                    mapping.put(original.charAt(1), s.charAt(1));
                }
                if(s.length()==3){
                    String original = map.get(7);
                    mapping.put(original.charAt(0), s.charAt(0));
                    mapping.put(original.charAt(1), s.charAt(1));
                }
                count++;
            }
        }
        return count;
    }
}
