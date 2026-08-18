class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: only one subarray (the whole array)
        if (k == n) {
            int max = nums[0];
            for (int x : nums) {
                if (x > max) max = x;
            }
            return max;
        }

        // Count frequencies for k == 1 and for endpoint checks
        int[] freq = new int[51]; // values are in [0, 50] as per constraints
        for (int x : nums) {
            freq[x]++;
        }

        // Case 2: k == 1 -> elements that appear exactly once
        if (k == 1) {
            int ans = -1;
            for (int x = 50; x >= 0; x--) {
                if (freq[x] == 1) {
                    return x;
                }
            }
            return ans;
        }

        // Case 3: 1 < k < n -> only nums[0] and nums[n-1] can be almost missing
        int candidate1 = -1;
        int candidate2 = -1;

        // Check nums[0]
        if (freq[nums[0]] == 1) {
            candidate1 = nums[0];
        }

        // Check nums[n-1]
        if (freq[nums[n - 1]] == 1) {
            candidate2 = nums[n - 1];
        }

        return Math.max(candidate1, candidate2);
    }
}