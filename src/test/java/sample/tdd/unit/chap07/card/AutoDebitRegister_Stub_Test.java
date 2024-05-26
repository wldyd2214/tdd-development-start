package sample.tdd.unit.chap07.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.tdd.unit.chap07.card.domain.AutoDebitInfo;
import sample.tdd.unit.chap07.card.domain.AutoDebitReq;
import sample.tdd.unit.chap07.card.domain.RegisterResult;
import sample.tdd.unit.chap07.card.function.AutoDebitRegister;
import sample.tdd.unit.chap07.card.function.StubCardNumberValidator;
import sample.tdd.unit.chap07.card.repository.MemoryAutoDebitInfoRepository;
import sample.tdd.unit.chap07.card.type.CardValidity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository; // private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository(); // stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    void invalidCard() {
        cardNumberValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = register.register(req);

        assertEquals(CardValidity.INVALID, result.getValidity());
    }

    @Test
    void theftCard() {
        cardNumberValidator.setTheftNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = register.register(req);

        assertEquals(CardValidity.THEFT, result.getValidity());
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    @Test
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234123412341234", saved.getCardNumber());
    }
}