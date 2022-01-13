import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch count = new CountDownLatch(3);
        long before = System.currentTimeMillis();
        executorService.execute(new Runnable() {
//        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum1 = 0;
                for (int i = 0; i < 1_000_000; i++) {
                    if (i % 2 == 0) sum1++;
                }
                System.out.println("summ of even digits is " + sum1);
                count.countDown();
            }
        });
//        Thread thread2 = new Thread(new Runnable() {
                    executorService.execute(new Runnable() {
            @Override
            public void run() {
                int sum1 = 0;
                for (int i = 0; i < 1_000_000; i++) {
                    if (i % 7 == 0) sum1++;
                }
                System.out.println("summ of multiply 7 digits is " + sum1);
                count.countDown();
            }
        });
//        Thread thread3 = new Thread(new Runnable() {
                    executorService.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Integer> list = new ArrayList<>();
                int sum1 = 0;
                for (int i = 0; i < 1_000; i++) {
                    list.add((int) (Math.random() * 100));
                    if (list.get(i) % 2 == 0) sum1++;
                }
                System.out.println("summ of even digits in collection is " + sum1);
                count.countDown();
            }
        });
//        thread1.start();
//        thread2.start();
//        thread3.start();
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long past = System.currentTimeMillis();

        executorService.shutdown();

        System.out.println("time " + (past - before));
    }


}
