class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        boolean hasOdd = false;
        boolean hasEven = false;

        // First pass: find min odd and check parity presence
        for (int x : nums1) {
            if ((x & 1) == 1) { // odd
                hasOdd = true;
                if (x < minOdd) minOdd = x;
            } else {
                hasEven = true;
            }
        }

        // If all same parity, always possible
        if (!hasOdd || !hasEven) return true;

        // Mixed parity: check if any even < minOdd
        for (int x : nums1) {
            if ((x & 1) == 0 && x < minOdd) {
                return false;
            }
        }

        return true;
    }
        
}
