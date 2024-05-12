package sample.tdd.unit.chap02;

import org.apache.logging.log4j.util.Strings;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
//        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;
        if(Strings.isEmpty(s)) return PasswordStrength.INVALID; // 리팩토링

        int metCounts = getMetCriteriaCounts(s);

        if(metCounts <= 1) return PasswordStrength.WEAK;
        if(metCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if(s.length() >= 8) metCounts++;
        if(meetsContainingNumberCriteria(s)) metCounts++;
        if(meetsContainingUppercaseCriteria(s)) metCounts++;
        return metCounts;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        // 넘겨오는 인자의 null 체크가 필요한지
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        // 넘겨오는 인자의 null 체크가 필요한지
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
