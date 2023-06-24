package domain;

import constant.card.Mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getCardInfo(int index) {
        return cards.get(index).getCardName();
    }

    public int getTotalNumber() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public void drawCard(int amount) {
        IntStream.range(0, amount).forEach(i -> addCard(randomCard()));
    }

    private Card randomCard() {
        Random random = new Random();

        int cardIndex = random.nextInt(13) + 1;
        Mark cardMark = Mark.values()[random.nextInt(Mark.values().length)];

        return new Card(cardIndex, cardMark);
    }

    public int size() {
        return cards.size();
    }

    public int getCardNumber(int index) {
        return cards.get(index).getNumber();
    }
}
