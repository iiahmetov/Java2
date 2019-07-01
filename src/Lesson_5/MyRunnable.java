package Lesson_5;

public class MyRunnable implements Runnable {

    String name;
    float[] arr;

    public MyRunnable(String name, float[] arr) {
        this.name = name;
        this.arr = arr;
    }

    @Override
    public void run() {
        calcArr(arr);
    }

    private static void calcArr (float[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
