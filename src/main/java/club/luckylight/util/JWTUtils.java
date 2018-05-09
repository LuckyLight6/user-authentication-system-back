package club.luckylight.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static final String SECRET = "LuckyLight";

    /**
     * 使用 JWT 生成 token
     *
     * @return 生成的 token
     * @throws UnsupportedEncodingException 抛出异常
     */
    public static String createToken(String data) throws UnsupportedEncodingException {
        // 签发时间
        Date createDate = new Date();

        // 过期时间 - 30 分钟过期
        Calendar nowTiem = Calendar.getInstance();
        nowTiem.add(Calendar.MINUTE, 30);
        Date expireDate = nowTiem.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map) // header
                .withClaim("projectName", "access-control-management-system-back") // payload
                .withClaim("author", "LuckyLight") // payload
                .withClaim("data", data) // payload
                .withExpiresAt(expireDate) // 设置过期时间
                .withIssuedAt(createDate) // 设置签发时间
                .sign(Algorithm.HMAC256(SECRET)); // 加密

        return token;
    }

    /**
     * 校验 token
     *
     * @param token token
     * @throws UnsupportedEncodingException 抛出异常
     */
    public static String verifyToken(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证已过期，请重新登录");
        }
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("data").asString();
    }
}
