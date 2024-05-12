package sample.tdd.unit.chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @DisplayName("모든 규칙을 충족하는 경우")
    @Test
    void meetsAllCriteria_Then_Strong() {
        // given, when
//        PasswordStrength result = meter.meter("ab12!@AB");
//        PasswordStrength result2 = meter.meter("ab12!@AB");

        // then
//        assertEquals(PasswordStrength.STRONG, result);
//        assertEquals(PasswordStrength.STRONG, result2);

        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!@Add", PasswordStrength.STRONG);
    }

    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 경우")
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        // given, when
//        PasswordStrength result = meter.meter("ab12!@A");

        // then
//        assertEquals(PasswordStrength.NORMAL, result);

        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우")
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        // given, when
//        PasswordStrength result = meter.meter("ab!@ABqwer");

        // then
//        assertEquals(PasswordStrength.NORMAL, result);

        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @DisplayName("null을 입력하는 경우")
    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하는 경우")
    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우")
    @Test
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @DisplayName("숫자 포함 조건만 충족하는 경우")
    @Test
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @DisplayName("대문자 포함 조건만 충족하는 경우")
    @Test
    void meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @DisplayName("아무 조건도 충족하지 않은 경우")
    @Test
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
