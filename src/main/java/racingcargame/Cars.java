package racingcargame;

import java.util.List;

public class Cars {

	private List<Car> racingCars;
	public Cars(String carNames) {
		if (carNames == null) {
			throw new NullPointerException();
		}
	}

	public List<Car> getRacingCars() {
		return racingCars;
	}
}
