package Design2;

import java.util.Scanner;

public class BinomialCoefficient {

    public static void main(String args[]) {
        int n, k;
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.next());
        k = Integer.parseInt(sc.next());

        int[][] B = new int[n+1][k+1];

        for(int i = 0; i <= n; i++){
            for(int j = 0; j<=Math.min(i, k); j++){
                if(j==0 || j==i)
                    B[i][j] = 1;
                else B[i][j] = B[i-1][j-1] + B[i-1][j];
            }
        }

        System.out.println(B[n][k]);
    }
}

