package org.example.mvc.repository;

import org.example.mvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private static Map<String, User> users = new HashMap<>();

    public static void save(User user) {
        users.put(user.getUserId(), user);
    }

    public static Collection<User> findAll() {
        log.info("UserRepositoryFindAll");
        return users.values();
    }
}
