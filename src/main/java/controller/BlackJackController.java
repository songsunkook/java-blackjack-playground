package controller;

import constant.Constants;
import constant.Message;
import domain.Dealer;
import domain.Person;
import domain.Player;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackJackController {
    //private List<Person> person = new ArrayList<>();
    private final BlackJackService blackJackService = new BlackJackService();

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
        setNames();
        setMoneys();
    }

    private void setNames() {
        OutputView.println(Message.INPUT_NAME.getMessage());
        blackJackService.setNames(InputView.inputNames());
        OutputView.println();
    }

    private void setMoneys() {
        blackJackService.setMoneyForDealer();
        setMoney(Constants.PLAYER1_PERSON_INDEX.get());
        setMoney(Constants.PLAYER2_PERSON_INDEX.get());
    }

    private void setMoney(int index) {
        OutputView.println(blackJackService.inputMoneyMessage(index));
        blackJackService.setMoney(index, InputView.inputMoney());
        OutputView.println();
    }

    private void divideTwoCards() {
        OutputView.println(blackJackService.divideTwoCardsMessage());
        blackJackService.drawCard(Constants.DEALER_PERSON_INDEX.get(), 2);
        blackJackService.drawCard(Constants.PLAYER1_PERSON_INDEX.get(), 2);
        blackJackService.drawCard(Constants.PLAYER2_PERSON_INDEX.get(), 2);
        IntStream.range(Constants.DEALER_PERSON_INDEX.get(), Constants.PLAYER2_PERSON_INDEX.get() + 1).forEach(i -> {
            outputCards(i);
            OutputView.println();
        });
        OutputView.println();
    }

    private void outputCards(int index) {
        OutputView.print(
                blackJackService.getName(index),
                Message.CARD.getMessage(),
                Message.DELIMITER.getMessage(),
                blackJackService.outputCards(index)
        );
    }

    public boolean checkBlackJacks() {
        if (blackJackService.checkBlackJack(Constants.PLAYER1_PERSON_INDEX.get())) {
            return true;
        }
        if (blackJackService.checkBlackJack(Constants.PLAYER2_PERSON_INDEX.get())) {
            return true;
        }
        return false;
    }

    private void oneMoreCards() {
        oneMoreCard(Constants.PLAYER1_PERSON_INDEX.get());
        oneMoreCard(Constants.PLAYER2_PERSON_INDEX.get());
        OutputView.println();
        if (blackJackService.checkDealerDrawsAnotherCard()) {
            oneMoreCardForDealer();
        }
    }

    private void oneMoreCard(int index) {
        do {
            OutputView.println(blackJackService.inputOneMoreCard(index));
            if (!InputView.inputOneMoreCard()) {
                break;
            }
            blackJackService.drawCard(index, 1);
            outputCards(index);
            OutputView.println();
        } while (blackJackService.survive(index));

        if (!blackJackService.survive(index)) {
            OutputView.println(blackJackService.gameOverMessage(index));
        }
    }

    private void oneMoreCardForDealer() {
        OutputView.println(Message.DEALER_ONE_MORE_CARD.getMessage());
        blackJackService.drawCard(Constants.DEALER_PERSON_INDEX.get(), 1);
        OutputView.println();
    }

    private void totalResults() {
        totalResult(Constants.DEALER_PERSON_INDEX.get());
        totalResult(Constants.PLAYER1_PERSON_INDEX.get());
        totalResult(Constants.PLAYER2_PERSON_INDEX.get());
        OutputView.println();
    }

    private void totalResult(int index) {
        outputCards(index);
        OutputView.println(blackJackService.totalResultMessage(index));
    }

    private void finalProfits() {
        OutputView.println(Message.FINAL_PROFIT.getMessage());
        calculateFinalProfits();
        outputFinalProfits();
    }

    private void calculateFinalProfits() {
        blackJackService.calculateFinalProfit(Constants.PLAYER1_PERSON_INDEX.get());
        blackJackService.calculateFinalProfit(Constants.PLAYER2_PERSON_INDEX.get());
    }

    public void outputFinalProfits() {
        outputFinalProfit(Constants.DEALER_PERSON_INDEX.get());
        outputFinalProfit(Constants.PLAYER1_PERSON_INDEX.get());
        outputFinalProfit(Constants.PLAYER2_PERSON_INDEX.get());
    }

    private void outputFinalProfit(int index) {
        OutputView.println(blackJackService.finalProfitMessage(index));
    }
}
