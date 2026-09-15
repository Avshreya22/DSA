class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // dp[i] = maximum answer for the first i characters
        int[] dp = new int[n + 1];

        for (int end = 1; end <= n; end++) {
            // Option 1: skip the character at end - 1
            dp[end] = dp[end - 1];

            // Check palindrome of length k
            if (end >= k && isPalindrome(s, end - k, end - 1)) {
                dp[end] = Math.max(dp[end], dp[end - k] + 1);
            }

            // Check palindrome of length k + 1
            if (end >= k + 1 && isPalindrome(s, end - k - 1, end - 1)) {
                dp[end] = Math.max(dp[end], dp[end - k - 1] + 1);
            }
        }

        return dp[n];
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}