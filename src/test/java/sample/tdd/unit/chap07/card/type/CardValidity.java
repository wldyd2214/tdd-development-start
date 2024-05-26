package sample.tdd.unit.chap07.card.type;

public enum CardValidity {
    INVALID("유효하지 않는 카드"),
    VALID("유효한 카드"),
    EXPIRED("만료된 카드"),
    THEFT("도난된 카드"),
    UNKNOWN("알수없는 카드"),
    ERROR("에러");

    private String description;

    CardValidity(String description) {
        this.description = description;
    }
}
