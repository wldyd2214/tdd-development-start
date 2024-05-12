package sample.tdd.unit.chap02;

public enum PasswordStrength {
    STRONG("강함"), NORMAL("보통"), WEAK("약함"), INVALID("유효하지 않은");

    private String description;

    PasswordStrength(String description) {
        this.description = description;
    }
}
