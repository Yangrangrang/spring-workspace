package org.example;

@FunctionalInterface    // 작성해주면 UserTest.java에 있는 user.initPassword(()-> "aabbccdde"); 람다식으로 작성해서 주입 가능
public interface PasswordGenerator {

    String generatePassword();
}
