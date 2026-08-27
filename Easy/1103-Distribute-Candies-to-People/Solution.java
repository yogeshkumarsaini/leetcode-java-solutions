class Solution {
    public int[] distributeCandies(int candies, int num_people) {

        int[] ans = new int[num_people];

        int person = 0;
        int give = 1;

        while (candies > 0) {

            int current = Math.min(give, candies);

            ans[person] += current;

            candies -= current;

            give++;
            person++;

            if (person == num_people) {
                person = 0;
            }
        }

        return ans;
    }
}