import constant.Message;
import constant.card.Mark;
import controller.BlackJackController;
import domain.Card;
import domain.Dealer;
import domain.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.BlackJackService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class BlackJackTest {
    BlackJackController blackJackController = new BlackJackController();
    BlackJackService blackJackService = new BlackJackService();

    @ParameterizedTest
    @MethodSource("cardArguments")
    public void 블랙잭(List<Card> cards1, List<Card> cards2, List<Card> cards3) {
        Dealer dealer = new Dealer();
        Player player1 = new Player();
        Player player2 = new Player();

        dealer.setName(Message.DEALER.getMessage());
        player1.setName("하나");
        player2.setName("두울");

        dealer.setMoney(0);
        player1.setMoney(10);
        player2.setMoney(100);

        dealer.addCard(cards1.get(0));
        dealer.addCard(cards1.get(1));

        player1.addCard(cards2.get(0));
        player2.addCard(cards2.get(1));

        player1.addCard(cards3.get(0));
        player2.addCard(cards3.get(1));

        blackJackService.setPerson(dealer, player1, player2);
        blackJackController.checkBlackJacks();
        blackJackController.outputFinalProfits();
    }

    private static Stream<Arguments> cardArguments() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(new Card(2, Mark.CLOVER), new Card(10, Mark.DIAMOND)),
                        Arrays.asList(new Card(1, Mark.HEART), new Card(3, Mark.CLOVER)),
                        Arrays.asList(new Card(10, Mark.DIAMOND), new Card(1, Mark.SPADE))
                )
        );
    }
}
