package sample.tdd.unit.chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 만료일 계산기
public class ExpiryDateCalculatorTest {

    // 만료일 계산기에서는 만원을 납부하면 한 달 뒤 같은 날을 만료일로 계산하는 것이 가장 쉬울 것 같다.
    // 2019년 3월 1일에 만원 납부시 만료일은 2019월 4월 1일
    @DisplayName("만원 납부하면 한달 뒤가 만료일이 된다.")
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
//        // given
//        LocalDate billingDate = LocalDate.of(2019, 3, 1);
//        LocalDate billingDate2 = LocalDate.of(2019,5, 5);
//
//        int payAmount = 10_000;
//        int payAmount2 = 10_000;
//
//        ExpiryDateCalculator cal = new ExpiryDateCalculator();
//        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
//
//        // when
//        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2);
//
//        // then
//        assertEquals(LocalDate.of(2019, 4, 1), expiryDate);
//        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2);

        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 3, 1))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 4, 1));
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 5, 5))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 6, 5));
    }

    @DisplayName("납부일과 한달 뒤 일자가 같지 않음")
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 1, 31))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 2, 28));
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 5, 31))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 6, 30));
        assertExpiryDate(
                PayData.builder()
                      .billingDate(LocalDate.of(2020, 1, 31))
                      .payAmount(10_000)
                      .build(),
                LocalDate.of(2020, 2, 29));
    }

    @DisplayName("첫 납부일과 만료일 일자가 다를때 만원 납부")
    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        assertExpiryDate(
                PayData.builder()
                       .firstBillingDate(LocalDate.of(2019, 1, 31))
                       .billingDate(LocalDate.of(2019, 2, 28))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 3, 31));
        assertExpiryDate(
                PayData.builder()
                       .firstBillingDate(LocalDate.of(2019, 1, 30))
                       .billingDate(LocalDate.of(2019, 2, 28))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 3, 30));
        assertExpiryDate(
                PayData.builder()
                       .firstBillingDate(LocalDate.of(2019, 5, 31))
                       .billingDate(LocalDate.of(2019, 6, 30))
                       .payAmount(10_000)
                       .build(),
                LocalDate.of(2019, 7, 31));
    }

    @DisplayName("이만원 이상 납부하면 비례해서 만료일 계산")
    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 3, 1))
                       .payAmount(20_000)
                       .build(),
                LocalDate.of(2019, 5, 1)
        );
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 3, 1))
                       .payAmount(30_000)
                       .build(),
                LocalDate.of(2019, 6, 1)
        );
    }

    @DisplayName("첫 납부일과 만료일 일자가 다를 때 2만원 이상 납부 사례 추가")
    @Test
    void 첫_납부일과_만료일_일자가_다를_때_2만원_이상_납부_사례_추가() {
        assertExpiryDate(
                PayData.builder()
                       .firstBillingDate(LocalDate.of(2019, 1, 31))
                       .billingDate(LocalDate.of(2019, 2, 28))
                       .payAmount(20_000)
                       .build(),
                LocalDate.of(2019, 4, 30)
        );
    }

    @DisplayName("십만원을 납부하면 1년 제공")
    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
                PayData.builder()
                       .billingDate(LocalDate.of(2019, 1, 28))
                       .payAmount(100_000)
                       .build(),
                LocalDate.of(2020, 1, 28)
        );
    }

    // 추가 테스트 케이스 작성 필요
    // 2020, 2, 29 윤달 마지막 날에 10만원을 납부하는 상황
    // 13만원을 납부하는 상황 1년 3개월 뒤에 만료일이 되어야하는 경우

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
