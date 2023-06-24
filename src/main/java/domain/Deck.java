package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getCardInfo(int index) {
        return cards.get(index).getCardName();
    }
}
