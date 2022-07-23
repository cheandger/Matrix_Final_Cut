class MultipleMatrixThread extends Thread{;

    public  final Matrix matrix_A;
    public  final Matrix  matrix_B;
    public final Matrix matrix_Result;
    public  final Integer firstIndex;
    public  final Integer lastIndex;
    public  Integer sumLength;

    public MultipleMatrixThread(Matrix matrix_A, Matrix matrix_B, Matrix matrix_Result,
                                Integer firstIndex, Integer lastIndex) {
        this.matrix_A = matrix_A;
        this.matrix_B = matrix_B;
        this.matrix_Result = matrix_Result;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.sumLength = matrix_B.length;
    }


        public void calcValue(Integer row, Integer col)
        {
            Integer sum = 0;
            for (Integer i = 0; i < sumLength; ++i)
                sum += matrix_A.getMatrix()[row][i] * matrix_B.getMatrix()[i][col];
            matrix_Result.getMatrix()[row][col] = sum;
        }


    @Override
    public void run()
    {
        System.out.println("Thread " + getName() + " Has started just now. It's calculating cells from " + firstIndex + " to " + lastIndex + "...");

       Integer colCount = matrix_B.getMatrix()[0].length;  // количество столбцов матрицы - результата;
        for (Integer index = firstIndex; index < lastIndex; ++index){
            calcValue(index / colCount, index % colCount);
       }

        System.out.println("Thread " + getName() + " is finished.");
    }



    public static Matrix multiplyMatrixMT(Matrix matrix_A,
                                               Matrix matrix_B)
    {
        if (matrix_A == null || matrix_A.length == 0 || matrix_A.getMatrix()[0] == null || matrix_A.getMatrix()[0].length == 0) {
            throw new IllegalArgumentException("matrix_A is wrong");
        }
        if (matrix_B == null || matrix_B.length == 0 || matrix_B.getMatrix()[0] == null || matrix_B.getMatrix()[0].length == 0) {
            throw new IllegalArgumentException("matrix_B is wrong");
        }
        if (matrix_A.getMatrix()[0].length != matrix_B.length) {
            throw new IllegalArgumentException("matrices are inconsistent");
        }


         Integer rowCount = matrix_A.getMatrix().length;             // количество строк матрицы-результата.
         Integer colCount = matrix_B.getMatrix()[0].length;         // количество столбцов матрицы-результата.
         Matrix result = new Matrix(rowCount,colCount);//Результат умножения.
        final Integer cellsForThread = matrix_B.getMatrix()[0].length;


        Integer firstIndex = 0;  // Индекс первой ячейки.
         MultipleMatrixThread[] multiplierThreads = new MultipleMatrixThread[ matrix_B.getMatrix()[0].length];  // Массив потоков (Чтобы создавать потоки в необходимом количестве).

        // Создание и запуск потоков.
        for (Integer threadIndex =  matrix_B.getMatrix()[0].length ; threadIndex > 0; --threadIndex) {
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