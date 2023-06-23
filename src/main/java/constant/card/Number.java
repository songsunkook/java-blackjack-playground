package constant.card;

public enum Number {
    ACE(1),
    JACK(11),
    QUEEN(12),
    KING(13),
    ;

    private final int number;

    Number(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}
