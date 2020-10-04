package FloodFill;

//GFG
class Solution {
    static int maximumPath(int N, int Matrix[][]) {
        // code here
        if (N == 1)
            return Matrix[0][0];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (r == 0)
                    continue;
                if (c == 0) {
                    Matrix[r][c] = Matrix[r][c] + Math.max(Matrix[r - 1][c + 1], Matrix[r - 1][c]);
                    continue;
                }
                if (c == N - 1) {
                    Matrix[r][c] = Matrix[r][c] + Math.max(Matrix[r - 1][c - 1], Matrix[r - 1][c]);
                    continue;
                }
                int maxe = Math.max(Matrix[r - 1][c - 1], Matrix[r - 1][c]);
                maxe = Math.max(maxe, Matrix[r - 1][c + 1]);
                Matrix[r][c] = Matrix[r][c] + maxe;
            }
        }
        int max = -1;
        for (int c = 0; c < N; c++) {
            if (Matrix[N - 1][c] > max)
                max = Matrix[N - 1][c];
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] x = { { 2, 2 }, { 2, 2 } };
        System.out.println(Solution.maximumPath(2, x));
    }
}
