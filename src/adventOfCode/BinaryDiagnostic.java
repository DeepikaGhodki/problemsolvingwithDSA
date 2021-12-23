package adventOfCode;

import java.util.*;

/**
 * @author deepika
 * https://adventofcode.com/2021/day/3
 */
public class BinaryDiagnostic {

    public static void main(String[] args) {
        String[] arr = {"00100","11110","10110","10111","10101","01111","00111","11100","10000","11001","00010","01010"};
        List<String> list = Arrays.asList(arr);
        List<String> listInput = getInput();

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        getProduct(listInput,gamma,epsilon);
        filterOnBitRiteria(listInput);

    }

    public static List<String> getInput(){
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while(sc.hasNext()) {
            list.add(sc.nextLine());
        }
        sc.close();
        return list;
    }

    public static void getProduct(List<String> list, StringBuilder gamma, StringBuilder epsilon){

        int len = list.get(0).length();
        for(int i=0; i<len; i++){
            int count = 0;
            for(String s: list){
                if(s.charAt(i) == '1') count++;
                else count--;
            }
            if(count<0) {gamma.append('0'); epsilon.append('1');}
            else {gamma.append('1'); epsilon.append('0');}
        }
        int res = Integer.parseInt(gamma.toString(),2);
        int res1 = Integer.parseInt(epsilon.toString(),2);
        System.out.println(epsilon);
        System.out.println(res*res1);
    }

    public static void filterOnBitRiteria(List<String> list){
        List<String> copy = new ArrayList<>(list);
        List<String> copy1 = new ArrayList<>(list);
        String o2="";
        String co2="";
        int len = list.get(0).length();
        ListIterator<String> itr;
        ListIterator<String> itr1;
        for(int i=0; i<len; i++){
            int count =0;
            for(String s: copy){
                if(s.charAt(i) == '1') count++;
                else count--;
            }
            itr = copy.listIterator();
            while(itr.hasNext()) {
                String s = itr.next();
                if (count < 0 && s.charAt(i) == '1') {
                    //remove ones
                    itr.remove();
                }
                if (count >= 0 && s.charAt(i) == '0') {
                    //remove ones
                    itr.remove();
                }
                if (copy.size() == 1) {
                    o2 = copy.get(0);break;
                }
            }
            count=0;
            for(String s: copy1){
                if(s.charAt(i) == '1') count++;
                else count--;
            }
            itr1 = copy1.listIterator();
            while(itr1.hasNext()) {
                String s = itr1.next();
                if (count < 0 && s.charAt(i) == '0') {
                    //remove ones
                    itr1.remove();
                }
                if (count >= 0 && s.charAt(i) == '1') {
                    //remove ones
                    itr1.remove();
                }
                if (copy1.size() == 1) {
                    co2 = copy1.get(0);break;
                }
            }
        }
        System.out.println(co2);
        System.out.println(Integer.parseInt(o2,2));
        System.out.println(Integer.parseInt(co2,2));
        System.out.println(Integer.parseInt(o2,2)* Integer.parseInt(co2,2));

    }

}
