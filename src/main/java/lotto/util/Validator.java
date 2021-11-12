package lotto.util;

import static lotto.util.InputParser.*;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern LOTTO_NUMBER_PATTERN = Pattern.compile("\\d+(" + NUMBER_DELIMITER + "\\d+){5}");

    private static final String INVALID_NUMBER_INPUT_ERR_MSG = "숫자만 입력 가능합니다.";
    private static final String INVALID_LOTTO_NUMBER_INPUT_ERR_MSG = "잘못된 형식의 로또 번호입니다.";

    private Validator() {
    }

    static void validateNumberPattern(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(INVALID_NUMBER_INPUT_ERR_MSG);
        }
    }

    static void validateLottoPattern(String lottoNumbers) {
        if (!LOTTO_NUMBER_PATTERN.matcher(lottoNumbers).matches()) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_INPUT_ERR_MSG);
        }
    }
}
