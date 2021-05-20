package com.nqminhuit.voucherproviderserver.services.impl.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.nqminhuit.voucherproviderserver.services.impl.constants.MessageResponse;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.junit.jupiter.api.Test;

public class VoucherGeneratorThreadTest {

    @Test
    public void generateResponseVoucherModelWithinThreeSecs() {
        var model = VoucherGeneratorThread.generateResponseVoucherModel(
            System.currentTimeMillis() - 2000L, "+840909123456", "https://www.my-site.com/api/callbackUrl");
        assertEquals("+840909123456", model.getPhoneNumber());
        assertEquals(MessageResponse.MSG_RESPONSE_CLIENT_SUCCESS, model.getMessage());
        assertEquals(VoucherResponseStatus.SUCCESS, model.getVoucherResponseStatus());
        assertNotNull(model.getCode());
        assertFalse(model.getCode().isBlank());
    }

    @Test
    public void generateResponseVoucherModelAfterThreeSecs() {
        var callbackUrl = "https://www.my-site.com/api/callbackUrl";
        var phoneNumber = "+840909123456";
        var model = VoucherGeneratorThread.generateResponseVoucherModel(
            System.currentTimeMillis() - 5000L, phoneNumber, callbackUrl);
        assertNull(model);
    }
}
