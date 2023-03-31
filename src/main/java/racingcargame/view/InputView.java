package racingcargame.view;

import java.util.Scanner;

public class InputView {

	public static final String CARNAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요";
	public static final String CHANCE_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";

	private Scanner scanner = new Scanner(System.in);

	public String inputCarNames() {
		System.out.println(CARNAMES_INPUT_MESSAGE);
		return scanner.next();
	}

	public int inputChanceCount() {
		System.out.println(CHANCE_INPUT_MESSAGE);
		return scanner.nextInt();
	}
}
