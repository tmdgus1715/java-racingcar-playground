package racingcargame;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {

	@Test
	@DisplayName("0~3범위의 랜덤 수가 입력되면 Car는 전진하지 않는다")
	public void notMoveTest() {
		Car car1 = new Car("car1");
		Car car2 = new Car("car2");

		car1.tryMove(0, 3);
		car2.tryMove(0, 3);

		assertThat(car1.getMovement()).isEqualTo(0);
		assertThat(car2.getMovement()).isEqualTo(0);
	}

	@Test
	@DisplayName("4~9범위의 랜덤 수가 입력되면 Car는 전진한다")
	public void moveTest() {
		Car car1 = new Car("car1");
		Car car2 = new Car("car2");

		car1.tryMove(4, 9);
		car2.tryMove(4, 9);

		assertThat(car1.getMovement()).isEqualTo(1);
		assertThat(car2.getMovement()).isEqualTo(1);
	}

}
