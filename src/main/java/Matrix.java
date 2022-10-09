import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.*;


class Matrix {                      //описываем класс матрицы
    private final Integer[][] matrix;


    public Matrix(Integer[][] matrixArray) {

       matrix = new Integer[matrixArray.length][matrixArray[0].length];// чтобы экземпляр матрицы был иммутабельным(threadsafe того требует)
                                                                        //делаем глубокую копию массива(ручками)

        for (Integer copyRow = 0; copyRow < matrixArray.length; copyRow++) {
            for (Integer copyCol = 0; copyCol < matrixArray[0].length; copyCol++) {
                matrix[copyRow][copyCol] = matrixArray[copyRow][copyCol];
            }
        }

    }


    public Integer getRow() {
        return matrix.length;
    }//геттеры, кудаж без них

    public Integer getCol() {
        return matrix[0].length;
    }

    public Integer[][] getMatrix(Matrix matrix) {
        return this.matrix;//поидее в уч материалах говорят, что геттер должен быть аналогичен конструктору,
        //но если мы просто читаем что-то по ссылке, и не пытаемся менять - хз зачем. Спросить у Дато!
       /* matrix = new Integer[matrix.getRow()][matrix.getCol()]; //

        for (Integer row = 0; row<matrix.getRow();row++){
            for (Integer col=0;col< matrix.getCol();col++){
                matrix[row][col] = deepCopy[row][col];
            }
        }
      return deepCopy;*/
    }


    public Integer getLength() {
        return matrix.length * matrix[0].length;
    }



    public Matrix MultipleMatricesMT(Matrix matrix, Integer threadPoolSize) throws ExecutionException, InterruptedException, IllegalArgumentException{
// метод умножения матриц
        if (this.matrix[0].length != matrix.getRow()) {
            throw new IllegalArgumentException("matrices are inconsistent");// проверяем косистентность
        }

        Integer[][] resultMatrix = new Integer[this.matrix.length][matrix.getCol()];//Создаем массив, где будем хранить результаты вычислений
        // и с помощью которого потом будем инициализировать результат вычислений


        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize); //запускаем ExecutorService

        for (Integer row = 0; row < this.matrix.length; row++){//Бежим циклом по массиву результата вычислений и напихиваем его значениями
           for ( Integer col = 0; col < matrix.getCol();col++){

               Integer innerRow = row;//создаем служебные переменные, чтобы использовать их в потоке.
               Integer innerCol = col;



               Integer calcThreadResult = executorService.submit(() -> {//Сабмитим задачи в том же цикле(используем callable, но можно и runnable),
                   //главное, чтобы можно было выдернуть возвращаемое значение с помощью get()

                   System.out.println("Thread " + Thread.currentThread().getName() + " Has started just now.");

                   Integer sum = 0;
                       for (Integer i = 0; i < matrix.getRow(); i++) {// в цикле рассчитываем значение каждой ячейки результирующей матрицы.

                           sum += getMatrix(Matrix.this)[innerRow][i] * matrix.getMatrix(matrix)[i][innerCol];
                       }

                       System.out.println("Thread " + Thread.currentThread().getName() + " finished.");

                   return sum;

               }).get(); //достаем результаты вычисления
               resultMatrix[row][col]=calcThreadResult;//добавляем в массив

            }
        } executorService.shutdown();//инициализируем массив и делаем из него матрицу



        return new Matrix(resultMatrix);}



    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
       for (Integer k = 0;k< matrix.length;k++){

        str.append("\n").append(Arrays.toString(matrix[k]));

       }
        return str.append("\n").toString();
    }

    }







