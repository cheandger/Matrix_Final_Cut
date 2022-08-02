import java.util.concurrent.CountDownLatch;


public class CalcThread extends Thread{//создаем класс потока, в нем описываем участников тех свального греха, на который матрицы обрекли математики
    public Integer rowIndex;
    public final Matrix matrix_Result;// Делаем по-возможности все final, чтобы просто менять состояние объектов.
    public final Matrix matrix_A;
    public final Matrix matrix_B;
   // public  boolean finish; // Флаг отработавшего метода, пока не придумал, зачем нужен, но пусть будет
    public  CountDownLatch downLatch;






    public CalcThread(Integer rowIndex, Matrix matrix_result, Matrix matrix_a, Matrix matrix_b) {
        this.matrix_Result = matrix_result;
       this.matrix_A = matrix_a;
        this.matrix_B = matrix_b;
        this.rowIndex = rowIndex;
       // downLatch = new CountDownLatch(matrix_A.getMatrix().length); // пока не нужна

    }

    @Override
    public void run() {

            System.out.println("Thread " + getName() + " Has started just now. It will calculate " + rowIndex + " row");

            calcRow();

        System.out.println("Thread " + getName() + " is finished." );}

        public void calcCell(final Integer row, final Integer col) //вычисление ячейки матрицы, значение которой мы потом положим в результирующую матрицу
        {
            Integer sum = 0;
            for (Integer i = 0; i < matrix_B.length; i++) {

                Integer value = matrix_A.getMatrix()[row][i] * matrix_B.getMatrix()[i][col];
                sum+=value;}
            matrix_Result.getMatrix()[row][col] = sum;
        }
        public void calcRow() {//Основная фунция потока, бежит по ряду, в зависимости от значения rowIndex;
                                // каждый ряд будет считаться в своем потоке.

            Integer colCount = matrix_B.getMatrix()[0].length;  // Число столбцов результирующей матрицы.
            for (Integer colIndex = 0; colIndex  < colCount; colIndex++){

                calcCell(rowIndex, colIndex);}
            }

};




