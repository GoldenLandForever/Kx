package com.example.backend.controller.bot;

import com.example.backend.service.bot.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BotController {
    @Autowired
    private BotService botService;

    @PostMapping("/pk/bot/")
    public Integer nextStep(@RequestParam int[] aMap ,@RequestParam int[] bMap,@RequestParam int point){

        List<Integer> ownBoard = new ArrayList<>();
        List<Integer> otherBoard = new ArrayList<>();
        Integer figure = point;
        System.out.println("here");
        System.out.println(point);
        for (int i = 0; i < 9; i++) {
            Integer a = aMap[i];
            Integer b = bMap[i];
            ownBoard.add(i,b);
            otherBoard.add(i,a);
            System.out.println(ownBoard);
            System.out.println(otherBoard);
        }
        System.out.println("here");
        return botService.nextStep(ownBoard,otherBoard,figure);
    }
}
