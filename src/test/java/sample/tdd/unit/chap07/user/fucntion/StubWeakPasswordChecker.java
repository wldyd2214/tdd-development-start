package sample.tdd.unit.chap07.user.fucntion;

import sample.tdd.unit.chap07.user.impl.WeakPasswordChecker;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }
}
