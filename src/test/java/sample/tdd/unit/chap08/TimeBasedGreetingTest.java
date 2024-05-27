package sample.tdd.unit.chap08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeBasedGreetingTest {
    
    @DisplayName("testMorningGreeting")
    @Test
    void testMorningGreeting() {
        TimeBasedGreeting timeBasedGreeting = new TimeBasedGreeting();
        assertEquals("Good Morning!", timeBasedGreeting.getGreeting());
    }

    @Test
    public void testAfternoonGreeting() {
        TimeBasedGreeting timeBasedGreeting = new TimeBasedGreeting();
        assertEquals("Good Afternoon!", timeBasedGreeting.getGreeting());
    }

    @Test
    public void testEveningGreeting() {
        TimeBasedGreeting timeBasedGreeting = new TimeBasedGreeting();
        assertEquals("Good Afternoon!", timeBasedGreeting.getGreeting());
    }
}
