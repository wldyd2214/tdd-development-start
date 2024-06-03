package sample.tdd.unit.chap10.quiz.jeehee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.tdd.unit.chap07.user.fucntion.UserRegister;
import sample.tdd.unit.chap07.user.impl.EmailNotifier;
import sample.tdd.unit.chap07.user.impl.UserRepository;
import sample.tdd.unit.chap07.user.impl.WeakPasswordChecker;

@ExtendWith(MockitoExtension.class)
public class ExTest {

    private UserRegister userRegister;

    @Mock
    private WeakPasswordChecker mockPasswordChecker;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailNotifier emailNotifier;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, userRepository, emailNotifier);
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {

        userRegister.register("id", "pw", "email");

        // 정확하게 일치하는 값으로 모의 객체 설정하지 않기
        // 암호 검사를 수행했는지 확인하는 테스트이기 때문에 어떤 문자열인지에는 상관이 없다.
        //BDDMockito.then(mockPasswordChecker).should().checkPasswordWeak("pwa");
        BDDMockito.then(mockPasswordChecker).should().checkPasswordWeak(Mockito.anyString());
    }

}
