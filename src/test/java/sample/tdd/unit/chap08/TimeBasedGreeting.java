package sample.tdd.unit.chap08;

import java.time.LocalDateTime;

public class TimeBasedGreeting {
    public String getGreeting() {
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() < 12) {
            return "Good Morning!";
        } else if (now.getHour() < 18) {
            return "Good Afternoon!";
        } else {
            return "Good Evening!";
        }
    }
}
