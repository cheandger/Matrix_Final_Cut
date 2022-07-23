import java.sql.Array;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.logging.SocketHandler;

public class RunMultiplier {
    public static void main(String[] args) {

        Matrix matrixA = new Matrix(4, 5);
        matrixA.RandomMatrix();
        Matrix matrixB = new Matrix(5, 6);
        matrixB.RandomMatrix();
       /* Semaphore semaphore = new Semaphore(matrixA.getMatrix().length);

        for (Integer i = matrixA.getMatrix().length; i<matrixA.getMatrix().length; i--){
            new Thread(){
                public void run(){
                    try {
                        semaphore.tryAcquire();
                        MultipleMatrixThread.multiplyMatrixMT(matrixA,matrixB)
                    }
                }
            };*/
        System.out.println(matrixA);
        System.out.println(matrixB);

        MultipleMatrixThread.multiplyMatrixMT(matrixA,matrixB);
    }

}




        // Первая  матрица.


    /*final Integer[][] matrix_B = {{6, 69, 13, 39, 31, 47}, {12, 85, 32, 41, 3, 84}, {21, 33, 9, 7, 2, 1}, {52, 81, 14, 19, 5, 6}, {22, 34, 45, 47, 61, 72}};  // Вторая матрица.

sout
        Integer[][] resultMatrixMT = MultipleMatrixThread.multiplyMatrixMT(matrix_A, matrix_B, 4);


        for (Integer[] matrix : resultMatrixMT) {


            System.out.println(Arrays.toString(matrix));

        }*/
 /*   }       }

public static class CalcThread extends Thread {
    private int startRow, endRow;
    private int[][] a, b, result;
    private int n;

    public CalcThread(int[][] a, int[][] b, int[][] result, int startRow, int endRow) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
        this.n = b.length;
    }

    @Override
    public void run() {
        System.out.println("Считаю со строки " + startRow + " до строки " + endRow + " включительно");
        for (int row = startRow; row <= endRow ; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = calcSingleValue(row, col);
            }
        }
    }

    private int calcSingleValue(int row, int col) {
        int c = 0;
        for (int i = 0; i < n; i++) {
            c += a[row][i] * b[i][col];
        }
        return c;
    }
}

    public static int[][] multiply(int[][] a, int[][] b, int threadsCount) {
        //проверки
        if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
            throw new IllegalArgumentException("a");
        }
        if (b == null || b.length == 0 || b[0] == null || b[0].length == 0) {
            throw new IllegalArgumentException("b");
        }
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("матрицы не согласованы");
        }
        //определяем размеры результирующей матрицы
        int m = a.length;
        int q = b[0].length;
        int[][] result = new int[m][q];
        //если количество потоков больше чем количество строк - уменьшим кол-во потоков
        if (threadsCount > m) {
            threadsCount = m;
        }
        //посчитаем сколько строк результирующей матрицы будет считать каждый поток
        int count = m / threadsCount;
        int additional = m % threadsCount; //если не делится на threadsCount, то добавим к первому потоку
        //создаем и запускаем потоки
        Thread[] threads = new Thread[threadsCount];
        int start = 0;
        for (int i = 0; i < threadsCount; i++) {
            int cnt = ((i == 0) ? count + additional : count);
            threads[i] = new CalcThread(a, b, result, start, start + cnt - 1);
            start += cnt;
            threads[i].start();
        }
        //ждем завершения
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] b = {{2, 1, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 1, 2, 1}, {2, 1, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 1, 2, 1}};
        int[][] c = multiply(a, b, 8);

        for (int[] ints : c) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
Поделиться
        Улучшить ответ
        Отслеживать
        изменён 4 дек 2021 в 19:46
        user avatar
        Дух сообществаБот



*/
