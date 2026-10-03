class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // baseline index

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else { // c == ')'
                stack.pop();

                if (stack.isEmpty()) {
                    // This ')' cannot be matched; use it as new baseline
                    stack.push(i);
                } else {
                    // Valid substring from stack.peek() + 1 to i
                    int len = i - stack.peek();
                    maxLen = Math.max(maxLen, len);
                }
            }
        }

        return maxLen;
    }
}