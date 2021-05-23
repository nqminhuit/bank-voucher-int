package com.nqminhuit.voucherintservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import com.nqminhuit.voucherintservice.messages.KafkaReceiveCodeProducer;
import com.voucher.provider.models.ResponseVoucherModel;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class VoucherCodeResponseControllerTest {

    private VoucherCodeResponseController controller = new VoucherCodeResponseController();

    private static ResponseVoucherModel body;

    static {
        body = ResponseVoucherModel.builder()
            .voucherResponseStatus(VoucherResponseStatus.SUCCESS)
            .transformMethod("transformMethod")
            .codeVerifier("codeVerifier")
            .code("codeCode")
            .message("message")
            .phoneNumber("+84123987654")
            .build();
    }

    @Test
    public void handleResponseCallback() {
        controller.setKafkaReceiveCodeProducer(mock(KafkaReceiveCodeProducer.class));
        controller.handleResponseCallback(body);
    }

    @Test
    public void handleResponseCallback_shouldProduceCorrectTopic() {
        var kafkaProducerSpy = spy(KafkaReceiveCodeProducer.class);
        doNothing().when(kafkaProducerSpy).send(eq(KafkaTopicConstants.RECEIVE_CODE),
            any(ReceiveCodeMsg.class));
        controller.setKafkaReceiveCodeProducer(kafkaProducerSpy);

        controller.handleResponseCallback(body);

        var msgCaptor = ArgumentCaptor.forClass(ReceiveCodeMsg.class);
        verify(kafkaProducerSpy).send(eq(KafkaTopicConstants.RECEIVE_CODE), msgCaptor.capture());
        var msg = msgCaptor.getValue();
        assertEquals("codeCode", msg.getVoucherCode());
        assertEquals("+84123987654", msg.getPhoneNumber());
        assertEquals("SUCCESS", msg.getStatus());
    }

}
