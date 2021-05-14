package com.nqminhuit.voucherservice.messages;

import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import com.nqminhuit.voucherservice.domain.dtos.VoucherDto;
import com.nqminhuit.voucherservice.services.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiveCodeConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaReceiveCodeConsumer.class);

    @Autowired
    private VoucherService voucherService;

    @KafkaListener(
        topics = KafkaTopicConstants.RECEIVE_CODE,
        groupId = "rec-group-db",
        containerFactory = KafkaTopicConstants.RECEIVE_CODE_LISTENER_CONTAINER_FACTORY)
    public void listenToReceiveCodeMsg(ReceiveCodeMsg msg) {
        log.info("voucher-service recives code msg: {}", msg);
        VoucherDto voucher = new VoucherDto();
        voucher.setPhoneNumber(msg.getPhoneNumber());
        voucher.setVoucherCode(msg.getVoucherCode());
        voucherService.insertNewVoucher(voucher);
    }

}
