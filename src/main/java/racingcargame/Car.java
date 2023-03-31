package racingcargame;

import racingcargame.utill.RandomGenerator;
import racingcargame.utill.RandomUtill;

public class Car {

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
		return movement;
	}

	private void move() {
		++movement;
	}
}
