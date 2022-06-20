package Design3;

public class Knapsack {
    public static void knapsackDyProg(int W[], int V[], int M, int n) {
        int B[][] = new int[n + 1][M + 1];

        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= M; j++) {
                B[i][j] = 0;
            }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= M; j++) {
                B[i][j] = B[i - 1][j];

                if ((j >= W[i - 1]) && (B[i][j] < B[i - 1][j - W[i - 1]] + V[i - 1])) {
                    B[i][j] = B[i - 1][j - W[i - 1]] + V[i - 1];
                }

                System.out.print(B[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println("Max Value:\t" + B[n][M]);

        System.out.println("Selected Packs: ");

        while (n != 0) {
            if (B[n][M] != B[n - 1][M]) {
                System.out.println("\tPackage " + n + " with W = " + W[n - 1] + " and Value = " + V[n - 1]);

                M = M - W[n - 1];
            }

            n--;
        }
    }
    public static void run() {
        /*
         * Pack and Weight - Value
         */
        //int W[] = new int[]{3, 4, 5, 9, 4};
        int W[] = new int[]{10, 20, 30};

        //int V[] = new int[]{3, 4, 4, 10, 4};
        int V[] = new int[]{50,60,100};

        /*
         * Max Weight
         */
        //int M = 11;
        int M = 50;
        int n = V.length;

        /*
         * Run the algorithm
         */
        knapsackDyProg(W, V, M, n);
    }
    public static void main(String[] args){
        run();
    }
}
