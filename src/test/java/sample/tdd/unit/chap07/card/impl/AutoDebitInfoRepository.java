package sample.tdd.unit.chap07.card.impl;

import sample.tdd.unit.chap07.card.domain.AutoDebitInfo;

public interface AutoDebitInfoRepository
{
    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}
