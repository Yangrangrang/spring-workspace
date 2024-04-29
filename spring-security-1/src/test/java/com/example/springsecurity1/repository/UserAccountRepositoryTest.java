package com.example.springsecurity1.repository;

import com.example.springsecurity1.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DisplayName("[JPA] 연결테스트")
@DataJpaTest
public class UserAccountRepositoryTest {

    private final UserAccountRepository userAccountRepository;

    public UserAccountRepositoryTest(
            @Autowired UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Test
    void insertSelectTest() {
        long cnt = userAccountRepository.count();
        System.out.println("cnt = " + cnt);
        UserAccount user = userAccountRepository.save(UserAccount.of(
                "test",
                "123",
                "tt",
                "test@mail.com",
                "010-1111-1111",
                "N",
                LocalDateTime.now()
        ));
        System.out.println("userAccountRepository = " + userAccountRepository.count());

        UserAccount user1 = userAccountRepository.getReferenceById(1L);
        System.out.println("user1.getId().equals(user.getId()) = " + user1.getId().equals(user.getId()));
    }
}
