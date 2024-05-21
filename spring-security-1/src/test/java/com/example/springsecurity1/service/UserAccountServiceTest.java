package com.example.springsecurity1.service;

import com.example.springsecurity1.domain.UserAccount;
import com.example.springsecurity1.dto.UserAccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UserAccountServiceTest {

    @Autowired
    private UserAccountService service;

    @Test
    void savedUserTest() {
        UserAccount user = UserAccount.of(
                "test",
                "username",
                "123",
                "tt",
                "test@mail.com",
                "010-1111-1111",
                "N",
                LocalDateTime.now()
        );
        UserAccountDto userDto = service.savedUser(UserAccountDto.from(user));
        System.out.println("userDto.id() = " + userDto.id());
    }

    @Test
    void getUserTest() {
        UserAccount user = UserAccount.of(
                "test",
                "username",
                "123",
                "tt",
                "test@mail.com",
                "010-1111-1111",
                "N",
                LocalDateTime.now()
        );
        UserAccountDto userDto = service.savedUser(UserAccountDto.from(user));

        UserAccountDto getUserDto = service.getUser(userDto.id());
        System.out.println("getUserDto.userId() = " + getUserDto.userId());
    }
}