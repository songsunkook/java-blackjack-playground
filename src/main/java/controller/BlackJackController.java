package controller;

import constant.Message;
import domain.Dealer;
import domain.Person;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    List<Person> person = new ArrayList<>();

    public void startGame() {
        setPerson();
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
        person.get(1).setName(names.get(0));
        person.get(2).setName(names.get(1));
        OutputView.println("");
    }

    private void setMoneys() {
        setMoney(1);
        setMoney(2);
    }

    private void setMoney(int index) {
        OutputView.println(Message.INPUT_MONEY.getMessage(person.get(index).getName()));
        person.get(index).setMoney(InputView.inputMoney());
        OutputView.println("");
    }


}
