package racingcargame;

import racingcargame.controller.GameController;

public class RacingCarGameApp {

	public static void main(String[] args) {
		GameController gameController = new GameController();
		gameController.init();
	}
}
