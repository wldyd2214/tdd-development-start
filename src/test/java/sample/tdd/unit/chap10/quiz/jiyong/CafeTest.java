package sample.tdd.unit.chap10.quiz.jiyong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.tdd.unit.chap10.quiz.jiyong.impl.CafeFixedTimeProvider;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CafeTest {

    private Cafe cafe;

    private void setCafeTimeProvider(LocalTime openingTime, LocalTime closingTime, LocalTime orderTime) {
        CafeTimeProvider cafeTimeProvider = new CafeFixedTimeProvider(orderTime);
        cafe = new Cafe(openingTime, closingTime, cafeTimeProvider);
    }

    @DisplayName("영업시간 중 음료 주문 테스트")
    @Test
    public void testOrderDrinkDuringOpenHours() {
        setCafeTimeProvider(LocalTime.of(7, 0), LocalTime.of(21, 0), LocalTime.of(20, 0));
        assertExpected("Order successful", cafe.orderDrink());
    }

    @DisplayName("영업시간이 아닌 시간 중 음료 주문 테스트")
    @Test
    public void testOrderDrinkOutsideOpenHours() {
        setCafeTimeProvider(LocalTime.of(7, 0), LocalTime.of(21, 0), LocalTime.of(21, 0));
        assertExpected("Cafe is closed", cafe.orderDrink());
    }

    private void assertExpected(String expected, String result) {
        assertEquals(expected, result);
    }
}
