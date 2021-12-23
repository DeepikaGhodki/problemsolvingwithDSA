/**
 * @author deepika
 * https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 */
public class KthSmallestTwoSortedArrays {
    public static void main(String[] args) {
        int[] A = {2, 3, 6};
        int[] B = {1, 4, 8, 10};
        int k=3;
        System.out.println(kthSmallest(A, B, k));
    }

    public static int kthSmallest(int[] A, int[] B, int k){
        int kth = kthSmallest(A, B, 0, 0,  k);
        return kth;
    }

    public static int kthSmallest(int[] A, int[] B, int s1,  int s2,  int k){
        System.out.println("k:"+ k);
        System.out.println("s1:"+ s1);
        System.out.println("s2:"+ s2);
        if(s1>=A.length){
            return B[s2 + k];
        }
        if(s2>=B.length){
            return A[s1 + k];
        }
        if(k==0){
            return Math.min(A[s1], B[s2]);
        }

        int kBy2 = (int)Math.floor(k/2); //2 -> 1

        if(A[s1 + kBy2 - 1] < B[s2 + kBy2 - 1]){ //A[0+2-1], B[0+2-1] -> A[2+1-1], B[0+1-1] 12>2
            s1 = s1 + kBy2; //5<7 -> s1+2 = 2  ->
        }else{
            s2 = s2 + kBy2; //s2+1 = 1
        }
        return kthSmallest(A, B, s1, s2, kBy2); //1
    }
}
