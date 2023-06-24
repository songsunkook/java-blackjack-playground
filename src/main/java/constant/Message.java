package constant;

public enum Message {
    INPUT_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    INPUT_MONEY("%s의 배팅 금액은?"),
    HAND_OUT_CARDS("%s와 %s, %s에게 2장의 나누었습니다."),
    INPUT_ONE_MORE_CARD("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"),
    GAME_OVER("%s는 21을 초과하여 패배하였습니다."),
    DEALER_ONE_MORE_CARD("%s는 16이하라 한장의 카드를 더 받았습니다."),
    FINAL_PROFIT("## 최종 수익"),

    RESULT(" - 결과%s %d"),
    DEALER("딜러"),
    CARD("카드"),
    DELIMITER(":"),
    COMMA(","),
    ;

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        if (message.equals(DEALER_ONE_MORE_CARD.message)) {
            return String.format(message, DEALER.getMessage());
        }
        return message;
    }

    public String getMessage(String name) {
        if (message.equals(INPUT_MONEY.getMessage())) {
            return String.format(message, name);
        }
        if (message.equals(INPUT_ONE_MORE_CARD.getMessage())) {
            return String.format(message, name);
        }
        if (message.equals(GAME_OVER.getMessage())) {
            return String.format(message, name);
        }
        return message;
    }

    public String getMessage(int amount) {
        if (message.equals(RESULT.getMessage())) {
            return String.format(message, DELIMITER.getMessage(), amount);
        }
        return message;
    }

    public String getMessage(String name1, String name2) {
        if (message.equals(HAND_OUT_CARDS.getMessage())) {
            return String.format(message, DEALER.getMessage(), name1, name2);
        }
        return message;
    }
}
