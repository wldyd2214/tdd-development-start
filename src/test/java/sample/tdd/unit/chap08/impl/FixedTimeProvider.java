package sample.tdd.unit.chap08.impl;

import sample.tdd.unit.chap08.TimeProvider;

import java.time.LocalDateTime;

public class FixedTimeProvider implements TimeProvider {
    private LocalDateTime fixedTime;

    public FixedTimeProvider(LocalDateTime fixedTime) {
        this.fixedTime = fixedTime;
    }

    @Override
    public LocalDateTime getCurrentTime() {
        return fixedTime;
    }
}
