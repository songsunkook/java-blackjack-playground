package domain;

import constant.Constants;
import constant.card.Number;

import java.util.stream.IntStream;

public class Person {
    protected Name name;
    protected Deck deck = new Deck();
    protected Wallet wallet;

    public void setName(String name) {
        this.name = new Name(name);
    }

    public void setMoney(int money) {
        wallet = new Wallet(money);
    }

    public void addCard(Card card) {
        deck.addCard(card);
    }

    public String getCardInfo(int index) {
        return deck.getCardInfo(index);
    }

    public String getName() {
        return name.get();
    }

    public void drawCard() {
        deck.drawCard(1);
    }

    public void drawCard(int amount) {
        deck.drawCard(amount);
    }

    public int cardAmount() {
        return deck.size();
    }

    public boolean survive() {
        return deck.getTotalNumber() <= Constants.BLACKJACK.get();
    }

    public boolean blackJack() {
        return deck.getTotalNumber() == Constants.BLACKJACK.get();
    }

    public int getTotalNumber() {
        int result = deck.getTotalNumber();
        for (int i = 0; i < deck.size(); i++) {
            if (deck.getCardNumber(i) == Number.ACE.getNumber()) {
                if (result + Constants.BIG_ACE_MINUS_SMALL_ACE.get() <= Constants.BLACKJACK.get()) {
                    return result + Constants.BIG_ACE_MINUS_SMALL_ACE.get();
                }
            }
        }
        return result;
    }

    public void lose() {
        wallet.lose();
    }

    public void firstTurnBlackJackFrom(boolean theSameTime) {
        wallet.firstTurnBlackJackFrom(theSameTime);
    }

    public void win() {
        wallet.win();
    }

    public int getGain() {
        return wallet.getGain();
    }

    public void addMoney(int money) {
        wallet.addMoney(money);
    }

    public int getMoney() {
        return wallet.getMoney();
    }
}
