package lotto.model;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import lotto.model.enums.Rank;

public class Lotto {
    public static final int NUMBER_SIZE = 6;
    static final String NUMBER_SIZE_ERR_MSG = "로또 숫자는 서로 다른 " + NUMBER_SIZE + "개로 구성되어야 합니다.";

    private final SortedSet<Number> numbers;

    public Lotto(Collection<Integer> numbers) {
        TreeSet<Number> sortedNumbers = Objects.requireNonNull(numbers)
            .stream()
            .map(Number::of)
            .collect(toCollection(TreeSet::new));
        this.numbers = Collections.unmodifiableSortedSet(sortedNumbers);
        validate();
    }

    Lotto(int... numbers) {
        this(Arrays.stream(numbers)
            .boxed()
            .collect(toSet()));
    }

    private void validate() {
        if (numbers.size() != NUMBER_SIZE) {
            throw new IllegalArgumentException(NUMBER_SIZE_ERR_MSG);
        }
    }

    public boolean contains(Number obj) {
        return numbers.contains(obj);
    }

    public Rank computeRank(Number bonusNumber, Lotto winningLotto) {
        Objects.requireNonNull(bonusNumber);
        Objects.requireNonNull(winningLotto);

        int countOfMatch = winningLotto.countMatches(numbers);
        boolean matchBonus = numbers.contains(bonusNumber);
        return Rank.valueOf(countOfMatch, matchBonus);
    }

    private int countMatches(Set<Number> others) {
        return (int)others.stream()
            .filter(this.numbers::contains)
            .count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
