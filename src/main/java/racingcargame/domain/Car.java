package racingcargame.domain;

import racingcargame.utill.RandomGenerator;
import racingcargame.utill.RandomUtill;

public class Car implements Comparable {

	private final RandomUtill randomUtill = new RandomGenerator();
	private final String name;
	private int movement = 0;

	public Car(String carName) {
		this.name = carName;
	}

	public void tryMove(int min, int max) {
		if (randomUtill.generateRandomNumber(min, max) > 3) {
			move();
		}
	}

	public int getMovement() {
		return this.movement;
	}

	private void move() {
		++movement;
	}

	@Override
	public int compareTo(Object o) {
		Car car = (Car)o;
		if (this.getMovement() > car.getMovement()) {
			return 1;
		}
		return -1;
	}

	public String getName() {
		return this.name;
	}
}
