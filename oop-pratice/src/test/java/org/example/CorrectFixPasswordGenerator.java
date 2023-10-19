package org.example;

public class CorrectFixPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "abcdefgh";  // 8글자
    }
}
