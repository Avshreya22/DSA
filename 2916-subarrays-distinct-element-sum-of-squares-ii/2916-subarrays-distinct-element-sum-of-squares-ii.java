class Solution {
    static final int M = 1_000_000_007;
    long[] sum, sq, lz;

    void apply(int o, int len, long k) {
        sq[o] = (sq[o] + 2 * k % M * sum[o] + k * k % M * len) % M;
        sum[o] = (sum[o] + k * len) % M;
        lz[o] += k;
    }

    void push(int o, int l, int r) {
        if (lz[o] != 0) {
            int mid = (l + r) >>> 1;
            apply(2 * o, mid - l + 1, lz[o]);
            apply(2 * o + 1, r - mid, lz[o]);
            lz[o] = 0;
        }
    }

    void update(int o, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            apply(o, r - l + 1, 1);
            return;
        }
        push(o, l, r);
        int mid = (l + r) >>> 1;
        if (ql <= mid) update(2 * o, l, mid, ql, qr);
        if (qr > mid) update(2 * o + 1, mid + 1, r, ql, qr);
        sum[o] = (sum[2 * o] + sum[2 * o + 1]) % M;
        sq[o] = (sq[2 * o] + sq[2 * o + 1]) % M;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lz = new long[4 * n];
        Map<Integer, Integer> last = new HashMap<>();

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int p = last.getOrDefault(nums[i], -1);
            update(1, 0, n - 1, p + 1, i);
            ans = (ans + sq[1]) % M;
            last.put(nums[i], i);
        }
        return (int) ans;
    }
}