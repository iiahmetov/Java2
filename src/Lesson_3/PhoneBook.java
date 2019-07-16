package Lesson_3;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class PhoneBook{

    private LinkedHashMap<String, LinkedList<Integer>> phoneBook = new LinkedHashMap<>(); //Хэш-таблица со строковым ключом и связным списком целых чисел


    public void add(String surname, Integer phoneNumber){   //метод добавления в справочник
        LinkedList<Integer> temp = new LinkedList<>();      //временный связный список целых чисел
        if (phoneBook.get(surname) == null) {               //проверяем есть ли в хэш-таблице элементы с данным ключом
            temp.addFirst(phoneNumber);                     //если нет то записываем номер телефона во временный список
        } else {
            temp = phoneBook.get(surname);                  //если есть, то берём список с номерами по данному ключу
            temp.addFirst(phoneNumber);                     //добавляем к списку новый номер
        }
        phoneBook.put(surname, temp);                       //записываем в хэш-таблицу пару ключ-список номером
    }

    public void get(String surname){                        //метод поиска номера телефона по фамилии
        if (phoneBook.containsKey(surname)) {               //проверяем существует ли такой ключ (фамилия)
                                                            //выводим ключ и все данныеб относящиеся к нему
            System.out.println("Товарищ " + surname + " доступен по следующему(им) номеру(ам): " +(phoneBook.get(surname).toString()));
        } else {                                            //в противном случае выводим сообщение об отсутствии данного ключа
            System.out.println("Товарищ " + surname + " не числится в данном телефонном справочнике!");
        }
    }



}
