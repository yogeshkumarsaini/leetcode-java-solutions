class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            int temp = i;
            boolean isSelf = true;

            while (temp > 0) {
                int digit = temp % 10;

                if (digit == 0 || i % digit != 0) {
                    isSelf = false;
                    break;
                }

                temp /= 10;
            }

            if (isSelf) {
                list.add(i);
            }
        }

        return list;
    }
}