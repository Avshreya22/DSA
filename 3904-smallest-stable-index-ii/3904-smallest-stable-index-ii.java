class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // 1. Build suffix minimum array: suffixMin[i] = min(nums[i..n-1])
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // 2. Scan left to right, maintaining prefix maximum
        int maxLeft = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            if (maxLeft - suffixMin[i] <= k) {
                return i; // smallest stable index
            }
        }

        return -1; // no stable index
        
        
    }
}