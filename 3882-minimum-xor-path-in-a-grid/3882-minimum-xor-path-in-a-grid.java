class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[j][x] = true if XOR value x is reachable at cell (i, j)
        boolean[][] dp = new boolean[n][1024];

        // Start at (0, 0)
        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            boolean[][] next = new boolean[n][1024];

            for (int j = 0; j < n; j++) {
                int value = grid[i][j];

                // From the cell above
                if (i > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[j][x]) {
                            next[j][x ^ value] = true;
                        }
                    }
                }

                // From the cell on the left
                if (j > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (next[j - 1][x]) {
                            next[j][x ^ value] = true;
                        }
                    }
                }

                // Starting cell
                if (i == 0 && j == 0) {
                    next[0][grid[0][0]] = true;
                }
            }

            dp = next;
        }

        // Return the smallest reachable XOR at bottom-right
        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}