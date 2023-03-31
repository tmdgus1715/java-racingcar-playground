package racingcargame.controller;

import racingcargame.domain.Cars;
import racingcargame.domain.RacingCarGame;
import racingcargame.view.InputView;
import racingcargame.view.OutputView;

public class GameController {

	public static final int COUNT_BOUNDARY = 0;
	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	private RacingCarGame racingCarGame;

	public void init() {
		this.racingCarGame = new RacingCarGame();
		String newCarNames = inputView.inputCarNames();
		racingCarGame.init(newCarNames);
		int count = inputView.inputChanceCount();
		gameStart(count);
	}

	public void gameStart(int count) {
		outputView.printResultMessage();
		while (count > COUNT_BOUNDARY) {
			racingCarGame.gameStart();
			--count;
			result();
		}
		outputView.printWinner(racingCarGame.result());
	}

	public void result() {
		Cars result = racingCarGame.getRacingCars();
		outputView.printResult(result.getCars());
	}
}
