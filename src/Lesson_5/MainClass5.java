package Lesson_5;

public class MainClass5 {
    public static void main(String[] args) {
        int size = 10000000;
        int h = size/2;

        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        calcArr(arr);
        calcArrInTwoTreads(arr);
        calcArrInFourTreads(arr);

    }

    public static void calcArr (float[] array){
        Thread arr = new Thread(new MyRunnable("FullArray", array));
        long start = System.currentTimeMillis();
        arr.run();
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void calcArrInTwoTreads (float[] array){
        int h = array.length/2;
        float[] arr = new float[array.length];
        float[] ar1 = new float[h];
        float[] ar2 = new float[h];
        long start = System.currentTimeMillis();
        System.arraycopy(array, 0, ar1, 0, h);
        System.arraycopy(array, h, ar2, 0, h);
        Thread arr1 = new Thread(new MyRunnable("FirstHalf", ar1));
        Thread arr2 = new Thread(new MyRunnable("SecondHalf", ar2));
        arr1.start();                           //сначала старт дабы запустить параллельным потоком
        arr2.run();                             //сделал через run (т.е. в основном потоке) т.к. операции 2 и достаточно 2 потоков
        try {
            arr1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        arr1.start();                         //вариант с 3 потоками, в данном случае излишний, но "чтобы был"
//        arr2.start();
//        try {
//            arr1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            arr2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.arraycopy(ar1, 0, arr, 0, h);
        System.arraycopy(ar2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void calcArrInFourTreads (float[] array){
        int h = array.length/4;
        float[] arr = new float[array.length];
        float[] ar1 = new float[h];
        float[] ar2 = new float[h];
        float[] ar3 = new float[h];
        float[] ar4 = new float[h];
        long start = System.currentTimeMillis();
        System.arraycopy(array, 0, ar1, 0, h);
        System.arraycopy(array, h, ar2, 0, h);
        System.arraycopy(array, (h * 2), ar3, 0, h);
        System.arraycopy(array, (h * 3), ar4, 0, h);
        Thread arr1 = new Thread(new MyRunnable("FirstQuarter", ar1));
        Thread arr2 = new Thread(new MyRunnable("SecondQuarter", ar2));
        Thread arr3 = new Thread(new MyRunnable("ThirdQuarter", ar3));
        Thread arr4 = new Thread(new MyRunnable("FourthQuarter", ar4));
        arr1.start();
        arr2.start();
        arr3.start();
        arr4.run();
//        arr4.start();
        try {
            arr1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            arr2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            arr3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            arr4.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.arraycopy(ar1, 0, arr, 0, h);
        System.arraycopy(ar2, 0, arr, h, h);
        System.arraycopy(ar3, 0, arr, (h * 2), h);
        System.arraycopy(ar4, 0, arr, (h * 3), h);
        System.out.println(System.currentTimeMillis() - start);
    }

}
