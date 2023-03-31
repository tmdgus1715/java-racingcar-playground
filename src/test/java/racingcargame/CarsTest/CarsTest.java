package racingcargame.CarsTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcargame.domain.Car;
import racingcargame.domain.Cars;

public class CarsTest {

	@Test
	@DisplayName("모든 Car들에게 4~9 범위의 랜덤 수를 생성하게 했을 때 모든 Car들이 전진한다.")
	public void moveCarsTest() {
		Cars cars = new Cars("car1,car2,car3");
		cars.tryMove(0, 3);

		cars.getCars().forEach(car -> {
				assertThat(car.getMovement()).isEqualTo(0);
			}
		);
	}

	@Test
	@DisplayName("모든 Car들에게 0~3 범위의 랜덤 수를 생성하게 했을 때 모든 Car들이 전진하지 않는다.")
	public void notMoveCarsTest() {
		Cars cars = new Cars("car1,car2,car3");
		cars.tryMove(4, 9);

		cars.getCars().forEach(car -> {
				assertThat(car.getMovement()).isEqualTo(1);
			}
		);
	}

	@Test
	@DisplayName("모든 Car들 중에서 movement가 가장 높은 Car가 한 개일 경우 가장 높은 Car의 name을 반환한다.")
	public void findWinnerTest_한_명일_때() {
		Cars cars = new Cars("car1,car2,car3");
		List<Car> racingCars = cars.getCars();

		racingCars.get(0).tryMove(4,9);
		racingCars.get(0).tryMove(4,9);//car1 두 번 전진
		racingCars.get(1).tryMove(4,9);//car2 한 번 전진, car3 전진하지 않음.

		String winner = cars.findWinner().get(0);
		assertThat(winner).isEqualTo("car1");
	}

	@Test
	@DisplayName("모든 Car들 중에서 movement가 가장 높은 Car가 여러 개일 경우 해당하는 모든 Car의 name을 반환한다.")
	public void findWinnerTest_여러_명일_때() {
		Cars cars = new Cars("car1,car2,car3");
		List<Car> racingCars = cars.getCars();

		racingCars.get(0).tryMove(4,9);
		racingCars.get(0).tryMove(4,9);//car1 두 번 전진
		racingCars.get(1).tryMove(4,9);
		racingCars.get(1).tryMove(4,9);//car2 두 번 전진, car3 전진하지 않음.

		String winner1 = cars.findWinner().get(0);
		String winner2 = cars.findWinner().get(1);
		assertThat(winner1).isEqualTo("car1");
		assertThat(winner2).isEqualTo("car2");
	}
}
