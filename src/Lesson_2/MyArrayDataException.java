package Lesson_2;

public class MyArrayDataException extends RuntimeException {  //самодельное исключение (элемент нельзя конвертировать)
    private String element;         //проблемный элемент
    private int col;                //номер столбца с элементом
    private int row;                //номер строки с элементом

    public String getElement() {
        return element;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public MyArrayDataException(String message, String element, int col, int row) {
        super(message);
        this.element = element;
        this.col = col;
        this.row = row;
    }
}
