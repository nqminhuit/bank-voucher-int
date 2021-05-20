package com.nqminhuit.voucherproviderserver.services.impl.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.nqminhuit.voucherproviderserver.services.impl.constants.MessageResponse;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.junit.jupiter.api.Test;

public class MessageResponseUtilsTest {

    @Test
    public void chooseSuccessMessage() {
        var actual = MessageResponseUtils.chooseMessage(20_000L);
        assertEquals(MessageResponse.MSG_RESPONSE_CLIENT_SUCCESS, actual);
    }

    @Test
    public void chooseLateSuccessMessage() {
        var actual = MessageResponseUtils.chooseMessage(31_000L);
        assertEquals(MessageResponse.MSG_RESPONSE_CLIENT_LATE_SUCCESS, actual);
    }

    @Test
    public void chooseSuccessStatus() {
        var actual = MessageResponseUtils.chooseStatus(10_000L);
        assertEquals(VoucherResponseStatus.SUCCESS, actual);
    }

    @Test
    public void chooseLateSuccessStatus() {
        var actual = MessageResponseUtils.chooseStatus(40_000L);
        assertEquals(VoucherResponseStatus.LATE_SUCCESS, actual);
    }
}
