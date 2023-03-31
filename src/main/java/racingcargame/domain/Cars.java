package racingcargame.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

	public void tryMove(int min, int max) {
		cars.forEach(car -> {
				car.tryMove(min, max);
			}
		);
	}

	public List<String> findWinner() {
		Optional<Car> winnerCar = cars.stream().max(Car::compareTo);

		int maxMovement = 0;
		if (winnerCar.isPresent()) {
			maxMovement = winnerCar.get().getMovement();
		}

		List<String> winners = new ArrayList<>();
		for (Car car : cars) {
			compareMovement(car, maxMovement, winners);
		}
		return winners;
	}

	public void compareMovement(Car car, int max, List<String> winners) {
		if (car.getMovement() == max) {
			winners.add(car.getName());
		}
	}

	public List<Car> getCars() {
		return cars;
	}
}
