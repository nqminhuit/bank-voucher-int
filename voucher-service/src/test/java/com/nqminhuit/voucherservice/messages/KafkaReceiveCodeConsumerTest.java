package com.nqminhuit.voucherservice.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import java.util.HashMap;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import com.nqminhuit.voucherservice.services.impl.VoucherServiceImpl;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" })
@TestInstance(Lifecycle.PER_CLASS)
public class KafkaReceiveCodeConsumerTest {

    private Producer<String, Object> producer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Autowired
    private KafkaReceiveCodeConsumer consumer;

    @SpyBean
    private VoucherServiceImpl voucherService;

    @Captor
    private ArgumentCaptor<VoucherDto> voucherDtoCaptor;

    @BeforeAll
    public void setup() {
        consumer.setVoucherService(voucherService);
        var configs = new HashMap<String, Object>(KafkaTestUtils.producerProps(embeddedKafka));
        producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new JsonSerializer<>())
            .createProducer();
    }

    @AfterAll
    public void tearDown() {
        producer.close();
    }

    @Test
    public void listenToReceiveCodeMsg() throws InterruptedException {
        Thread.sleep(1000); // TODO: remove this ugly wait for kafka embedded broker to start
        produceMessage();
        verify(voucherService, timeout(5000).times(1)).insertNewVoucher(voucherDtoCaptor.capture());

        var capturedVoucherDto = voucherDtoCaptor.getValue();
        assertNotNull(capturedVoucherDto);
        assertEquals("0909876543", capturedVoucherDto.getPhoneNumber());
        assertEquals("voucherCode1", capturedVoucherDto.getVoucherCode());
    }

    private void produceMessage() {
        var msg = new ReceiveCodeMsg("0909876543", "voucherCode1", "SUCCESS");
        producer.send(new ProducerRecord<String, Object>(KafkaTopicConstants.RECEIVE_CODE, msg));
    }

}
