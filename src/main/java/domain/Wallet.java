package domain;

public class Wallet {
    private static final int winMagnification = 2;
    private static final int loseMagnification = -1;
    private static final double blackJackMagnification = 1.5;

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
        money *= winMagnification;
    }

    public void win(int money) {
        gain += money;
        this.money += money;
    }

    public void firstTurnBlackJackFrom(boolean theSameTime) {
        if (theSameTime) {
            return;
        }
        gain += (int)(money * blackJackMagnification);
        money += money * blackJackMagnification;
    }

    public void firstTurnBlackJackFrom(boolean theSameTime, int money) {
        if (theSameTime) {
            return;
        }
        gain -= (int)(money * blackJackMagnification);
        this.money -= money * blackJackMagnification;
    }

    public void lose() {
        gain += money * loseMagnification;
        money += money * loseMagnification;
    }

    public void lose(int money) {
        gain += money * loseMagnification;
        this.money += money * loseMagnification;
    }

    public int getGain() {
        return gain;
    }

    public int getMoney() {
        return money;
    }
}
