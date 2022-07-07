import java.util.Arrays;

public class RunMultiplier {
    public static void main(String[] args) {
        final Integer[][] matrix_A = {{5, 8, 11, 25, 31}, {12, 85, 32, 41, 3}, {21, 33, 9, 7, 2}, {52, 81, 14, 19, 5}};    // Первая  матрица.
        final Integer[][] matrix_B = {{6, 69, 13, 39, 31, 47}, {12, 85, 32, 41, 3, 84}, {21, 33, 9, 7, 2, 1}, {52, 81, 14, 19, 5, 6}, {22, 34, 45, 47, 61, 72}};  // Вторая матрица.


        Integer[][] resultMatrixMT = MultipleMatrixThread.multiplyMatrixMT(matrix_A, matrix_B, 4);


        for (Integer[] matrix : resultMatrixMT) {


            System.out.println(Arrays.toString(matrix));

        }
        }
    }





