class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] freq = new int[10001];

        for(int card : deck){
            freq[card]++;
        }
        int gcd = 0;

        for (int count : freq) {
            if (count > 0) {
                gcd = gcd(gcd, count);

                if (gcd == 1) {
                    return false;
                }
            }
        }

        return gcd >= 2;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;

    }
}