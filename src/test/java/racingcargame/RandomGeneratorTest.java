package racingcargame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcargame.utill.RandomGenerator;
import racingcargame.utill.RandomUtill;

public class RandomGeneratorTest {

	@ParameterizedTest
	@DisplayName("범위 내의 랜덤 수를 생성하여 반환한다.")
	@CsvSource(value = {"0:9", "100:109", "1000:1009"}, delimiter = ':')
	public void generate(int min, int max) {
		RandomUtill randomGenerator = new RandomGenerator();
		Assertions.assertThat(randomGenerator.generateRandomNumber(min, max)).isBetween(min, max);
	}

}
