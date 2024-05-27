package sample.tdd.unit.chap08;

import sample.tdd.unit.chap08.impl.SystemTimeProvider;

import java.time.LocalDateTime;

public class TimeBasedGreeting {
    private TimeProvider timeProvider;

    public TimeBasedGreeting(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public String getGreeting() {
        LocalDateTime now = timeProvider.getCurrentTime();
        if (now.getHour() < 12) {
            return "Good Morning!";
        } else if (now.getHour() < 18) {
            return "Good Afternoon!";
        } else {
            return "Good Evening!";
        }
    }

    public static void main(String[] args) {
        TimeProvider timeProvider = new SystemTimeProvider();
        TimeBasedGreeting timeBasedGreeting = new TimeBasedGreeting(timeProvider);
        System.out.println(timeBasedGreeting.getGreeting());
    }
}
