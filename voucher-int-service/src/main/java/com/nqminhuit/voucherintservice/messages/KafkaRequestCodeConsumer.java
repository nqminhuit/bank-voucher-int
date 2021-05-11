package com.nqminhuit.voucherintservice.messages;

import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherintservice.clients.VoucherProviderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaRequestCodeConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaRequestCodeConsumer.class);

    @Autowired
    private VoucherProviderClient vpsClient;

    @KafkaListener(topics = KafkaTopicConstants.REQUEST_CODE, groupId = "req-group")
    public void listenToRequestCode(String message) {
        log.info("listen to request code message: {}", message);
        vpsClient.requestForVoucherCode();
        log.info("done!!!");
    }

}
