package sample.tdd.unit.chap07.user.impl;

import sample.tdd.unit.chap07.user.domain.User;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
