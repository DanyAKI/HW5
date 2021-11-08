import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Task1();
        Task2();
    }

    public static void Task1() {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Thread time 1: " + (System.currentTimeMillis() - startTime) + " ms.");
    }


    public static void Task2() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];

        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t0 = new Thread(() -> Calc(a1));
        Thread t1 = new Thread(() -> Calc(a2));

        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();

        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }


        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long end = System.currentTimeMillis() - start;
        System.out.println("Operation took: " + end + " ms.");

    }

    private static void Calc(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}






