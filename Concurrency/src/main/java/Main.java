import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        System.out.println(withoutConcurrency());
        System.out.println(withConcurrency());
        System.out.println("\n finish");
    }

    public static long withConcurrency() {
        float[] array = new float[10000000];
        Arrays.fill(array, 1f);
        long before = System.currentTimeMillis();

        float[] arr1 = new float[5000000];
        float[] arr2 = new float[5000000];
        System.arraycopy(array,0,arr1,0,5000000);
        System.arraycopy(array,5000000,arr2,0,5000000);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arr1,0,array,0,5000000);
        System.arraycopy(arr2,0,array,5000000,5000000);
        return System.currentTimeMillis() - before;
    }

    public static long withoutConcurrency() {
        float[] array = new float[10000000];
        Arrays.fill(array, 1f);
        long before = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }

        return System.currentTimeMillis() - before;
    }
}
