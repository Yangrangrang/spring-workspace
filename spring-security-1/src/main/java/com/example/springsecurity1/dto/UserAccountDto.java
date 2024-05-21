package com.example.springsecurity1.dto;

import com.example.springsecurity1.domain.UserAccount;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

// record클래스는 불변 데이터를 객체 간에 전달하는 작업을 간단하게 만들어준다.
// 특징 : 멤버변수는 private final로 선언, 필드별 getter 자동 생성, 모든 멤버변수를 인자로 하는 public 생성자를 자동 생성
//       equals, hashcode, toString을 자동 생성, 기본생성자는 제공하지 않아 필요한 경우 직접 생성
public record UserAccountDto(
        Long id,
        String userId,
        String userName,
        String userPassword,
        String nickName,
        String email,
        String phone,
        String isDelete,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public UserAccountDto of(Long id, String userName, String userId, String userPassword, String nickName, String email, String phone, String isDelete, LocalDateTime lastLogin, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(id, userId, userName, userPassword, nickName, email, phone, isDelete, lastLogin, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    // DB에서 가져온 entity를 dto로 전환
    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserName(),
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getNickName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getIsDelete(),
                entity.getLastLogin(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    // DTO를 entity로 전환
    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userName,
                userPassword,
                nickName,
                email,
                phone,
                isDelete,
                lastLogin
        );
    }

    public UserAccount toEntity(PasswordEncoder passwordEncoder) {
        String encryptedPassword = passwordEncoder.encode(userPassword);
        return UserAccount.of(
                userId,
                userName,
                encryptedPassword,
                nickName,
                email,
                phone,
                isDelete,
                lastLogin
        );
    }
}
