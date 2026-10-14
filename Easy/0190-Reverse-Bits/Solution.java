class Solution {
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            // result ko left shift karo
            result = result << 1;

            // n ka last bit nikalo
            int bit = n & 1;

            // bit ko result me add karo
            result = result | bit;

            // n ko right shift karo
            n = n >> 1;
        }

        return result;
    }
}