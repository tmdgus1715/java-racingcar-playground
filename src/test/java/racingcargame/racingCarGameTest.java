package racingcargame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcargame.domain.Car;
import racingcargame.domain.Cars;
import racingcargame.domain.RacingCarGame;

public class racingCarGameTest {

	@ParameterizedTest
	@DisplayName("전달한 carNames대로 Car들이 생성이 된다.")
	@CsvSource(value = {"abc,ab,abcd,a:4", "a,ab:2",
		"a,ab,abc,abcd,abcde,defgh,efghi:7"}, delimiter = ':')
	public void initTest(String carNames, int expectedSize) {
		RacingCarGame racingCarGame = new RacingCarGame();
		racingCarGame.init(carNames);

		Cars racingCars = racingCarGame.getRacingCars();

		assertThat(racingCars.getCars().size()).isEqualTo(expectedSize);
	}

	@Test
	@DisplayName("Car의 name이 담긴 List<String>이 반환된다.")
	public void resultTest() {
		RacingCarGame racingCarGame = new RacingCarGame();
		racingCarGame.init("car1,car2,car3");

		Cars racingCars = racingCarGame.getRacingCars();
		racingCars.tryMove(4, 9);

		List<String> result = racingCarGame.result();
		assertThat(result.get(0)).isEqualTo("car1");
		assertThat(result.get(1)).isEqualTo("car2");
		assertThat(result.get(2)).isEqualTo("car3");
	}
}
