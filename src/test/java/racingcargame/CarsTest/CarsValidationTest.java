package racingcargame.CarsTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcargame.Cars;
import racingcargame.exception.DuplicateNameException;
import racingcargame.exception.InvalidNameLengthException;

public class CarsValidationTest {

	@Test
	@DisplayName("carNames가 null이면 NullPointerException이 발생한다.")
	public void carNames_null() {
		String carNames = null;
		assertThatThrownBy(() -> new Cars(carNames)).isInstanceOf(NullPointerException.class);
	}

	@ParameterizedTest
	@DisplayName("carNames의 길이는 1~5범위가 아니면 InvalidNameLengthException이 발생한다.")
	@ValueSource(strings = {"", "testVa", "test,testVa"})
	public void carNamesLength(String carNames) {
		assertThatThrownBy(() -> new Cars(carNames)).isInstanceOf(InvalidNameLengthException.class);
	}

	@ParameterizedTest
	@DisplayName("carNames에 중복이 있을 경우 DuplicateNameException이 발생한다")
	@ValueSource(strings = {"test,test", "test,test,test", "t,test,t"})
	public void carNamesDuplicate(String carNames) {
		assertThatThrownBy(() -> new Cars(carNames)).isInstanceOf(DuplicateNameException.class);
	}

	@ParameterizedTest
	@DisplayName("중복되지 않고, 길이가 1~5의 carName으로 이루어진 문자열이 입력되면 Cars는 각각의 name을 필드로 갖는 Car객체로 이루어진 List를 가진다")
	@CsvSource(value = {"abc,ab,abcd,a:4", "a,ab:2",
		"a,ab,abc,abcd,abcde,defgh,efghi:7"}, delimiter = ':')
	public void carNames(String carNames, int expectedSize) {
		Cars cars = new Cars(carNames);
		assertThat(cars.getCars().size()).isEqualTo(expectedSize);
	}
}
