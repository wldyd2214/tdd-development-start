package sample.tdd.unit.chap07.card.domain;

import java.time.LocalDateTime;

public class AutoDebitInfo {
    private String userId;
    private String cardNumber;
    private LocalDateTime regDate;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime regDate) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.regDate = regDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
