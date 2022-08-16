import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class RunMultiplier {
    public static void main(String[] args) throws InterruptedException, ExecutionException,IllegalArgumentException {


       Matrix matrix1 = new Matrix(randomise2DArray(10,8));

       Matrix matrix2= new Matrix(randomise2DArray(8,25));
        System.out.println(matrix1);
        System.out.println(matrix2);
     try {  System.out.println(matrix1.MultipleMatricesMT(matrix2,3));
    } catch (IllegalArgumentException exception){
       System.out.println(exception);
     }
    };

   private static Integer[][] randomise2DArray(Integer row, Integer col) {
       if (row == 0 ||col == 0) {
           throw new IllegalArgumentException("the matrix is wrong");
       }

       Integer[][] randomMatrix = new Integer[row][col];

        for (Integer i = 0; i < row; i++) {                           //Метод для заполненеия матрицы случайными значениями
            for (Integer j = 0; j < col; j++) {                    // вдруг пригодится;
                Integer random = new java.util.Random().nextInt(500);
                randomMatrix[i][j] = random;
            }
        }

        return randomMatrix;
    }
}

