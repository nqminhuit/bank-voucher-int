package com.nqminhuit.gateway.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import com.nqminhuit.gateway.messages.KafkaRequestCodeProducer;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import redis.clients.jedis.Jedis;

public class VoucherControllerTest {

    private VoucherController controller;

    private KafkaRequestCodeProducer kafkaProducer = spy(KafkaRequestCodeProducer.class);

    private Jedis jedis = spy(Jedis.class);

    @BeforeEach
    public void setup() {
        controller = new VoucherController();
        doNothing().when(kafkaProducer).send(anyString(), anyString());
        doReturn("").when(jedis).setex(anyString(), anyLong(), anyString());
        controller.setKafkaProducer(kafkaProducer);
        controller.setJedis(jedis);
        controller.setRedisRecordTtl(180L);
    }

    @Test
    public void requestVoucher_shouldStoreCallbackUrlByPhoneNumberToRedis() {
        String phoneNumber = "0833123456";
        String callbackUrl = "https://www.abc.com/callbackUrl";
        controller.requestVoucher(phoneNumber, callbackUrl);

        var phoneNumberCaptor = ArgumentCaptor.forClass(String.class);
        var ttlCaptor = ArgumentCaptor.forClass(Long.class);
        var callbackCaptor = ArgumentCaptor.forClass(String.class);

        verify(jedis).setex(phoneNumberCaptor.capture(), ttlCaptor.capture(), callbackCaptor.capture());

        assertEquals(phoneNumber, phoneNumberCaptor.getValue());
        assertEquals(180L, ttlCaptor.getValue());
        assertEquals(callbackUrl, callbackCaptor.getValue());
    }

    @Test
    public void requestVoucher_shouldSendRequestToKafka() {
        String phoneNumber = "0912345678";
        controller.requestVoucher(phoneNumber, "https://www.cde.com");

        var topicCaptor = ArgumentCaptor.forClass(String.class);
        var phoneNumberCaptor = ArgumentCaptor.forClass(String.class);

        verify(kafkaProducer).send(topicCaptor.capture(), phoneNumberCaptor.capture());

        assertEquals(KafkaTopicConstants.REQUEST_CODE, topicCaptor.getValue());
        assertEquals(phoneNumber, phoneNumberCaptor.getValue());
    }
}
