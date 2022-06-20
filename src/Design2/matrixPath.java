package Design2;

import java.util.Scanner;

public class matrixPath {
    static int[][] DP;
    static int[][] cost;
    static boolean[][] path; //왼쪽은 false, 위는 tru
    public static int N, M;
    public static int INF = 0;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.next());
        M = Integer.parseInt(sc.next());
        cost = new int[N + 1][M + 1];
        DP = new int[N + 1][M + 1];
        path  = new boolean[N + 1][M + 1];

        //init
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= M; j++)
                cost[i][j] = INF;
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= M; j++)
                DP[i][j] = INF;
        DP[0][0] = cost[0][0];
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= M; j++)
                path[i][j] = false;

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                cost[i][j] = Integer.parseInt(sc.next());

        operate();
        System.out.println();
        System.out.println(DP[N][M]);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(path[i][j] == true) System.out.print("Up\t");
                else System.out.print("Left\t");
            }
            System.out.println();
        }


        sc.close();
    }

    public static void operate() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(DP[i-1][j] > DP[i][j-1]) { // 위의 값이 더 작은 경로인경우, 위를 우선할 경우 <를 ==로 할것
                    DP[i][j] = DP[i-1][j] + cost[i][j];
                    path[i][j] = true;
                } else{
                    DP[i][j] = DP[i][j-1] + cost[i][j];
                }
            }
        }
    }

    static int mat(int i, int j) {

        if (DP[i][j] != -1)

            return DP[i][j];

        if (i == 1 && j == 1)

            DP[i][j] = cost[i][j];

        else if (i == 1)

            DP[i][j] = mat(1, j - 1) + cost[i][j];

        else if (j == 1)

            DP[i][j] = mat(i - 1, 1) + cost[i][j];

        else

            DP[i][j] = Math.min(mat(i - 1, j), mat(i, j - 1)) + cost[i][j];

        return DP[i][j];
    }
}

/*
4 4
6 7 12 5
5 3 11 18
7 17 3 3
8 10 14 9
 */

/*
for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                System.out.print(cost[i][j] + " ");
            System.out.println();
        }
 */