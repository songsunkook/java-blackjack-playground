package domain;

public class Wallet {
    private int money;
    private int gain = 0;

    public Wallet(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        gain += money;
        this.money += money;
    }

    public void win() {
        gain += money;
        money *= 2;
    }

    public void win(int money) {
        gain += money;
        this.money += money;
    }

    public void firstTurnBlackJackFrom(boolean theSameTime) {
        if (theSameTime) {
            return;
        }
        gain += (int)(money * 0.5);
        money *= 1.5;
    }

    public void lose() {
        gain -= money;
        money = 0;
    }

    public void lose(int money) {
        gain -= money;
        this.money -= money;
    }

    public int getGain() {
        return gain;
    }

    public int getMoney() {
        return money;
    }
}
