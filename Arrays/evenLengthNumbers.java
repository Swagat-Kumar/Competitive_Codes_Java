class Solution {
    public int findNumbers(int[] nums) {
        int count=0;
        for(int n:nums){
            if((Math.floor(Math.log10(n))+1)%2==0) count++;
        }
        return count;
    }
}