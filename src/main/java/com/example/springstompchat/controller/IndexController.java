package com.example.springstompchat.controller;

import com.example.springstompchat.config.RoomUserManager2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class IndexController {
    @GetMapping("")
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/room/{roomId}")
    public Integer roomUsers(@PathVariable String roomId) {
        Integer roomUsers = RoomUserManager2.getRoomUsers(roomId);
        return roomUsers;
    }
}
