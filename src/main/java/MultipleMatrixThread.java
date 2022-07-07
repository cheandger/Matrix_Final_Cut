import jdk.swing.interop.DropTargetContextWrapper;

import java.util.ArrayList;
import java.util.Arrays;

public class MultipleMatrixThread extends Thread{





    public final Integer [][] matrix_A;
    public  final Integer [][] matrix_B;
    public  final Integer [][] matrix_Result;
    public  final Integer firstIndex;
    public  final Integer lastIndex;
    public  final Integer resultMatrixLength;


    public MultipleMatrixThread(Integer[][] matrix_A,
                                Integer[][] matrix_B,
                                Integer[][] matrix_Result,
                                Integer firstIndex,
                                Integer lastIndex) {
        this.matrix_A = matrix_A;
        this.matrix_B = matrix_B;
        this.matrix_Result = matrix_Result;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        resultMatrixLength = matrix_B.length;
    }
    public void calcValue(Integer row, Integer col)
    {
        Integer sum = 0;
        for (Integer i = 0; i < resultMatrixLength; ++i)
            sum += matrix_A[row][i] * matrix_B[i][col];
        matrix_Result[row][col] = sum;
    }

    @Override
    public void run()
    {
        System.out.println("Thread " + getName() + " Has started just now. It's calculating cells from " + firstIndex + " to " + lastIndex + "...");

        final Integer colCount = matrix_B[0].length;  // количество столбцов матрицы - результата;
        for (Integer index = firstIndex; index < lastIndex; ++index){
            calcValue(index / colCount, index % colCount);
       }

        System.out.println("Thread " + getName() + " is finished.");
    }



    public static Integer[][] multiplyMatrixMT(Integer[][] matrix_A,
                                               Integer[][] matrix_B, Integer threadCount)
    {
        if (matrix_A == null || matrix_A.length == 0 || matrix_A[0] == null || matrix_A[0].length == 0) {
            throw new IllegalArgumentException("matrix_A is wrong");
        }
        if (matrix_B == null || matrix_B.length == 0 || matrix_B[0] == null || matrix_B[0].length == 0) {
            throw new IllegalArgumentException("matrix_B is wrong");
        }
        if (matrix_A[0].length != matrix_B.length) {
            throw new IllegalArgumentException("matrices are inconsistent");
        }


         Integer rowCount = matrix_A.length;             // количество строк матрицы-результата.
         Integer colCount = matrix_B[0].length;         // количество столбцов матрицы-результата.
         Integer[][] result = new Integer[rowCount][colCount];//Результат умножения.
        final int cellsForThread = (rowCount * colCount) / threadCount;


        Integer firstIndex = 0;  // Индекс первой ячейки.
         MultipleMatrixThread[] multiplierThreads = new MultipleMatrixThread[threadCount];  // Массив потоков (Чтобы создавать потоки в необходимом количестве).

        // Создание и запуск потоков.
        for (Integer threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            Integer lastIndex = firstIndex + cellsForThread;  // Индекс последней вычисляемой ячейки.

            multiplierThreads[threadIndex] = new MultipleMatrixThread(matrix_A, matrix_B, result, firstIndex, lastIndex);

            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;

        }

        // Ожидание завершения потоков.
        try {
            for (MultipleMatrixThread multiplierThread : multiplierThreads) {

                multiplierThread.join();
            }
        }
        catch (InterruptedException exception) {
            System.out.println(exception);
        }

        return result;
    }


}






































/* static Integer[][] multiplyMatrixSingleTh(final Integer[][] firstMatrix,
                                      final Integer[][] secondMatrix)
    {
        final Integer rowCount = firstMatrix.length;             // Число строк результирующей матрицы.
        final Integer colCount = secondMatrix[0].length;         // Число столбцов результирующей матрицы.
        final Integer sumLength = secondMatrix.length;           // Число членов суммы при вычислении значения ячейки.
        final Integer[][] result = new Integer[rowCount][colCount];  // Результирующая матрица.

        for (Integer row = 0; row < rowCount; ++row) {  // Цикл по строкам матрицы.
            for (Integer col = 0; col < colCount; ++col) {  // Цикл по столбцам матрицы.
                Integer sum = 0;
                for (Integer i = 0; i < sumLength; ++i)
                    sum += firstMatrix[row][i] * secondMatrix[i][col];
                result[row][col] = sum;
            }
        }

        return result;
    }
*/