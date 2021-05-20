package com.nqminhuit.voucherShared.constants;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class KafkaTopicConstantsTest {

    @Test
    public void testKafkaConstants() {
        assertEquals("receive-code", KafkaTopicConstants.RECEIVE_CODE);
        assertEquals("request-code", KafkaTopicConstants.REQUEST_CODE);
        assertEquals("receiveCodeMsgListenerContainerFactory",
            KafkaTopicConstants.RECEIVE_CODE_LISTENER_CONTAINER_FACTORY);
    }
}
