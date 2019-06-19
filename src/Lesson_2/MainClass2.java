package Lesson_2;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class MainClass2 {
    public static void main(String[] args) {
        String[][] arr = {
                {"0", "1", "2", "3"},                       //нормальный массив
                {"4", "5", "6", "7"},
                {"8", "9", "10", "11"},
                {"12", "13", "14", "15"},
//
//                {"0", "1", "2", "3"},                       //массив с неверным количеством символов
//                {"4", "5", "6", "7"},
//                {"8", "9", "10", "11"},
//                {"12", "13", "14"},
//
//                {"0", "1", "2", "3"},                       //массив с неконвертируемым элементом
//                {"4", "5", "6", "7"},
//                {"k", "9", "10", "11"},
//                {"12", "13", "14", "15"},
        };
        try {                                               //ловим исключения
            System.out.println(sumElemArray(arr));          //при попытке просуммировать все элементы массива  (у меня почему-то не предлагал автоматически заключить метод в конструкцию try-catch)
        } catch (MyArraySizeException exSize) {             //проверяем размер массива
            System.out.println(exSize.getMessage());        //в случае исключения выводим сообщение
        } catch (MyArrayDataException exData) {             //проверяем наличие неконвертируемых элементов
            System.out.println("Элемент массива " + exData.getElement() + " нельзя преобразовать в число"); //выводим сообщение с проблемным элементом
            System.out.println("Элемент находится в:");
            System.out.println(exData.getRow() + " строке");    //выводим координаты проблемного элемента
            System.out.println(exData.getCol() + " столбце");
        }

    }


    public static int sumElemArray (String[][] array) throws  MyArraySizeException, MyArrayDataException {  //метод с прокидыванием исключений (у меня почему-то не предлагал автоматически прописать throws)
        for (int i = 0; i < 4 ; i++) {                                          //проверяем размер массива
            if (array.length != 4 | array[i].length != 4) {
                throw new MyArraySizeException("Неверный размер массива.");     //в случае провала кидаем самодельное исключение
            }
        }
        int sum = 0;                                                            //сумма изначально равна 0
        int temp;
        for (int i = 0; i < array.length; i++) {                            //проходим в массиве по всем элементам
            for (int j = 0; j < array[0].length ; j++) {
                try {                                                       //пытаемся конвертировать элемент
                    temp = Integer.parseInt(array[j][i]);
                } catch (NumberFormatException e){                          //в случае провала отлавливаем стандартное исключение NumberFormatException
                    throw new MyArrayDataException ("Элемент не может быть преобразован в число", array[j][i], i , j);  //прокидываем самодельное исключение
                }
                sum += temp;                            //приплюсовываем элемент к общей сумме
            }
        }
        return sum;                             //возврас общей суммы элементов
    }

}
