package org.example.controller;

import org.example.annotation.Controller;
import org.example.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
}
