package constant;

public enum Constants {
    BLACKJACK(21),
    BIG_ACE_MINUS_SMALL_ACE(10),
    TOTAL_NUMBER_OF_CARDS(13),
    DEALER_DRAWS_ANOTHER_CARD(17),
    DEALER_PERSON_INDEX(0),
    PLAYER1_PERSON_INDEX(1),
    PLAYER2_PERSON_INDEX(2),;

    private final int number;

    Constants(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }
}
