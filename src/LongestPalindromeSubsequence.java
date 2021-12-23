import java.util.Scanner;

/**
 * @author Deepika Ghodki (dghodki)
 * Print the length of maximum palindrome sequence and the subsequence itself
 * Input from stdin is <input string>
 */
public class LongestPalindromeSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        findLongestPalindrome(input);
    }

    /**
     * converting this problem into finding the longest subsequence between the
     * given string and it's reverse
     * opt[i][j] = max(opt[i-1][j], opt[i][j-1]), characters are not same
     * opt[i][j] = opt[i-1][j-1] + 2,             characters are same
     * @param input
     */
    public static void findLongestPalindrome(String input){
        int len = input.length();
        String reverse = new StringBuilder(input).reverse().toString();

        int[][] opt = new int[len+1][len+1];
        /**
         * can contain 3 values indicating direction of chosen optSeq {d, u, l}
         * d - diagonal, u - up, l - left
         */
        char[][] optSeq = new char[len+1][len+1];
        for(int i=0; i<len+1; i++){ //initialising the 0th row and column
            opt[i][0] = 0;
            opt[0][i] = 0;
        }
        for(int i=1; i<len+1; i++){
            for(int j=1; j<len+1; j++){
                char s = input.charAt(i-1); //character from start of input
                char r = reverse.charAt(j-1); //character from end of input

                if(s==r && i+j < len+1){
                    opt[i][j] = opt[i-1][j-1] + 2; //count as palindrome if not the same character from start and end
                    optSeq[i][j] = 'd';
                }else{
                    //count the last character obtained on traversing from start to end (palindrome of odd length)
                    //counted when we have reached the max possible length
                    if(opt[i - 1][j] == opt[i][j - 1] &&
                                opt[i][j - 1] == opt[i - 1][j - 1] &&
                                    i+j == len+1) {
                            opt[i][j] = opt[i - 1][j - 1] + 1;
                            optSeq[i][j] = 'd';
                    }else {
                        if (opt[i - 1][j] > opt[i][j - 1]) {
                            opt[i][j] = opt[i - 1][j];
                            optSeq[i][j] = 'u';
                        } else {
                            opt[i][j] = opt[i][j - 1];
                            optSeq[i][j] = 'l';
                        }
                    }
                }
            }
        }
        // printing the maximum length of palindrome
        System.out.println(opt[len][len]);

        /**
         * Retracing path to create the longest subsequence based on the direction array
         * creating the first half and combining it with reverse in the last step
         */
        StringBuilder output = new StringBuilder();
        String  last = ""; //in case of odd length palindrome
        int i=len; int j=len;
        while(i>0 && j>0) {
            if (optSeq[i][j] == 'd') {
                if (opt[i][j] % 2 == 0) {
                    output.append(input.charAt(i - 1));
                } else {
                    last = input.charAt(i - 1) + "";
                }
                j--;
                i--;
            } else {
                if (optSeq[i][j] == 'l') {
                    j--;
                } else {
                    i--;
                }
            }
        }
        StringBuilder outputBuilder = new StringBuilder(output).reverse();
        outputBuilder.append(last);
        outputBuilder.append(output);
        // printing the longest palindrome subsequence
        System.out.println(outputBuilder);
    }
}
