package racingcargame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import racingcargame.exception.DuplicateNameException;
import racingcargame.exception.InvalidNameLengthException;

public class Cars {

	private List<Car> cars;

	public Cars(String carNames) {
		if (carNames == null) {
			throw new NullPointerException();
		}
		String[] carNameTokens = carNames.split(",");

		validateCarNameLength(carNameTokens);
		validateDuplicateCarName(carNameTokens);

		List<Car> racingCars = new ArrayList<>();
		Arrays.stream(carNameTokens).forEach(carName -> racingCars.add(new Car(carName)));

		cars = racingCars;
	}

	private void validateDuplicateCarName(String[] carNameTokens) {
		List<String> validTokens = new ArrayList<>();

		Arrays.stream(carNameTokens).forEach(carName -> {
				if (validTokens.contains(carName)) {
					throw new DuplicateNameException();
				}
				validTokens.add(carName);
			}
		);
	}

	private void validateCarNameLength(String[] carNameTokens) {
		Arrays.stream(carNameTokens).forEach(carName -> {
				if (carName.length() < 1 || carName.length() > 5) {
					throw new InvalidNameLengthException();
				}
			}
		);
	}

	public List<Car> getCars() {
		return cars;
	}

	public void tryMove(int min, int max) {
		cars.forEach(car -> {
				car.tryMove(min, max);
			}
		);
	}

	public List<String> findWinner() {
		return null;
	}
}
