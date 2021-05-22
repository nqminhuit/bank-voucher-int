package com.nqminhuit.voucherservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.nqminhuit.voucherservice.messages.KafkaReceiveCodeConsumer;
import com.nqminhuit.voucherservice.messages.configs.KafkaConsumerConfig;
import com.nqminhuit.voucherservice.repositories.VoucherRepository;
import com.nqminhuit.voucherservice.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllersTest {

    @Autowired
    private VoucherController voucherController;

    @Autowired
    private KafkaReceiveCodeConsumer kafkaReceiveCodeConsumer;

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Autowired
    private VoucherRepository voucherRepository;

    @Test
    public void contextLoads() {
        assertNotNull(voucherController);
        assertNotNull(kafkaReceiveCodeConsumer);
        assertNotNull(kafkaConsumerConfig);
        assertNotNull(voucherRepository);
    }

    @Test
    public void findAllVouchersByPhoneNumber() {
        var phoneNumber = "0909000001";
        TestUtils.insertVoucher(voucherRepository, phoneNumber, "voucherCode1");
        TestUtils.insertVoucher(voucherRepository, phoneNumber, "voucherCode2");
        TestUtils.insertVoucher(voucherRepository, phoneNumber, "voucherCode3");

        var result = voucherController.findAllVouchersByPhoneNumber(phoneNumber);

        var voucherDtos = result.getVoucherDtos();
        assertEquals(3, voucherDtos.size());
        TestUtils.assertSamePhoneNumber(phoneNumber, voucherDtos);
    }

    @Test
    public void findAllVouchersByPhoneNumber_NotFound() {
        var result = voucherController.findAllVouchersByPhoneNumber("000000000");
        assertEquals(0, result.getVoucherDtos().size());
    }

}
