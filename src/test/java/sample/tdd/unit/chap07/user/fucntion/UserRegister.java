package sample.tdd.unit.chap07.user.fucntion;

import sample.tdd.unit.chap07.user.domain.User;
import sample.tdd.unit.chap07.user.exception.DupIdException;
import sample.tdd.unit.chap07.user.exception.WeakPasswordException;
import sample.tdd.unit.chap07.user.impl.EmailNotifier;
import sample.tdd.unit.chap07.user.impl.UserRepository;
import sample.tdd.unit.chap07.user.impl.WeakPasswordChecker;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository, EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User(id, pw, email));

        emailNotifier.sendRegisterEmail(email);
    }
}
