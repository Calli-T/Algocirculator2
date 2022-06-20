package Design2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SubSum {
    static ArrayList<Integer> startingPoint;
    static int maxPoint;
    static int start;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        double list[] = new double[n];

        for (int i = 0; i < n; i++)
            list[i] = Double.parseDouble(sc.next());

        startingPoint = new ArrayList<>();

        double ans = maxSubList(list, n);

        System.out.println();
        System.out.println(ans);
        
        //이하는 list출력
        Iterator<Integer> it = startingPoint.iterator();
        while (it.hasNext()) {
            int temp = it.next();
            if (temp > maxPoint) break;
            start = temp;
        }

        for (int i = start; i <= maxPoint; i++)
            System.out.print(list[i] + " ");
        System.out.println();

        sc.close();
    }

    public static double maxSubList(double[] A, int n) {
        double[] B;
        double max;
        B = new double[n];
        B[0] = A[0];
        max = A[0];
        startingPoint.add(0); // list 출력을 위한

        for (int i = 1; i < n; i++) {
            //B[i] = (B[i-1] <= 0) ? A[i] : B[i-1] + A[i];
            if (B[i - 1] <= 0) {
                B[i] = A[i];
                startingPoint.add(i); // list 출력을 위함
            } else {
                B[i] = B[i - 1] + A[i];
            }
            if (max < B[i]) {
                max = B[i];
                maxPoint = i; // list 출력을 위함
            }
        }

        return max;
    }
}

//2: 14p