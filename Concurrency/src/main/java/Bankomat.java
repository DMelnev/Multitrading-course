public class Bankomat {
    private int money = 10000;

    public Bankomat(int money) {
        this.money = money;
    }

    public Bankomat() {
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void outgoingTransaction(String name, int summ) {
        System.out.println(name + " came to bankomat");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            if (summ <= money) {
                System.out.println(name + " cashed " + summ + " rubles");
                money -= summ;
                System.out.println("left " + money + " rubles");
                return;
            }
        }
        System.out.println("Not enough money for " + name);
        return;
    }
}
