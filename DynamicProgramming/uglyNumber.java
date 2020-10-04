package DynamicProgramming;

import java.util.Scanner;

class Solution {
    long getNthUglyNo(int n) {
        // code here
        long[] listA = new long[10001];
        int c = 0;
        listA[c] = 1;
        int i = 0;
        int j = 0;
        int k = 0;
        while (c < n) {
            long nextU = Math.min(listA[i] * 2, listA[j] * 3);
            nextU = Math.min(nextU, listA[k] * 5);
            if (nextU == listA[i] * 2)
                i++;
            if (nextU == listA[j] * 3)
                j++;
            if (nextU == listA[k] * 5)
                k++;
            listA[++c] = nextU;
        }
        return listA[c - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        Solution ob = new Solution();
        System.out.println(ob.getNthUglyNo(x));
        sc.close();
    }
}
