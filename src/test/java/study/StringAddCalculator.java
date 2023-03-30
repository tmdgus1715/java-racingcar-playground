package study;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringAddCalculator {

    public static final String CUSTOM_DELIMITER_REGEX = "//(.)\n(.*)";

    /*
        쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환
        (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
        앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다.
        커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
        예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
        문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.
         */
    public static int splitAndSum(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        if (text.matches(CUSTOM_DELIMITER_REGEX)) {
            return splitCustomDelimiter(text);
        }
        String[] tokens = text.split(",|:");
        validateTokens(tokens);
        return sumTokens(tokens);
    }

    private static int sumTokens(String[] tokens) {
        return Stream.of(tokens).mapToInt(Integer::parseInt).sum();
    }

    private static int splitCustomDelimiter(String text) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        String customDelimiter = m.group(1);
        String[] tokens = m.group(2).split(customDelimiter);
        validateTokens(tokens);
        return sumTokens(tokens);
    }

    public static void validateTokens(String[] tokens) {
        Arrays.stream(tokens).forEach(token -> {
                    validateNumber(token);
                    validateNagative(token);
                }
        );
    }
    public static void validateNagative(String value) {
        if (Integer.parseInt(value) < 0) {
            throw new RuntimeException("validateNagative");
        }
    }

    public static void validateNumber(String value) {
        System.out.println(value);
        if (!value.matches("[0-9]")) {
            throw new RuntimeException("validateNumber");
        }
    }
}
