public class main3HW {
    private static final Object monitor1 = new Object();
    private static final Object monitor2 = new Object();
    private static final Object monitor3 = new Object();


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor1) {
                        System.out.print("A");
                        try {
                            synchronized (monitor2) {
                                monitor2.notifyAll();
                            }
                            monitor1.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (monitor3) {
                    monitor3.notifyAll();
                }
                synchronized (monitor2) {
                    monitor2.notifyAll();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor2) {
                        System.out.print("B");
                        try {
                            synchronized (monitor3) {
                                monitor3.notifyAll();
                            }
                            monitor2.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor3) {
                        System.out.print("C");
                        try {
                            synchronized (monitor1) {
                                monitor1.notifyAll();
                            }
                            monitor3.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread1.start();
        try {
            Thread.sleep(100);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        try {
            Thread.sleep(100);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }

        thread3.start();
    }
}
