import java.util.Scanner;

//Graph to Adjacent matrix
public class GTA {
    public static void main(String args[]){
        int v, e;
        Scanner sc = new Scanner(System.in);
        v = Integer.parseInt(sc.next());
        e = Integer.parseInt(sc.next());

        int matrix[][] = new int[v][v];
        for(int i = 0; i < v; i++)
            for(int j = 0; j<v; j++)
                matrix[i][j] = -1;
        for(int i = 0; i < v; i++)
            matrix[i][i] = 0;

        for(int i =0 ; i < e; i++){
            int t1, t2, t3;
            t1 = Integer.parseInt(sc.next());
            t2 = Integer.parseInt(sc.next());
            t3 = Integer.parseInt(sc.next());

            matrix[t1-1][t2-1] = t3;
            matrix[t2-1][t1-1] = t3;
        }

        for(int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
