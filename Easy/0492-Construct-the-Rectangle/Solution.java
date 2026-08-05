class Solution {
    public int[] constructRectangle(int area) {
        int w= (int)Math.sqrt(area);
        while(w>0){
            if(area%w==0){
               int l=area/w;
                return new int[]{l, w};
            }
            w--;
        }
        return new int[]{0,0};
    }
}