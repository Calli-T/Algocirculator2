package Design2;

import java.util.Scanner;

public class Floyd_path_adjacentMatrix {
    public static int INF = 100000000;

    public static void main(String args[]) {
        int n;

        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.next());
        int W[][] = new int[n + 1][n + 1]; // Edge
        int D[][] = new int[n + 1][n + 1]; // 최단거리
        int P[][] = new int[n + 1][n + 1]; // 경로

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int number = Integer.parseInt(sc.next());
                if (number == -1)
                    W[i][j] = INF;
                else
                    W[i][j] = number;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (W[i][j] == INF)
                    System.out.print("INF\t");
                else
                    System.out.print(W[i][j] + "\t");
            }
            System.out.println();
        }

        floyd2(n, W, D, P);
    }

    public static void floyd2(int n, int W[][], int D[][], int P[][]) {
        int i, j, k;
        for (i = 1; i <= n; i++)
            for (j = 1; j <= n; j++)
                P[i][j] = 0;
        D = W;
        for (k = 1; k <= n; k++) {
            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j++) {
                    if (D[i][k] + D[k][j] < D[i][j]) {
                        P[i][j] = k;
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }

            System.out.println();
            System.out.println("D^" + k);
                    for (int ii = 1; ii <= n; ii++) {
                        for (int jj = 1; jj <= n; jj++) {
                            if (D[ii][jj] == INF)
                                System.out.print("INF\t");
                            else
                                System.out.print(D[ii][jj] + "\t");
                }
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("P");
        for (int ii = 1; ii <= n; ii++) {
            for (int jj = 1; jj <= n; jj++) {
                if (P[ii][jj] == INF)
                    System.out.print("INF\t");
                else
                    System.out.print(P[ii][jj] + "\t");
            }
            System.out.println();
        }

        Scanner sc = new Scanner(System.in);
        int temp1 = Integer.parseInt(sc.next());
        int temp2 = Integer.parseInt(sc.next());

        System.out.print("v" + temp1 + " ");
        path(temp1, temp2, P);
        System.out.print("v" + temp2 + " ");
    }

    static void path(int q, int r, int[][] P) {
        if (P[q][r] != 0) {
            path(q, P[q][r], P);
            System.out.print("v" + P[q][r] + " ");
            path(P[q][r], r, P);
        }
    }

}

/*

 */
/*
INF는 -1로 표기
5
0 1 -1 1 5
9 0 3 2 -1
-1 -1 0 4 -1
-1 -1 2 0 3
3 -1 -1 -1 0

5 3
 */

/*
10
0 11 0
 */

/*// path 없는 일반 알고리즘
void floyd(int n, const number W[][], number D[][]) {
int i, j, k;
D = W;
for (k=1; k <= n; k++)
for (i=1; i <= n; i++)
for (j=1; j <= n; j++)
D[i][j] = minimum(D[i][j], D[i][k]+D[k][j]);
}
 */