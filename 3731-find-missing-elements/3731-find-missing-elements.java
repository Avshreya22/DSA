class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int mn = nums[0], mx = nums[0];
        for (int x : nums) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
        }

        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        List<Integer> ans = new ArrayList<>();
        for (int x = mn + 1; x < mx; x++) {
            if (!set.contains(x)) {
                ans.add(x);
            }
        }

        return ans;
    }    
}