package sample.tdd.unit.chap10.quiz.jiyong;

import java.time.LocalTime;

public class Cafe {
    private LocalTime openingTime;
    private LocalTime closingTime;
    private CafeTimeProvider cafeTimeProvider;

    public Cafe(LocalTime openingTime, LocalTime closingTime, CafeTimeProvider cafeTimeProvider) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.cafeTimeProvider = cafeTimeProvider;
    }

    public String orderDrink() {
        LocalTime orderTime = cafeTimeProvider.getCurrentTime();
        if (orderTime.isAfter(openingTime) && orderTime.isBefore(closingTime)) {
            return "Order successful";
        } else {
            return "Cafe is closed";
        }
    }
}
