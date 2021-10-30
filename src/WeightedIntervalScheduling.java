import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author deepika
 * Pick the intervals such that they are non-overlapping and maximise profit
 * Source: https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 */
public class WeightedIntervalScheduling {

    /**
     * [1,2,3,4,6]
     * [3,5,10,6,9]
     * [20,20,100,70,60]
     * Answer: 150
     */
    public static void main(String[] args) {
        WeightedIntervalScheduling weightedIntervalScheduling = new WeightedIntervalScheduling();
        /**int[] s = {1,2,3,4,6};
        int[] f = {3,5,10,6,9};
        int[] p = {20,20,100,70,60}; **/

        //answer = 998
        /**
        int[] s= {341,22,175,424,574,687,952,439,51,562,962,890,250,47,945,914,835,937,419,343,125,809,
                807,959,403,861,296,39,802,562,811,991,209,375,78,685,592,409,369,478,417,162,938,298,
                618,745,888,463,213,351,406,840,779,299,90,846,58,235,725,676,239,256,996,362,819,622,
                449,880,951,314,425,127,299,326,576,743,740,604,151,391,925,605,770,253,670,507,306,
                294,519,184,848,586,593,909,163,129,685,481,258,764};
        int[] f= {462,101,820,999,900,692,991,512,655,578,996,979,425,893,975,960,930,991,987,524,208,
                901,841,961,878,882,412,795,937,807,957,994,963,716,608,774,681,637,635,660,750,632,948,
                771,943,801,985,476,532,535,929,943,837,565,375,854,174,698,820,710,566,464,997,551,884,
                844,830,916,970,965,585,631,785,632,892,954,803,764,
                283,477,970,616,794,911,771,797,776,686,895,721,917,920,975,984,996,471,770,656,977,922};
        int[] p ={85,95,14,72,17,3,86,65,50,50,42,75,40,87,35,78,47,74,92,10,100,29,55,57,51,34,10,96,14,
                71,63,99,8,37,16,71,10,71,83,88,68,79,27,87,3,58,56,43,89,31,16,9,49,84,62,30,35,7,27,34,
                24,33,100,25,90,79,58,21,31,30,61,46,36,45,85,62,91,54,28,63,50,69,48,36,77,39,19,97,20,
                39,48,72,37,67,72,46,54,37,53,30};
         **/
        //Answer = 45
        int[] s= {6,24,45,27,13,43,47,36,14,11,11,12};
        int[] f= {31,27,48,46,44,46,50,49,24,42,13,27};
        int[] p= {14,4,16,12,20,3,18,6,9,1,2,8};

        System.out.println(weightedIntervalScheduling.jobScheduling(s, f, p));

    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        int[] finishTime = new int[profit.length];
        for (int i = 0; i < finishTime.length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            jobs.add(currJob);
        }
        Collections.sort(jobs, new JobComparator());
        // jobs.sort(Comparator.comparingInt(a -> a.get(0)));

        for (int i = 0; i < finishTime.length; i++) {
            finishTime[i] = jobs.get(i).get(1);
        }

        int[] opt = new int[profit.length];
        int p[] = new int[profit.length];
        p[0] = -1;
        for(int i=0; i<p.length; i++){
            p[i] = binarySearch(finishTime, 0, profit.length, jobs.get(i).get(0));
        }
        //populating opt array
        opt[0] = jobs.get(0).get(2);

        for(int i=1; i<profit.length; i++){
            int p_0;
            if(p[i]==-1) p_0 = 0;
            else p_0= opt[p[i]];
            if(opt[i-1] > jobs.get(i).get(2) + p_0)
                opt[i] = opt[i-1];
            else
                opt[i] = jobs.get(i).get(2)+ p_0;
        }
        return opt[profit.length-1];

    }

    int binarySearch(int[] fin, int s, int e, int num){
        if(s==e && num>=fin[s]) return s;
        if(s>=e){
            return -1;
        }
        int mid =  s+ (int) Math.ceil((double)(e-s)/2);

        if(fin[mid] == num) return mid;
        if(fin[mid] < num){
            if(fin[mid+1]>num){
                return mid;
            }
            return binarySearch(fin, mid+1, e, num);
        }
        return binarySearch(fin, s, mid-1, num);
    }
    int linearSearch(int[] arr, int s, int e, int num){
        for(int i =e; i>=0; i--){
            if(num >= arr[i]){
                return i;
            }
        }
        return -1;
    }
}



    class JobComparator implements Comparator<List<Integer>> {

        // override the compare() method
        public int compare(List<Integer> s1, List<Integer> s2)
        {
            if (s1.get(1) > s2.get(1))
                return 1;
            else
                return -1;
        }
    }

