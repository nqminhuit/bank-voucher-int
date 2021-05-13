package com.nqminhuit.voucherShared.constants;

public class KafkaTopicConstants {

    public static final String REQUEST_CODE = "request-code";

    public static final String RECEIVE_CODE = "receive-code";

    /**
     * method name from {@link com.nqminhuit.voucherShared.configs.BaseConfig#receiveCodeMsgListenerContainerFactory()}
     */
    public static final String RECEIVE_CODE_LISTENER_CONTAINER_FACTORY = "receiveCodeMsgListenerContainerFactory";
}
