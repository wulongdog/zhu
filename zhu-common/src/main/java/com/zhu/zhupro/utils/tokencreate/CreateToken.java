package com.zhu.zhupro.utils.tokencreate;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import net.minidev.json.parser.ParseException;

import java.util.Date;
import java.util.Objects;

public class CreateToken {

    /**
     * 创建秘钥
     */
    private static final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();

    /**
     * 过期时间5秒
     */
    private static final long EXPIRE_TIME = 1000 * 5;

        /**
         * 生成Token
         * @param email
         * @return
         */
        public static String buildJWT(String email) {
            try {
                /**
                 * 1.创建一个32-byte的密匙
                 */
                MACSigner macSigner = new MACSigner(SECRET);
                /**
                 * 2. 建立payload 载体
                 */
                JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                        .subject("lhy")
                        .issuer("http://lhyzal.com")
                        .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                        .claim("email",email)
                        .build();

                /**
                 * 3. 建立签名
                 */
                SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
                signedJWT.sign(macSigner);

                /**
                 * 4. 生成token
                 */
                String token = signedJWT.serialize();
                return token;
            } catch (KeyLengthException e) {
                e.printStackTrace();
            } catch (JOSEException e) {
                e.printStackTrace();
            }
            return null;
        }


    /**
     * 校验token
     * @param token
     * @return
     */
    public static boolean vaildToken(String token ) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);
            //校验是否有效
            if (!jwt.verify(verifier)) {
                return false;
            }

            //校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                return false;
            }

            //获取载体中的数据
            Object account = jwt.getJWTClaimsSet().getClaim("email");
            //是否有openUid
            if (Objects.isNull(account)){
                return false;
            }
        } catch (JOSEException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

}
