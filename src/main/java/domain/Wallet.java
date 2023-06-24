package domain;

public class Wallet {
    private int money;

    public Wallet(int money) {
        this.money = money;
    }

    public int win() {
        //모든 유저의 베팅 금액 반환
        return 0;
    }

    public int firstTurnBlackJackFrom(boolean theSameTime) {
        if (theSameTime) {
            return money;
        }
        money *= 1.5;
        return money;
    }

    public int draw() {
        return money;
    }

    public int lose() {
        money = 0;
        return money;
    }
}
