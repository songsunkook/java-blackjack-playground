package domain;

import constant.card.Mark;
import constant.card.Number;

import java.util.stream.Stream;

public class Card {
    private final int number;
    private final Mark mark;

    public Card(int number, Mark mark) {
        this.number = number;
        this.mark = mark;
    }

    public String getCardName() {
        return getNumberName() + getMarkName();
    }

    private String getNumberName() {
        if (number == Number.ACE.getNumber() || number >= Number.JACK.getNumber()) {
            char result = Stream.of(Number.values())
                    .filter(numberElement -> numberElement.getNumber() == number)
                    .findFirst()
                    .get()
                    .toString()
                    .charAt(0);
            return Character.toString(result);
        }
        return Integer.toString(number);
    }

    private String getMarkName() {
        return Stream.of(Mark.values())
                .filter(markElemnet -> markElemnet == mark)
                .findFirst()
                .get()
                .getMessage();
    }

    public int getNumber() {
        if (number > Number.JACK.getNumber()) {
            return Number.JACK.getNumber();
        }
        return number;
    }
}
