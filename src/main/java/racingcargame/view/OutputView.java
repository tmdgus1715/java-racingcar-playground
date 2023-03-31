package racingcargame.view;

import java.util.List;
import racingcargame.domain.Car;

public class OutputView {

	public static final String RESULT_MESSAGE = "실행 결과";
	public static final String WINNER_MESSAGE = "가 최종 우승했습니다.";
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final String RESULT_DELIMITER = " : ";

	public void printResultMessage() {
		System.out.println(RESULT_MESSAGE);
	}

	public void printResult(List<Car> cars) {
		cars.forEach(car -> {
			System.out.print(car.getName() + RESULT_DELIMITER);
			printMovement(car.getMovement());
		});
		System.out.println();
	}

	public void printMovement(int movement) {
		while (movement > ZERO) {
			System.out.print("-");
			--movement;
		}
		System.out.println();
	}

	public void printWinner(List<String> winners) {
		for (int i = ZERO; i < winners.size() - ONE; ++i) {
			System.out.print(winners.get(i) + ", ");
		}
		System.out.println(winners.get(winners.size() - ONE) + WINNER_MESSAGE);
	}
}
