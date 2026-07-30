class Solution {
    public boolean isPerfectSquare(int num) {
        int l = 1;
        int r = num;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long sqrt = mid * mid;

            if (sqrt == num)
                return true;
            else if (sqrt < num)
                l = (int) mid + 1;
            else
                r = (int) mid - 1;
        }
        return false;
    }
}