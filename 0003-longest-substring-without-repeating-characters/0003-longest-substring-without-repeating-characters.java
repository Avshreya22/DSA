class Solution {
    public int lengthOfLongestSubstring(String s) {
                boolean[] seen = new boolean[128];
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (seen[c]) {                  // if repeated
                seen[s.charAt(left)] = false;  // remove left char
                left++;
            }

            seen[c] = true;                    // mark current char
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
        
    }
}