class Solution {
    public boolean isHappy(int n) {
        int s = n;
        int f = getNext(n);
        while (f != 1 && s != f) {
            s = getNext(s);
            f = getNext(getNext(f));
        }
        return f == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}