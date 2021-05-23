package com.nqminhuit.voucherintservice.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nqminhuit.voucherShared.constants.KafkaTopicConstants;
import com.nqminhuit.voucherShared.messageModels.ReceiveCodeMsg;
import com.nqminhuit.voucherintservice.http_clients.VoucherProviderClient;
import com.nqminhuit.voucherintservice.http_clients.utils.HttpResponseUtils;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestInstance(Lifecycle.PER_CLASS)
public class KafkaRequestCodeConsumerTest {

    private Producer<String, String> producer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Autowired
    private KafkaRequestCodeConsumer consumer;

    private KafkaReceiveCodeProducer kafkaReceiveCodeProducer = spy(KafkaReceiveCodeProducer.class);

    @MockBean
    private VoucherProviderClient vpsClientMock;

    @Captor
    private ArgumentCaptor<String> kafkaSendTopicCaptor;

    @Captor
    private ArgumentCaptor<ReceiveCodeMsg> receiveCodeMsgCaptor;

    @BeforeAll
    public void setup() throws JsonProcessingException {
        String response = new ObjectMapper().writeValueAsString(Map.ofEntries(
            Map.entry("message", "message"),
            Map.entry("code", "code"),
            Map.entry("voucherResponseStatus", VoucherResponseStatus.SUCCESS.name()),
            Map.entry("phoneNumber", "+84909123654"),
            Map.entry("codeVerifier", "codeVerifier"),
            Map.entry("codeTransform", "codeTransform")));

        doReturn(HttpResponseUtils.response(response, 200))
            .when(vpsClientMock)
            .requestForVoucherCode("+84909123654");

        doNothing()
            .when(kafkaReceiveCodeProducer)
            .send(eq(KafkaTopicConstants.RECEIVE_CODE), any(ReceiveCodeMsg.class));

        consumer.setKafkaReceiveCodeProducer(kafkaReceiveCodeProducer);
        consumer.setVoucherProviderClient(vpsClientMock);

        setupProducer();
    }

    private void setupProducer() {
        var configs = new HashMap<String, Object>(KafkaTestUtils.producerProps(embeddedKafka));
        producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new StringSerializer())
            .createProducer();
    }

    @AfterAll
    public void tearDown() {
        producer.close();
    }

    @Test
    public void listenToRequestCodeMsg() throws InterruptedException {
        Thread.sleep(1000);
        producerMessage();
        Thread.sleep(1000);
        verify(kafkaReceiveCodeProducer).send(kafkaSendTopicCaptor.capture(), receiveCodeMsgCaptor.capture());
        var sentTopic = kafkaSendTopicCaptor.getValue();
        var sentMsg = receiveCodeMsgCaptor.getValue();

        assertEquals(KafkaTopicConstants.RECEIVE_CODE, sentTopic);
        assertEquals("+84909123654", sentMsg.getPhoneNumber());
        assertEquals("SUCCESS", sentMsg.getStatus());
        assertEquals("code", sentMsg.getVoucherCode());
    }

    private void producerMessage() {
        var record = new ProducerRecord<String, String>(KafkaTopicConstants.REQUEST_CODE, "+84909123654");
        producer.send(record);
    }
}
