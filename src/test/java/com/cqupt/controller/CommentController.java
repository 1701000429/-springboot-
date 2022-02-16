package com.cqupt.controller;

import com.cqupt.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Test
    void selectTest() {
        commentMapper.getByPaperId((long)21);
    }
}
