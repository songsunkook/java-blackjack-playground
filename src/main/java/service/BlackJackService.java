package service;

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

public class BlackJackService {
    private List<Person> person = new ArrayList<>();

    public BlackJackService() {
        person.add(new Dealer());
        person.add(new Player());
        person.add(new Player());
    }

    public BlackJackService(Dealer dealer, Player player1, Player player2) {
        person.add(dealer);
        person.add(player1);
        person.add(player2);
    }

    public void setNames(List<String> names) {
        person.get(0).setName(Message.DEALER.getMessage());
        person.get(1).setName(names.get(0));
        person.get(2).setName(names.get(1));
    }


    public void setMoney(int index, int money) {
        person.get(index).setMoney(money);
    }

    public void setMoneyForDealer() {
        person.get(Constants.DEALER_PERSON_INDEX.get()).setMoney(0);
    }

    public String getName(int index) {
        return person.get(index).getName();
    }

    public void drawCard(int index, int amount) {
        person.get(index).drawCard(amount);
    }

    public boolean checkBlackJack(int index) {
        int playerMoney = person.get(index).getMoney();

        if (person.get(index).blackJack()) {
            if (person.get(Constants.DEALER_PERSON_INDEX.get()).blackJack()) {
                person.get(index).firstTurnBlackJackFrom(true);
                ((Dealer) person.get(Constants.DEALER_PERSON_INDEX.get())).firstTurnBlackJackFrom(true, playerMoney);
            }
            person.get(index).firstTurnBlackJackFrom(false);
            ((Dealer) person.get(Constants.DEALER_PERSON_INDEX.get())).firstTurnBlackJackFrom(false, playerMoney);
            return true;
        }
        return false;
    }

    public boolean checkDealerDrawsAnotherCard() {
        return person.get(Constants.DEALER_PERSON_INDEX.get()).cardAmount() < Constants.DEALER_DRAWS_ANOTHER_CARD.get();
    }

    public void calculateFinalProfit(int index) {
        int playerTotalNumber = person.get(index).getTotalNumber();
        int dealerTotalNumber = person.get(Constants.DEALER_PERSON_INDEX.get()).getTotalNumber();

        if (!person.get(index).survive()) {
            lose(index);
            return;
        }
        if (!person.get(Constants.DEALER_PERSON_INDEX.get()).survive()) {
            win(index);
            return;
        }
        if (playerTotalNumber > dealerTotalNumber) {
            win(index);
            return;
        }
        if (playerTotalNumber < dealerTotalNumber) {
            lose(index);
        }
    }

    private void win(int index) {
        ((Dealer) person.get(Constants.DEALER_PERSON_INDEX.get())).lose(person.get(index).getMoney());
        person.get(index).win();
    }

    private void lose(int index) {
        ((Dealer) person.get(Constants.DEALER_PERSON_INDEX.get())).win(person.get(index).getMoney());
        person.get(index).lose();
    }

    public String inputMoneyMessage(int index) {
        return Message.INPUT_MONEY.getMessage(person.get(index).getName());
    }

    public String divideTwoCardsMessage() {
        return Message.HAND_OUT_CARDS.getMessage(
                person.get(Constants.PLAYER1_PERSON_INDEX.get()).getName(),
                person.get(Constants.PLAYER2_PERSON_INDEX.get()).getName());
    }

    public String outputCards(int index) {
        int cardAmount = person.get(index).cardAmount();
        String cards = IntStream.range(0, cardAmount)
                .mapToObj(i -> person.get(index).getCardInfo(i) + Message.COMMA.getMessage() + " ")
                .collect(Collectors.joining());
        return cards.substring(0, cards.length() - 2);
    }

    public String inputOneMoreCard(int index) {
        return Message.INPUT_ONE_MORE_CARD.getMessage(person.get(index).getName());
    }

    public boolean survive(int index) {
        return person.get(index).survive();
    }

    public String gameOverMessage(int index) {
        return Message.GAME_OVER.getMessage(person.get(index).getName());
    }

    public String totalResultMessage(int index) {
        return Message.RESULT.getMessage(person.get(index).getTotalNumber());
    }

    public String finalProfitMessage(int index) {
        return person.get(index).getName() + Message.DELIMITER.getMessage() + " " + person.get(index).getGain();
    }
}
