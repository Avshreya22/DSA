class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];
        for (int i = 0; i < n; i++) sq[i] = (long) nums[i] * nums[i];
        Arrays.sort(sq);

        long ans = 0;
        int minus = n / 2;
        for (int i = 0; i < n; i++) {
            ans += (i < minus) ? -sq[i] : sq[i];
        }
        return ans;
    }
}