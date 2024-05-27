package sample.tdd.unit.chap07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import sample.tdd.unit.chap07.user.exception.WeakPasswordException;
import sample.tdd.unit.chap07.user.fucntion.UserRegister;
import sample.tdd.unit.chap07.user.impl.EmailNotifier;
import sample.tdd.unit.chap07.user.impl.WeakPasswordChecker;
import sample.tdd.unit.chap07.user.repository.MemoryUserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        BDDMockito
                // "pw" 인자를 사용해서 모의 객체의 checkPasswordWeak 메소드를 호출하면
                .given(mockPasswordChecker.checkPasswordWeak("pw"))
                // 결과로 true를 리턴하라
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        BDDMockito.then(mockPasswordChecker)
                  // 특정 메서드가 호출됐는지 검증
                  .should()
                  // 임이의 String 타입 인자를 이용해서 checkPasswordWeak() 메소드 호출 여부를 확인한다.
                  .checkPasswordWeak(BDDMockito.anyString());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        // 모의 객체를 메소드를 호출할 때 전달한 객체를 담는 기능을 제공
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                  .should()
                  .sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }
}
