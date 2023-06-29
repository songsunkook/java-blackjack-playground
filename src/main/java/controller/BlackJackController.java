package controller;

import constant.Constants;
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
    private List<Person> person = new ArrayList<>();

    public void startGame() {
        setPerson();
        divideTwoCards();
        if (checkBlackJacks()) {
            finalProfits();
            return;
        }
        oneMoreCards();
        totalResults();
        finalProfits();
    }

    private void setPerson() {
        person.add(new Dealer());
        person.add(new Player());
        person.add(new Player());
        setNames();
        setMoneys();
    }

    public void setPerson(Dealer dealer, Player player1, Player player2) {
        person.add(dealer);
        person.add(player1);
        person.add(player2);
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
        person.get(0).setMoney(0);
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
        IntStream.range(0, person.size()).forEach(i -> {
            outputCards(i);
            OutputView.println();
        });
        OutputView.println();
    }

    private void outputCards(int userIndex) {
        int cardAmount = person.get(userIndex).cardAmount();
        String cards = IntStream.range(0, cardAmount)
                .mapToObj(i -> person.get(userIndex).getCardInfo(i) + Message.COMMA.getMessage() + " ")
                .collect(Collectors.joining());
        cards = cards.substring(0, cards.length() - 2);

        OutputView.print(
                person.get(userIndex).getName(),
                Message.CARD.getMessage(),
                Message.DELIMITER.getMessage(),
                cards
        );
    }

    public boolean checkBlackJacks() {
        if (checkBlackJack(1)) {
            return true;
        }
        if (checkBlackJack(2)) {
            return true;
        }
        return false;
    }

    private boolean checkBlackJack(int index) {
        int playerMoney = person.get(index).getMoney();

        if (person.get(index).blackJack()) {
            if (person.get(0).blackJack()) {
                person.get(index).firstTurnBlackJackFrom(true);
                ((Dealer) person.get(0)).firstTurnBlackJackFrom(true, playerMoney);
            }
            person.get(index).firstTurnBlackJackFrom(false);
            ((Dealer) person.get(0)).firstTurnBlackJackFrom(false, playerMoney);
            return true;
        }
        return false;
    }

    private void oneMoreCards() {
        oneMoreCard(1);
        oneMoreCard(2);
        OutputView.println();
        if (person.get(0).cardAmount() < Constants.DEALER_DRAWS_ANOTHER_CARD.get()) {
            oneMoreCardForDealer();
        }
    }

    private void oneMoreCard(int index) {
        do {
            OutputView.println(Message.INPUT_ONE_MORE_CARD.getMessage(person.get(index).getName()));
            if (!InputView.inputOneMoreCard()) {
                break;
            }
            person.get(index).drawCard(1);
            outputCards(index);
            OutputView.println();
        } while (person.get(index).survive());

        if (!person.get(index).survive()) {
            OutputView.println(Message.GAME_OVER.getMessage(person.get(index).getName()));
        }
    }

    private void oneMoreCardForDealer() {
        OutputView.println(Message.DEALER_ONE_MORE_CARD.getMessage());
        person.get(0).drawCard();
        OutputView.println();
    }

    private void totalResults() {
        totalResult(0);
        totalResult(1);
        totalResult(2);
        OutputView.println();
    }

    private void totalResult(int index) {
        outputCards(index);
        OutputView.println(Message.RESULT.getMessage(person.get(index).getTotalNumber()));
    }

    private void finalProfits() {
        OutputView.println(Message.FINAL_PROFIT.getMessage());
        calculateFinalProfits();
        outputFinalProfits();
    }


    private void calculateFinalProfits() {
        calculateFinalProfit(1);
        calculateFinalProfit(2);
    }

    private void calculateFinalProfit(int index) {
        int playerTotalNumber = person.get(index).getTotalNumber();
        int dealerTotalNumber = person.get(0).getTotalNumber();
        int playerMoney = person.get(index).getMoney();

        if (!person.get(index).survive()) {
            person.get(index).lose();
            ((Dealer) person.get(0)).win(playerMoney);
            return;
        }
        if (playerTotalNumber > dealerTotalNumber) {
            person.get(index).win();
            ((Dealer) person.get(0)).lose(playerMoney);
            return;
        }
        if (playerTotalNumber < dealerTotalNumber) {
            person.get(index).lose();
            ((Dealer) person.get(0)).win(playerMoney);
        }
    }

    public void outputFinalProfits() {
        outputFinalProfit(0);
        outputFinalProfit(1);
        outputFinalProfit(2);
    }

    private void outputFinalProfit(int index) {
        OutputView.println(person.get(index).getName() + Message.DELIMITER.getMessage() + " " + person.get(index).getGain());
    }
}
