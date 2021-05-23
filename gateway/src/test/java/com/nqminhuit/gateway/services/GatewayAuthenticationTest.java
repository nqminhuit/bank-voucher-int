package com.nqminhuit.gateway.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nqminhuit.gateway.filters.services.GatewayAuthentication;
import org.junit.jupiter.api.Test;

public class GatewayAuthenticationTest {

    private static final String USER_TEST = "batman";

    private static final String PHONE_NUMBER_TEST = "0908123654";

    @Test
    public void verify() {
        String jwt = JWT.create()
            .withIssuer("bank-int-gateway")
            .withAudience(USER_TEST)
            .withClaim("phoneNumber", PHONE_NUMBER_TEST)
            .withExpiresAt(Date.from(
                ZonedDateTime.now().plus(10, ChronoUnit.MINUTES).truncatedTo(ChronoUnit.SECONDS).toInstant()))
            .sign(Algorithm.HMAC256("f049586d-6d8d-45c5-92e5-0bd5ce32854b"));

        boolean actual = GatewayAuthentication.verify(jwt, PHONE_NUMBER_TEST);

        assertTrue(actual);
    }

    @Test
    public void generateJwt() {
        var expectedJwt = JWT.create()
            .withIssuer("bank-int-gateway")
            .withAudience(USER_TEST)
            .withClaim("phoneNumber", PHONE_NUMBER_TEST)
            .withExpiresAt(Date.from(
                ZonedDateTime.now().plus(10, ChronoUnit.MINUTES).truncatedTo(ChronoUnit.SECONDS).toInstant()))
            .sign(Algorithm.HMAC256("f049586d-6d8d-45c5-92e5-0bd5ce32854b"));

        var jwt = GatewayAuthentication.generateJwt(USER_TEST, PHONE_NUMBER_TEST);

        assertEquals(expectedJwt, jwt);
    }

}
