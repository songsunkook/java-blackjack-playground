package domain;

import constant.card.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("카드 ")
public class CardTest {
    @ParameterizedTest
    @DisplayName("이름을 정확하게 불러오는가")
    @MethodSource("getCardNameArguments")
    public void getCardNameTest(int cardNumber, Mark cardMark, String displayName) {
        assertEquals(displayName, new Card(cardNumber, cardMark).getCardName());
    }

    private static Stream<Arguments> getCardNameArguments() {
        return Stream.of(
                Arguments.of(1, Mark.DIAMOND, "A다이아몬드"),
                Arguments.of(7, Mark.SPADE, "7스페이드"),
                Arguments.of(10, Mark.HEART, "10하트"),
                Arguments.of(13, Mark.CLOVER, "K클로버")
        );
    }
}