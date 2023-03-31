package racingcargame.domain;

import java.util.List;

public class RacingCarGame {

	public static final int RANDOM_NUMBER_MIN_BOUNDARY = 0;
	public static final int RANDOM_NUMBER_MAX_BOUNDARY = 9;

	private Cars racingCars;

	public RacingCarGame() {
	}

	public void init(String carNames) {
		this.racingCars = new Cars(carNames);
	}

	public Cars getRacingCars() {
		return racingCars;
	}

	public void gameStart() {
		racingCars.tryMove(RANDOM_NUMBER_MIN_BOUNDARY, RANDOM_NUMBER_MAX_BOUNDARY);
	}

	public List<String> result() {
		return racingCars.findWinner();
	}
}
