package com.example.demo.common.shiro_jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.enity.UserEnity;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT相关工具类
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/8
 */
@Slf4j
public class JWTUtils {

    /*** 签发人名称*/
    private static String issuer = "service";

    /**
     * 生成token
     */
    public static String createUserToken(UserEnity userEnity, String secret) {
        Calendar calendar = Calendar.getInstance();
        // 当前时间
        Date now = (Date) calendar.getTime().clone();
        // 过期时间
        calendar.add(Calendar.SECOND, 10);
        Date expireTime = calendar.getTime();

        //构建token头部信息
        Map<String, Object> head = new HashMap<>(2);
        //构建秘钥信息
        Algorithm algorithm = Algorithm.HMAC256(secret);
        head.put("alg", "HS256");
        head.put("typ", "JWT");

        JWTCreator.Builder jwtBuild = JWT.create();
        jwtBuild.withHeader(head);

        //自定义载荷中的字段
        jwtBuild.withClaim("userName", userEnity.getUserName());
        //签发人
        jwtBuild.withIssuer(issuer);
        //token主题
        jwtBuild.withSubject("this is a userToken");
        //签发观众
        jwtBuild.withAudience(userEnity.getUserName());
        //生成签名时间
        jwtBuild.withIssuedAt(now);
        //签名生效时间
        jwtBuild.withNotBefore(now);
        //签名过期时间
        jwtBuild.withExpiresAt(expireTime);

        // 设置签名并将生成的token返回
        return jwtBuild.sign(algorithm);
    }

    /**
     * 校验token
     */
    public static boolean verifyUserToken(String userToken, String secret) {
        //加密算法和secret
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //JWT验证器
        JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
        //校验token  检验失败抛出异常
        jwtVerifier.verify(userToken);

        return true;
    }

    /**
     * 拿到token中用户id
     * 不要秘钥解析也能拿到定义的信息
     */
    public static String getUserName(String token) {

        return JWT.decode(token).getClaim("userName").asString();
    }
}