package sample.tdd.unit.chap08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.tdd.unit.chap08.impl.FixedTimeProvider;

import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class TimeBasedGreetingTest {
    private TimeBasedGreeting greeting;

    private void setTimeProvider(LocalDateTime time) {
        TimeProvider timeProvider = new FixedTimeProvider(time);
        greeting = new TimeBasedGreeting(timeProvider);
    }
    
    @DisplayName("12시 이전이면 Good Morning!")
    @Test
    void testMorningGreeting() {
        setTimeProvider(LocalDateTime.of(2024, 5, 27, 9, 0));
        assertEquals("Good Morning!", greeting.getGreeting());
    }

    @DisplayName("12시에 18시 사이인 경우 Good Afternoon!")
    @Test
    public void testAfternoonGreeting() {
        setTimeProvider(LocalDateTime.of(2024, 5, 27, 15, 0));
        assertEquals("Good Afternoon!", greeting.getGreeting());
    }

    @DisplayName("오후 6시 이후인 경우 Good Evening!")
    @Test
    public void testEveningGreeting() {
        setTimeProvider(LocalDateTime.of(2024, 5, 27, 20, 0));
        assertEquals("Good Evening!", greeting.getGreeting());
    }
}
