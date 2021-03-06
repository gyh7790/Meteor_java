package com.gyh.config.security;

import com.gyh.system.sys.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gyh
 * @Date 2020/6/14 21:05
 */
public class JwtUtils {

    public static final String TOKEN_PREFIX = "Meteor_";

    //密钥，用于signature（签名）部分解密
    private static final String PRIMARY_KEY = "jwtsecretdemo";
    //签发者
    private static final String ISS = "Gent.Ni";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    // 添加角色的key
    public static final String ROLE_CLAIMS = "role";
    public static final String USER_NAME = "userName";
    public static final String USER_PHONE = "userPhone";

    private static final String USER = "user";


    public static String generateToken(String userId, String username, Map<String, Object> map, boolean isRememberMe){
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder()
                //采用HS512算法对JWT进行的签名,PRIMARY_KEY是我们的密钥
                .signWith(SignatureAlgorithm.HS512, PRIMARY_KEY)
                //设置角色名
                .setClaims(map)
                //设置发证人
                .setIssuer(ISS)
                .setSubject(username)
                .setId(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * description: 从token中获取用户名
     * @param token
     * @return java.lang.String
     */
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }


    /**
     * 获取用户名称
     * @param token
     * @return
     */
    public static String getName(String token){
        return (String) getTokenBody(token).get(USER_NAME);
    }

    /**
     * 获取用户手机号
     * @param token
     * @return
     */
    public static String getPhone(String token){
        return (String) getTokenBody(token).get(USER_PHONE);
    }

    // 获取用户角色
    public static List<String> getUserRole(String token){
        return (List<String>) getTokenBody(token).get(ROLE_CLAIMS);
    }

    // 获取用户角色
    public static String getUserId(String token){
        return getTokenBody(token).getId();
    }

    /**
     * description: 判断Token是否过期
     * @param token
     * @return boolean
     */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * description:　获取
     * @param token
     * @return io.jsonwebtoken.Claims
     */
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(PRIMARY_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
