package Lesson_2;

public class MyArraySizeException extends RuntimeException {    //самодельное исключение (массив неверной размерности)
    private String[][] array;
//    private int colNumber = array.length;
//    private int rowNumber = array[0].length;
//
//    public int getColNumber() {
//        return colNumber;
//    }
//
//    public int getRowNumber() {
//        return rowNumber;
//    }

    public MyArraySizeException(String message) {
        super(message);
    }
}
