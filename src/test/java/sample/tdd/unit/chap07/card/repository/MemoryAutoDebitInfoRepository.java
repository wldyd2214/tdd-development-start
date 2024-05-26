package sample.tdd.unit.chap07.card.repository;

import sample.tdd.unit.chap07.card.domain.AutoDebitInfo;
import sample.tdd.unit.chap07.card.impl.AutoDebitInfoRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}
