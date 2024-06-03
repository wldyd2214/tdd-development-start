package sample.tdd.unit.chap10.quiz.jiyong.impl;

import sample.tdd.unit.chap10.quiz.jiyong.CafeTimeProvider;

import java.time.LocalTime;

public class CafeFixedTimeProvider implements CafeTimeProvider {
    private LocalTime fixedTime;

    public CafeFixedTimeProvider(LocalTime localTime) {
        this.fixedTime = localTime;
    }

    @Override
    public LocalTime getCurrentTime() {
        return fixedTime;
    }
}
