import java.util.Arrays;


public class Matrix  {                      //описываем класс матрицы
    private final Integer [][] matrix;
    public Integer length;




    public Matrix(Integer row, Integer col) {

        this.matrix = new Integer[row][col];
        this.length = this.matrix.length;


    }

    public Integer[][] getMatrix() { // Геттер матрицы, чтобы щупать ее за всякое
        return this.matrix;
    }




    public void RandomMatrix(){

        for (Integer i=0; i<this.matrix.length;i++){                           //заполняем матрицу случайными значениями
            for (Integer j = 0; j<this.matrix[0].length;j++){
                Integer random = new java.util.Random().nextInt(500);
                matrix[i][j]=random;}
        }
    }

    @Override
  public String toString(){                              //переопределяем toString

        for (Integer[] integers : this.matrix)
            System.out.println(Arrays.toString(integers));
        return "";
    }
}
