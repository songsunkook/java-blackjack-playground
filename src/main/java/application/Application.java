package application;

import controller.BlackJackController;

public class Application {
    private static BlackJackController blackJackController = new BlackJackController();

    public static void main(String[] args) {
        blackJackController.startGame();
    }
}
