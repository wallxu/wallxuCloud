package com.wallxu.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/19.
 */
public class JwtToken {
    private final static String base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    private final static int expiresSecond = 172800000;

    //生成token
    public static String createToken() {
        try {
            //签发时间
            Date iatDate = new Date();

            //过期时间 1天过期
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.DATE, 1);
            Date expiresDate = nowTime.getTime();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("alg","HS256");
            map.put("typ","JWT");

            //加密算法
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withHeader(map)  //header
                    .withIssuer("auth0")
                    .withExpiresAt(expiresDate)  //1天过期
                    .withIssuedAt(iatDate)    //签发时间
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
        }
        return null;
    }

    //校验token
    public static boolean verifyToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            exception.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(JwtToken.createToken());
        System.out.println(JwtToken.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs"));
    }

}
