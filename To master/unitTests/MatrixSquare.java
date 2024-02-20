package unitTests;

import java.util.ArrayList;

public class MatrixSquare {
    public static int[][] matrixsquare(int n) {
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            int value = -i;
            for (int j = 0; j < n; j++) {
                array[i][j] = value;
                value++;
                array[i][j] = Math.abs(array[i][j]);
            }
        }
        return array;
    }

    public static int[][] matrixsquare2(int n) {
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            int value = 0;
            for (int j = i; j < n; j++) {
                array[i][j] = value;
                array[j][i] = value++;
            }
        }
        return array;
    }
    public static int[][] matrixsquare3(int n) {
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = Math.abs(i-j);
            }
        }
        return array;
    }
}


