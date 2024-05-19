package sample.tdd.unit.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        //int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;
        int addedMonths = addedMonthCalculator2(payData.getPayAmount()); // 메소드로 기능으로 구현하여 리펙토링

        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate2(payData, addedMonths);
        }

        return payData.getBillingDate().plusMonths(addedMonths);
    }

    private int addedMonthCalculator(int payAmount) {
        return payAmount >= 100_000 ? 12 : payAmount / 10_000;
    }

    private int addedMonthCalculator2(int payAmount) {
        int addedMonths = payAmount / 10_000;

        if (addedMonths < 10) return addedMonths;

        int resultMonths = 0;
        int year = addedMonths / 10;
        int month = addedMonths % 10;

        resultMonths = year * 12;
        resultMonths += month;

        return resultMonths;
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        // 처음 납부일에 대한 일수를 가져온다
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if(dayOfFirstBilling != candidateExp.getDayOfMonth()) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
            if(dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate2(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        if(isNotSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            // 처음 납부일에 대한 일수를 가져온다
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isNotSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() != date2.getDayOfMonth() ? true : false;
    }

    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }
}
