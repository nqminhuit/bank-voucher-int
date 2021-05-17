package com.nqminhuit.gateway.rest_clients.impl;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import com.nqminhuit.gateway.rest_clients.VoucherServiceClient;
import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherShared.dtos.VoucherDtosWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VoucherServiceClientImpl implements VoucherServiceClient {

    private static final Logger log = LoggerFactory.getLogger(VoucherServiceClientImpl.class);

    @Value("${bank-voucher-int.voucher-service.server}")
    private String voucherServiceUri;

    @Value("${gateway.request.protocol}")
    private String requestProtocol;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<VoucherDto> findAllVouchersByPhoneNumber(String phoneNumber) {
        var url = URI
            .create(requestProtocol + "://" + voucherServiceUri + "/api/voucher/" + phoneNumber)
            .toString();
        log.info("Request voucher service for voucherDtos with url: {}", url);
        var response = restTemplate.getForEntity(url, VoucherDtosWrapper.class);
        var responseBody = response.getBody();
        if (responseBody != null) {
            return responseBody.getVoucherDtos();
        }
        return Collections.emptyList();
    }

}
