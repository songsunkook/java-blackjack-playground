package domain;

public class Dealer extends Person {
    public void lose(int money) {
        wallet.lose(money);
    }

    public void win(int money) {
        wallet.win(money);
    }
}
