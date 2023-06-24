package domain;

public class Person {
    protected Name name = new Name();
    protected Deck deck = new Deck();
    protected Wallet wallet;

    public void setMoney(int money) {
        wallet = new Wallet(money);
    }

    public void addCard(Card card) {
        deck.addCard(card);
    }

    public String getCardInfo(int index) {
        return deck.getCardInfo(index);
    }
}
