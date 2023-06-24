package controller;

import constant.Message;
import domain.Dealer;
import domain.Person;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackJackController {
    List<Person> person = new ArrayList<>();

    public void startGame() {
        setPerson();
        divideTwoCards();
        oneMoreCards();
    }

    private void setPerson() {
        person.add(new Dealer());
        person.add(new Player());
        person.add(new Player());
        setNames();
        setMoneys();
    }

    private void setNames() {
        OutputView.println(Message.INPUT_NAME.getMessage());
        List<String> names = InputView.inputNames();
        person.get(0).setName(Message.DEALER.getMessage());
        person.get(1).setName(names.get(0));
        person.get(2).setName(names.get(1));
        OutputView.println();
    }

    private void setMoneys() {
        setMoney(1);
        setMoney(2);
    }

    private void setMoney(int index) {
        OutputView.println(Message.INPUT_MONEY.getMessage(person.get(index).getName()));
        person.get(index).setMoney(InputView.inputMoney());
        OutputView.println();
    }

    private void divideTwoCards() {
        OutputView.println(Message.HAND_OUT_CARDS.getMessage(person.get(1).getName(), person.get(2).getName()));
        person.forEach(user -> user.drawCard(2));
        IntStream.range(0, person.size()).forEach(this::outputCards);
        OutputView.println();
    }

    private void outputCards(int userIndex) {
        int cardAmount = person.get(userIndex).cardAmount();
        String cards = IntStream.range(0, cardAmount)
                .mapToObj(i -> person.get(userIndex).getCardInfo(i) + Message.COMMA.getMessage() + " ")
                .collect(Collectors.joining());
        cards = cards.substring(0, cards.length() - 2);

        OutputView.println(
                person.get(userIndex).getName(),
                Message.CARD.getMessage(),
                Message.DELIMITER.getMessage(),
                cards
        );
    }

    private void oneMoreCards() {
        oneMoreCard(1);
        oneMoreCard(2);
    }

    private void oneMoreCard(int index) {
        do {
             OutputView.println(Message.INPUT_ONE_MORE_CARD.getMessage(person.get(index).getName()));
             if (!InputView.inputOneMoreCard()) {
                 break;
             }
             person.get(index).drawCard(1);
             outputCards(index);
        } while (person.get(index).survive());

        if(!person.get(index).survive()) {
            OutputView.println(Message.GAME_OVER.getMessage(person.get(index).getName()));
        }
    }
}
