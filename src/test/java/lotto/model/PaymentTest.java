package lotto.model;

import static lotto.model.Payment.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PaymentTest {
    @ParameterizedTest
    @DisplayName("지불 금액이 " + LOTTO_PRICE + "원 미만이거나 " + LOTTO_PRICE + "으로 나누어 떨어지지 않을 때 예외 발생")
    @ValueSource(ints = {LOTTO_PRICE - 1, LOTTO_PRICE + 1})
    void 객체_생성_시_유효성_검사(int payment) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Payment(payment));
    }

    @Test
    @DisplayName("구매금액이 주어졌을 때 적절한 로또의 갯수를 반환하는지 테스트")
    void countLotto() {
        Payment payment = new Payment(14000);
        int count = payment.countLotto();
        assertThat(count).isEqualTo(14000 / LOTTO_PRICE);
    }

    @Test
    @DisplayName("당첨금액이 주어졌을 때 적절한 수익률을 반환하는지 테스트")
    void computeRateOfReturn() {
        Payment payment = new Payment(14000);
        RateOfReturn rateOfReturn = payment.computeRateOfReturn(30000);
        assertThat(rateOfReturn).isEqualTo(new RateOfReturn((double)30000 / 14000));
    }

    @Test
    @DisplayName("동등성 검사")
    void equals() {
        assertThat(new Payment(14000)).isEqualTo(new Payment(14000));
    }
}
