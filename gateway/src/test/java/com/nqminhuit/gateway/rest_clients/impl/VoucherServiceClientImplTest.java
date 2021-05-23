package com.nqminhuit.gateway.rest_clients.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.net.URI;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherShared.dtos.VoucherDtosWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class VoucherServiceClientImplTest {

    private VoucherServiceClientImpl voucherServiceClient;

    private RestTemplate restTemplate = spy(RestTemplate.class);

    @BeforeEach
    public void setup() {
        voucherServiceClient = new VoucherServiceClientImpl();
        voucherServiceClient.setRequestProtocol("https");
        voucherServiceClient.setVoucherServiceUri("voucher-service");

        doReturn(responseEntity(HttpStatus.OK, null))
            .when(restTemplate)
            .getForEntity(
                eq(URI.create("https://voucher-service/api/voucher/phoneNotFound").toString()), any());

        var correctPhoneNumber = "+84909888777";
        doReturn(responseEntity(HttpStatus.OK, createVoucherDtosWrapper(correctPhoneNumber)))
            .when(restTemplate)
            .getForEntity(
                eq(URI.create("https://voucher-service/api/voucher/" + correctPhoneNumber).toString()),
                any());

        voucherServiceClient.setRestTemplate(restTemplate);
    }

    private VoucherDtosWrapper createVoucherDtosWrapper(String phoneNumber) {
        var voucherDtos = new ArrayList<VoucherDto>();
        voucherDtos.add(VoucherDto.builder().phoneNumber(phoneNumber).voucherCode("111").id(1L).build());
        voucherDtos.add(VoucherDto.builder().phoneNumber(phoneNumber).voucherCode("222").id(2L).build());
        voucherDtos.add(VoucherDto.builder().phoneNumber(phoneNumber).voucherCode("333").id(3L).build());

        var wrapper = new VoucherDtosWrapper();
        wrapper.setVoucherDtos(voucherDtos);
        return wrapper;
    }

    public ResponseEntity<VoucherDtosWrapper> responseEntity(HttpStatus status, VoucherDtosWrapper wrapper) {
        return ResponseEntity.status(status).body(wrapper);
    }

    @Test
    public void findAllVouchersByPhoneNumber_phoneNotFound() {
        String phoneNumber = "phoneNotFound";
        var voucherDtos = voucherServiceClient.findAllVouchersByPhoneNumber(phoneNumber);
        assertEquals(0, voucherDtos.size());
    }

    @Test
    public void findAllVouchersByPhoneNumber() {
        String phoneNumber = "+84909888777";
        var voucherDtos = voucherServiceClient.findAllVouchersByPhoneNumber(phoneNumber);
        assertEquals(3, voucherDtos.size());

        var ids = voucherDtos.stream().map(VoucherDto::getId).collect(Collectors.toSet());
        var idIterator = ids.iterator();
        assertEquals(1L, idIterator.next());
        assertEquals(2L, idIterator.next());
        assertEquals(3L, idIterator.next());

        var voucherCodes = voucherDtos.stream().map(VoucherDto::getVoucherCode).collect(Collectors.toSet());
        var codeIterator = voucherCodes.iterator();
        assertEquals("111", codeIterator.next());
        assertEquals("222", codeIterator.next());
        assertEquals("333", codeIterator.next());

        var phoneNumbers = voucherDtos.stream().map(VoucherDto::getPhoneNumber).collect(Collectors.toSet());
        assertEquals(1, phoneNumbers.size());
        assertEquals("+84909888777", phoneNumbers.iterator().next());
    }

}
