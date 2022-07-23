import java.util.Arrays;


public class Matrix  {
    private final Integer [][] matrix;

   public Integer length;
   public static Integer row;
   public static Integer col;


    public Matrix(Integer row, Integer col) {

        this.matrix = new Integer[row][col];
        this.length = this.matrix.length;

    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public static Integer getRow() {
        return row;
    }

    public static Integer getCol() {
        return col;
    }

    public void RandomMatrix(){

        for (Integer i=0; i<this.matrix.length;i++){
            for (Integer j = 0; j<this.matrix[0].length;j++){
                Integer random = new java.util.Random().nextInt(500);
                matrix[i][j]=random;}
        }
    }

    @Override
  public String toString(){

        for (Integer[] integers : this.matrix)
            System.out.println(Arrays.toString(integers));
        return "";
    }
}
