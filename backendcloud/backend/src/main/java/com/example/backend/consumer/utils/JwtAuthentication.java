package com.example.backend.consumer.utils;

import com.example.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;

public class JwtAuthentication {
    public static Integer getUserId(String token) {
        int userId = -1;
        try {
            //获取jwt
            Claims claims = JwtUtil.parseJWT(token);
            //获取userId
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return userId;
    }
}