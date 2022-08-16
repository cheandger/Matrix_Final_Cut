import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.*;


class Matrix {                      //описываем класс матрицы
    private final Integer[][] matrix;


    public Matrix(Integer[][] matrixArray) {

       matrix = new Integer[matrixArray.length][matrixArray[0].length];// чтобы экземпляр матрицы был иммутабельным(threadsafe того требует)
                                                                        //делаем глубокую копию массива(ручками), это не объект и clone() здесь не проканает

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

        if (this.matrix[0].length != matrix.getRow()) {
            throw new IllegalArgumentException("matrices are inconsistent");
        }

        Integer[][] resultMatrix = new Integer[this.matrix.length][matrix.getCol()];


        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        for (Integer row = 0; row < this.matrix.length; row++){
           for ( Integer col = 0; col < matrix.getCol();col++){

               Integer innerRow = row;
               Integer innerCol = col;



               Integer calcThreadResult = executorService.submit(() -> {

                   System.out.println("Thread " + Thread.currentThread().getName() + " Has started just now.");

                   Integer sum = 0;
                       for (Integer i = 0; i < matrix.getRow(); i++) {

                           sum += getMatrix(Matrix.this)[innerRow][i] * matrix.getMatrix(matrix)[i][innerCol];
                       }

                       System.out.println("Thread " + Thread.currentThread().getName() + " finished.");

                   return sum;

               }).get();
               resultMatrix[row][col]=calcThreadResult;

            }
        } executorService.shutdown();



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







