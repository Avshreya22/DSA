class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Store first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';

            first[index] = Math.min(first[index], i);
            last[index] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try creating a valid interval for every character
        for (int ch = 0; ch < 26; ch++) {
            if (last[ch] == -1) {
                continue;
            }

            int left = first[ch];
            int right = last[ch];
            boolean valid = true;

            int i = left;

            while (i <= right) {
                int current = s.charAt(i) - 'a';

                // This character appeared before the interval
                if (first[current] < left) {
                    valid = false;
                    break;
                }

                // Include all occurrences of this character
                right = Math.max(right, last[current]);
                i++;
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort intervals by ending index
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();
        int previousEnd = -1;

        // Greedily choose non-overlapping intervals
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];

            if (left > previousEnd) {
                answer.add(s.substring(left, right + 1));
                previousEnd = right;
            }
        }

        return answer;
    }
}