package com.nqminhuit.voucherproviderserver.controllers.models.builders;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import com.nqminhuit.voucherproviderserver.controllers.models.ResponseVoucherModel;
import com.nqminhuit.voucherproviderserver.services.enumerations.VoucherResponseStatus;
import com.nqminhuit.voucherproviderserver.services.impl.constants.MessageResponse;

public class ResponseVoucherModelBuilder {

    private String message;

    private String code;

    private String phoneNumber;

    private String codeVerifier;

    private String transformMethod;

    private VoucherResponseStatus voucherResponseStatus;

    public ResponseVoucherModelBuilder() {}

    public ResponseVoucherModelBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ResponseVoucherModelBuilder code(String code) {
        this.code = code;
        return this;
    }

    public ResponseVoucherModelBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ResponseVoucherModelBuilder codeVerifier(String codeVerifier) {
        this.codeVerifier = codeVerifier;
        return this;
    }

    public ResponseVoucherModelBuilder transformMethod(String transformMethod) {
        this.transformMethod = transformMethod;
        return this;
    }

    public ResponseVoucherModelBuilder voucherResponseStatus(VoucherResponseStatus status) {
        this.voucherResponseStatus = status;
        return this;
    }

    public ResponseVoucherModel build() {
        ResponseVoucherModel res = new ResponseVoucherModel();
        res.setCode(code);
        res.setCodeVerifier(codeVerifier);
        res.setMessage(message);
        res.setPhoneNumber(phoneNumber);
        res.setTransformMethod(transformMethod);
        res.setVoucherResponseStatus(voucherResponseStatus);
        return res;
    }

    public ResponseVoucherModel buildResponseTimeout() {
        return ResponseVoucherModel.builder()
            .message(MessageResponse.MSG_RESPONSE_CLIENT_WAIT)
            .voucherResponseStatus(VoucherResponseStatus.PENDING)
            .build();
    }

    public ResponseVoucherModel buildResponseFromFuture(Future<ResponseVoucherModel> futureResponse) {
        try {
            return futureResponse.get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseVoucherModel.builder()
                .message("something bad happend: " + e.getMessage())
                .voucherResponseStatus(VoucherResponseStatus.ERROR)
                .build();
        }
    }
}
