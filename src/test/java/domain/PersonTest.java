package domain;

import constant.card.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest {
    @ParameterizedTest
    @MethodSource("cardArguments")
    public void 카드받기(Card card1, Card card2, Card card3) {
        Dealer dealer = new Dealer();
        Player player1 = new Player();
        Player player2 = new Player();

        dealer.addCard(card1);
        player1.addCard(card2);
        player2.addCard(card3);

        assertEquals(dealer.getCardInfo(0), card1.getCardName());
        assertEquals(player1.getCardInfo(0), card2.getCardName());
        assertEquals(player2.getCardInfo(0), card3.getCardName());
    }

    private static Stream<Arguments> cardArguments() {
        return Stream.of(
                Arguments.of(new Card(1, Mark.CLOVER), new Card(1, Mark.DIAMOND), new Card(1, Mark.SPADE)),
                Arguments.of(new Card(2, Mark.HEART), new Card(3, Mark.CLOVER), new Card(13, Mark.DIAMOND)),
                Arguments.of(new Card(10, Mark.DIAMOND), new Card(11, Mark.SPADE), new Card(12, Mark.CLOVER))
        );
    }
}
