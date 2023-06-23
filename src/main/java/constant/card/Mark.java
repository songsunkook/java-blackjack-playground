package constant.card;

public enum Mark {
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    ;

    private final String message;

    Mark(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
