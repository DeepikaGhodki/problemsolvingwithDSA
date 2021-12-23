package adventOfCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author deepika
 * https://adventofcode.com/2021/day/4
 */
public class Bingo {
    public static void main(String[] args) throws FileNotFoundException {

        List<List<List<Integer>>> boards = new ArrayList<>();

        File file = new File("/Users/DG/IdeaProjects/problemsolvingwithDSA/data/adventOfCode/Day4");
        File file1 = new File("/Users/DG/IdeaProjects/problemsolvingwithDSA/data/adventOfCode/Day4_2");
        Scanner sc = new Scanner(file1);
        String in = sc.nextLine();
        String[] input = in.split(",");
        List<List<Integer>> board = new ArrayList<>();
        while(sc.hasNext()){
            sc.nextLine();
            List<List<Integer>> row = new ArrayList<>();
            for(int i=0; i<5; i++){
                List<Integer> col = new ArrayList<>();
                for(int j=0; j<5;j++) {
                    col.add(j, sc.nextInt());
                }
                row.add(col);

            }
            boards.add(row);
        }
        int index=-1;
        int last=0;
        //find first winner board
        for(String s: input){
            for(int i=0; i<boards.size(); i++){
                List<List<Integer>> b = boards.get(i);
                if(markFirst(b, Integer.parseInt(s))){
                    index = i;
                    last = Integer.parseInt(s);
                    break;
                }
            }
            if(index!=-1) break;
        }
        int sum=0;
        List<List<Integer>> bingo = boards.get(index);
        for(int i=0; i<5; i++){
            for(int j=0; j<5;j++) {
                sum+= bingo.get(i).get(j);
            }
        }
        System.out.println(sum*last);

        //find last winner board
        int indexl=-1;
        int lastl=0;
        int count=0;
        List<Integer> alreadyMarkedBoards = new ArrayList<>();
        for(String s: input){
            for(int i=0; i<boards.size(); i++){
                List<List<Integer>> b = boards.get(i);
                //mark boards and add to list
                if(!alreadyMarkedBoards.contains(i) && markFirst(b, Integer.parseInt(s))){
                    if(!alreadyMarkedBoards.contains(i)){
                        alreadyMarkedBoards.add(i);
                    }
                    if(alreadyMarkedBoards.size()== boards.size()){
                        indexl = i;
                        lastl = Integer.parseInt(s);
                        break;
                    }

                }
            }
            if(indexl!=-1) break;
        }

        int suml=0;
        List<List<Integer>> bingol = boards.get(indexl);
        for(int i=0; i<5; i++){
            for(int j=0; j<5;j++) {
                suml+= bingol.get(i).get(j);
            }
        }
        System.out.println(suml*lastl);

    }

    static boolean markFirst(List<List<Integer>> b, int num){
        int r=-1; int c=-1;
        for(int i=0; i<5; i++){
            for(int j=0; j<5;j++) {
                if(b.get(i).get(j) == num){
                 r=i; c=j;
                 b.get(i).set(j,0);
                 break;
                }
            }
            if(r!=-1 && c!=-1) break;
        }
        if(r!=-1 && c!=-1){
            //check row
            int j=0;
            for(; j<5;j++){
                if(b.get(r).get(j) != 0){
                    break;
                }
            }
            if(j==5) return true;

            //check column
            int i=0;
            for(; i<5;i++){
                if(b.get(i).get(c) != 0){
                    break;
                }
            }
            if(i==5) return true;
        }
        return false;
    }
}
