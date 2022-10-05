package com.example.backend.service.impl.bot;

import com.example.backend.service.bot.BotService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BotServiceImpl implements BotService {
    @Override
    public Integer nextStep(List<Integer> ownBoard, List<Integer> otherBoard, Integer figure) {
        int index = -1;
        int maxNum = -999;
        int emptyrow = 0;
        int emptyrowop = 0;

        for(int j = 0;j < 9; j ++){
            if(ownBoard.get(j) != 0) continue;
            Integer[] aMap = ownBoard.toArray(new Integer[0]);
            Integer[] bMap = otherBoard.toArray(new Integer[0]);

            aMap[j] = figure;
            for (int i = j / 3 * 3; i < j / 3 * 3 + 3; i++) {
                if (bMap[i] == figure) {
                    bMap[i]=0;
                }
            }
            int a_score = 0;
            int b_score = 0;

            for (int i = 0; i < 3; i++) {
                int a = aMap[i * 3];
                int b = aMap[i * 3 + 1];
                int c = aMap[i * 3 + 2];

                if (a == b && b == c) a_score += a * 9;
                else if (a == b) a_score += a * 4 + c;
                else if (a == c) a_score += a * 4 + b;
                else if (b == c) a_score += b * 4 + a;
                else a_score += (a + b + c);
            }
            for (int i = 0; i < 3; i++) {
                int a = bMap[i * 3];
                int b = bMap[i * 3 +1];
                int c = bMap[i * 3 + 2];

                if (a == b && b == c) b_score += a * 9;
                else if (a == b) b_score += a * 4 + c;
                else if (a == c) b_score += a * 4 + b;
                else if (b == c) b_score += b * 4 + a;
                else b_score += (a + b + c);
            }
            int temp = 0;
            temp = a_score - b_score;
            int ownempty = 0;
            int opempty = 0;
            for (int i = j / 3 * 3; i < j / 3 * 3 + 3; i ++ ){
                if (aMap[i] == 0) ownempty ++;
                if (bMap[i] == 0) opempty ++;
            }
            if (temp > maxNum){
                System.out.println(temp);
                System.out.println(maxNum);
                index = j;
                maxNum = temp;
                emptyrow = ownempty;
                emptyrowop = opempty;
            }else if(temp == maxNum){
                if(ownempty > emptyrow){

                    index = j;
                    maxNum = temp;
                    emptyrow = ownempty;
                    emptyrowop = opempty;
                }else if (ownempty == emptyrow){
                    if (opempty < emptyrowop){
                        if (figure > 3){
                            index = j;
                            maxNum = temp;
                            emptyrow = ownempty;
                            emptyrowop = opempty;
                        }
                    }else if (opempty > emptyrowop){
                        if (figure < 3){
                            index = j;
                            maxNum = temp;
                            emptyrow = ownempty;
                            emptyrowop = opempty;
                        }
                    }
                }
            }
        }

        return index;
    }
}
