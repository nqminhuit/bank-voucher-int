package com.voucher.provider.models;

import static org.junit.Assert.assertEquals;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.junit.Test;

public class ResponseVoucherModelTest {

    private static ResponseVoucherModel expected = new ResponseVoucherModel();
    static {
        expected.setCode("code");
        expected.setCodeVerifier("codeVerifier");
        expected.setMessage("message");
        expected.setPhoneNumber("+84909123456");
        expected.setTransformMethod("transformMethod");
        expected.setVoucherResponseStatus(VoucherResponseStatus.LATE_SUCCESS);
    };

    @Test
    public void responseVoucherModelBuilder() {
        // given:
        var expected = new ResponseVoucherModel();
        expected.setCode("code");
        expected.setCodeVerifier("codeVerifier");
        expected.setMessage("message");
        expected.setPhoneNumber("+84909123456");
        expected.setTransformMethod("transformMethod");
        expected.setVoucherResponseStatus(VoucherResponseStatus.LATE_SUCCESS);

        // when:
        var actual = ResponseVoucherModel.builder()
            .code("code")
            .codeVerifier("codeVerifier")
            .message("message")
            .phoneNumber("+84909123456")
            .transformMethod("transformMethod")
            .voucherResponseStatus(VoucherResponseStatus.LATE_SUCCESS)
            .build();

        // then:
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void responseVoucherModelToJson() {
        String expectedJson = "{\"code\": \"code\", \"codeVerifier\": \"codeVerifier\","
            + " \"message\": \"message\", \"phoneNumber\": \"+84909123456\", "
            + "\"transformMethod\": \"transformMethod\", \"voucherResponseStatus\": \"LATE_SUCCESS\"}";
        String actualJson = expected.toJson();
        assertEquals(expectedJson, actualJson);
    }
}
