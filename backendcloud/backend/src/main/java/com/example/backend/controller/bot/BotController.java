package com.example.backend.controller.bot;

import com.example.backend.service.bot.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BotController {
    @Autowired
    private BotService botService;

    @PostMapping("/pk/bot/")
    public Integer nextStep(@RequestParam  List<Integer> ownBoard, List<Integer> otherBoard, Integer figure){
        return botService.nextStep(ownBoard,otherBoard,figure);
    }
}
