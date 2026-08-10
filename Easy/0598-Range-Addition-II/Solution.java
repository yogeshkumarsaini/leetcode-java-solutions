class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) return m*n;
        int minRow = m;
        int minCol = n;
        for (int i = 0; i < ops.length; i++) {
            int a = ops[i][0];
            int b = ops[i][1];

            minRow = Math.min(minRow, a);
            minCol = Math.min(minCol, b);
        }
        return minRow * minCol;
    }
}