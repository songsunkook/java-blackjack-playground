package controller;

import domain.Dealer;
import domain.Person;
import domain.Player;
import view.InputView;

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
    }

    private void setNames() {
        List<String> names = InputView.inputNames();
        person.get(1).setName(names.get(0));
        person.get(2).setName(names.get(1));
    }
}
