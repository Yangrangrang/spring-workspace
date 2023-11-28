package org.example;

import java.io.IOException;

// GET /calculate?operand1=11&opertor=*&operand2=33
public class Main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationServer(8081).start();
    }
}