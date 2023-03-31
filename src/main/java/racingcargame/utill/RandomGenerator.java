package racingcargame.utill;

import java.util.Random;

public class RandomGenerator implements RandomUtill{

	private final Random random = new Random();

	@Override
	public int generateRandomNumber(int min, int max) {
		return random.nextInt(min, max + 1);
	}
}
