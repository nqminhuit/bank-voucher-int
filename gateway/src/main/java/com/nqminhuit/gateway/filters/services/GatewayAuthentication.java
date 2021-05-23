package com.nqminhuit.gateway.filters.services;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class GatewayAuthentication {

    private static final String ISSUER = "bank-int-gateway";

    // FIXME: find a way to inject secret to the class AuthFilter
    private static String jwtSecret = "f049586d-6d8d-45c5-92e5-0bd5ce32854b";

    public static boolean verify(String jwt, String phoneNumber) {
        try {
            JWT.require(Algorithm.HMAC256(jwtSecret))
                .withIssuer(ISSUER)
                .withClaim("phoneNumber", phoneNumber)
                .build()
                .verify(jwt);
            return true;
        }
        catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * TODO:
     * when user generate a new token while the old one is still valid,
     * find a way to deprecate the old jwt
     */
    public static String generateJwt(String username, String phoneNumber) {
        return JWT.create()
            .withIssuer(ISSUER)
            .withAudience(username)
            .withClaim("phoneNumber", phoneNumber)
            .withExpiresAt(minsFromNow(10))
            .sign(Algorithm.HMAC256(jwtSecret));
    }

    private static Date minsFromNow(int mins) {
        return Date.from(ZonedDateTime.now()
            .plus(mins, ChronoUnit.MINUTES)
            .truncatedTo(ChronoUnit.SECONDS)
            .toInstant());
    }

}
