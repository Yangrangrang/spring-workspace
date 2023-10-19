package org.example;

public class WrongFixPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "ab";  // 2글자
    }
}
