class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();

        long val = 1;

        for(int i = 1; i <= rowIndex; i++){
            res.add((int) val);

            val = val * (rowIndex - i + 1) / i;
        }

        res.add(1);

        return res;
    }
}