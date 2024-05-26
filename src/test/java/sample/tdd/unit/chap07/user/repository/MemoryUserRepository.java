package sample.tdd.unit.chap07.user.repository;

import sample.tdd.unit.chap07.user.domain.User;
import sample.tdd.unit.chap07.user.impl.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }
}
