package com.nqminhuit.voucherproviderserver.services.impl.utils;

import com.nqminhuit.voucherproviderserver.services.enumerations.VoucherResponseStatus;
import com.nqminhuit.voucherproviderserver.services.impl.constants.ClientTimer;
import com.nqminhuit.voucherproviderserver.services.impl.constants.MessageResponse;

public class MessageResponseUtils {

    public static String chooseMessage(long elapsedTimeMillis) {
        return elapsedTimeMillis <= ClientTimer.MAX_WEB_RESPONSE_MILLIS
            ? MessageResponse.MSG_RESPONSE_CLIENT_SUCCESS
            : MessageResponse.MSG_RESPONSE_CLIENT_LATE_SUCCESS;
    }

    public static VoucherResponseStatus chooseStatus(long elapsedTimeMillis) {
        return elapsedTimeMillis <= ClientTimer.MAX_WEB_RESPONSE_MILLIS
            ? VoucherResponseStatus.SUCCESS
            : VoucherResponseStatus.LATE_SUCCESS;
    }

}
