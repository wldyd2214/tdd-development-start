package sample.tdd.unit.chap08.impl;

import sample.tdd.unit.chap08.TimeProvider;

import java.time.LocalDateTime;

public class SystemTimeProvider implements TimeProvider {
    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
