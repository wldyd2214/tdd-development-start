package sample.tdd.unit.chap07.card.function;

import sample.tdd.unit.chap07.card.domain.AutoDebitInfo;
import sample.tdd.unit.chap07.card.domain.AutoDebitReq;
import sample.tdd.unit.chap07.card.domain.RegisterResult;
import sample.tdd.unit.chap07.card.impl.AutoDebitInfoRepository;
import sample.tdd.unit.chap07.card.type.CardValidity;

import java.time.LocalDateTime;

public class AutoDebitRegister {
    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());

        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }
        AutoDebitInfo info = repository.findOne(req.getUserId());
        if(info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            AutoDebitInfo newInfo =
                    new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDateTime.now());
            repository.save(newInfo);
        }
        return RegisterResult.success();
    }
}
