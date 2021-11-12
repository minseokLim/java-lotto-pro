package lotto.model;

import java.util.Collection;
import java.util.Objects;

import lotto.model.enums.Rank;

public class LottoMatcher {
    static final String DUPLICATE_BONUS_NUMBER_ERR_MSG = "보너스 번호는 당첨번호와 겹칠 수 없습니다.";

    private final Number bonusNumber;
    private final Lotto winningLotto;

    public LottoMatcher(int bonusNumber, int... winningNumbers) {
        this(Number.of(bonusNumber), new Lotto(winningNumbers));
    }

    public LottoMatcher(Number bonusNumber, Lotto winningLotto) {
        this.bonusNumber = Objects.requireNonNull(bonusNumber);
        this.winningLotto = Objects.requireNonNull(winningLotto);

        validate();
    }

    private void validate() {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_ERR_MSG);
        }
    }

    public MatchResult match(Payment payment, Lottos lottos) {
        Objects.requireNonNull(payment);
        Objects.requireNonNull(lottos);

        Collection<Rank> ranks = lottos.computeRanks(bonusNumber, winningLotto);
        return new MatchResult(payment, ranks);
    }
}
