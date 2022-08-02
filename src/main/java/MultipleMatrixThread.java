import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class MultipleMatrixThread {            // Класс умножалка

    public final Matrix matrix_A;
    public final Matrix matrix_B;
    public final Matrix matrix_Result;
    public Integer rowIndex;
    private int threadSize;    //Пока не понял как прикрутить семафор или щеколду, поэтому пусть просто полежит


    public MultipleMatrixThread(Matrix matrix_A, Matrix matrix_B) {
        this.matrix_A = matrix_A;
        this.matrix_B = matrix_B;
        this.rowIndex = 0;
        this.matrix_Result = new Matrix(matrix_A.getMatrix().length, matrix_B.getMatrix()[0].length);
        this.threadSize = matrix_A.getMatrix().length;
    }


    public void multiplyMatrixMT() throws InterruptedException {   //Собственно сам метод, сначала проверяем условия

        if (matrix_A == null || matrix_A.length == 0 || matrix_A.getMatrix()[0] == null || matrix_A.getMatrix()[0].length == 0) {
            throw new IllegalArgumentException("matrix_A is wrong");
        }
        if (matrix_B == null || matrix_B.length == 0 || matrix_B.getMatrix()[0] == null || matrix_B.getMatrix()[0].length == 0) {
            throw new IllegalArgumentException("matrix_B is wrong");
        }
        if (matrix_A.getMatrix()[0].length != matrix_B.length) {
            throw new IllegalArgumentException("matrices are inconsistent");
        }

        //Thread[] threadList = new Thread[matrix_A.length];//раньше делал по примеру предыдущих поколений с массивом потоков, потом сделал через очередь
        Queue<Thread> threadQueue = new LinkedList<>();


        for (Integer threadCount = 0; threadCount < matrix_A.length; threadCount++) {//создаем потоки, в которых будем считать ряды матрицы

            threadQueue.add(new CalcThread(rowIndex, matrix_Result, matrix_A, matrix_B));
            // Собственно экземпляр потока с индексом номера ряда,
            //каждый поток будет считать свой ряд

            rowIndex++;//а вот здесь мы счетчик рядов прикрутим;

        }
        ;
        for (Thread thread : threadQueue) {//Старутем и джойнимся, чтобы все отработало, а то консоль субординацию не блюдет и печатает первой...
            thread.start();
            thread.join();

          //  try{CalcThread.downLatch.await();} catch (InterruptedException e) {// так и не понял,что лучше использовать щеколду или барьеры,
                                                                                 //пока метод join(), потом посмотрим;
              //  e.printStackTrace();
            }
          //  CalcThread.getDownLatch().countDown();
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