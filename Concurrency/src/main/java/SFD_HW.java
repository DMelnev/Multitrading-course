public class SFD_HW {
    private static final Object MONITOR1 = new Object();
    private static final Object MONITOR2 = new Object();

    public void scan(int count) {
        synchronized (MONITOR1) {
            for (int i = 1; i < count; i++) {
                System.out.println("Scanned " + i + "sheets");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void print(int count) {
        synchronized (MONITOR2) {
            for (int i = 1; i < count; i++) {
                System.out.println("Printed " + i + "sheets");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
