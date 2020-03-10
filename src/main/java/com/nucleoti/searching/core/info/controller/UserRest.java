package com.nucleoti.searching.core.info.controller;

import com.nucleoti.searching.core.info.Service.UserServiceImpl;
import com.nucleoti.searching.core.info.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable(name = "id") Integer id) {
        try {
            logger.info("=== (PUT) /user/login START ==={}", userService.findUserById(id));
            return userService.findUserById(id);
        } finally {
            logger.info("=== (PUT) /user/login END ===");
        }
    }
}
