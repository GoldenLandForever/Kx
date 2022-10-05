package com.example.backend.service.bot;

import java.util.List;

public interface BotService {
    public Integer nextStep(List<Integer> ownBoard, List<Integer> otherBoard, Integer figure);
}
