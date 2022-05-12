package com.ad.core.system.auth.jwt;

import cn.hutool.core.codec.Base64;
import com.ad.common.utils.DateEx;
import com.ad.core.system.common.Constant;
import com.ad.core.system.common.CustomException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author CoderYoung
 */
public class JwtUtil {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static final String ENCRYPT_JWT_KEY = "ADEncryptJWTKey";

    /**
     * 校验token是否正确
     *
     * @param token Token
     * @return boolean 是否正确
     */
    public static int verify(String token) {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, Constant.ACCOUNT) + Base64.encode(ENCRYPT_JWT_KEY);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return 1;
        } catch (TokenExpiredException e) {
            return 2;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token
     * @param claim
     * @return java.lang.String
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            logger.error("解密Token中的公共信息出现JWTDecodeException异常:{}", e.getMessage());
            throw new CustomException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

    /**
     * 生成签名
     *
     * @param account 帐号
     * @return java.lang.String 返回加密的Token
     */
    public static String sign(String account) {
        return sign(account, 1);
    }

    public static String sign(String account, int days) {
        // 帐号加JWT私钥加密
        String secret = account + Base64.encode(ENCRYPT_JWT_KEY);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // Token有效期 1天
        Date expireDate = new DateEx().addDay(days).getDate();
        // 附带account帐号信息
        return JWT.create()
                .withClaim("account", account)
                .withClaim("currentTimeMillis", System.currentTimeMillis())
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }
}
