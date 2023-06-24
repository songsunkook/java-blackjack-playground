package domain;

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

    public void drawCard(int amount) {
        deck.drawCard(amount);
    }

    public int cardAmount() {
        return deck.size();
    }
}
