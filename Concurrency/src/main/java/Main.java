import java.util.*;

public class Main {
    private static int TEN_MLN = 10_000_000;
    private static int FIVE_MLN = 5_000_000;
    private static int TWO_MLN = 2_000_000;


    public static void main(String[] args) {
        System.out.println("Start");
        System.out.println("one thread " + withoutConcurrency());
        System.out.println("two threads " + withConcurrency());
        System.out.println("five threads " + fiveThreadsConcurrency());
//        System.out.println("without concurrency " + withoutConcurrency());
        System.out.println("\n finish");
    }

    public static long withConcurrency() {
        float[] array = new float[TEN_MLN];
        Arrays.fill(array, 1f);
        float[] arr1 = new float[FIVE_MLN];
        float[] arr2 = new float[FIVE_MLN];
        System.arraycopy(array, 0, arr1, 0, FIVE_MLN);
        System.arraycopy(array, FIVE_MLN, arr2, 0, FIVE_MLN);

        long before = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
                    if (i % 1000000 == 0) {
//                        try {
//                            Thread.sleep(0);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
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
        long past = System.currentTimeMillis();

        System.arraycopy(arr1, 0, array, 0, FIVE_MLN);
        System.arraycopy(arr2, 0, array, FIVE_MLN, FIVE_MLN);
        return past - before;
    }

    public static long withoutConcurrency() {
        float[] array = new float[TEN_MLN];
        Arrays.fill(array, 1f);
        long before = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }

        return System.currentTimeMillis() - before;
    }

    public static long fiveThreadsConcurrency() {
        float[] array = new float[TEN_MLN];
        Arrays.fill(array, 1f);
        float[] arr1 = new float[FIVE_MLN];
        float[] arr2 = new float[FIVE_MLN];
        float[] arr3 = new float[FIVE_MLN];
        float[] arr4 = new float[FIVE_MLN];
        float[] arr5 = new float[FIVE_MLN];
        System.arraycopy(array, 0, arr1, 0, TWO_MLN);
        System.arraycopy(array, TWO_MLN, arr2, 0, TWO_MLN);
        System.arraycopy(array, TWO_MLN * 2, arr3, 0, TWO_MLN);
        System.arraycopy(array, TWO_MLN * 3, arr4, 0, TWO_MLN);
        System.arraycopy(array, TWO_MLN * 4, arr5, 0, TWO_MLN);

        long before = System.currentTimeMillis();
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
//                    try {
//                        if (i % 1000 == 0) Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr3.length; i++) {
                    arr3[i] = (float) (arr3[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
//                    try {
//                        if (i % 1000 == 0) Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr4.length; i++) {
                    arr4[i] = (float) (arr4[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
//                    try {
//                        if (i % 1000 == 0) Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr5.length; i++) {
                    arr5[i] = (float) (arr5[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
//                    try {
//                        if (i % 1000 == 0) Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long past = System.currentTimeMillis();

        System.arraycopy(arr1, 0, array, 0, TWO_MLN);
        System.arraycopy(arr2, 0, array, TWO_MLN, TWO_MLN);
        System.arraycopy(arr3, 0, array, TWO_MLN * 2, TWO_MLN);
        System.arraycopy(arr4, 0, array, TWO_MLN * 3, TWO_MLN);
        System.arraycopy(arr5, 0, array, TWO_MLN * 4, TWO_MLN);
        return past - before;
    }
}
