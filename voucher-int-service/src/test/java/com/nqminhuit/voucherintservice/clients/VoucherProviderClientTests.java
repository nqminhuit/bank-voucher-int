package com.nqminhuit.voucherintservice.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nqminhuit.voucherintservice.http_clients.VoucherProviderClient;
import com.nqminhuit.voucherintservice.http_clients.utils.HttpResponseUtils;
import com.voucher.provider.models.ResponseVoucherModel;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class VoucherProviderClientTests {

    private VoucherProviderClient client;

    private ObjectMapper jsonMapper;


    @BeforeAll
    public void setup() throws IOException, InterruptedException {
        client = new VoucherProviderClient();
        client.setRequestProtocol("http");
        client.setVoucherServerProviderUrl("host.com");
        HttpClient mockClient = mock(HttpClient.class);
        when(mockClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
            .thenReturn(HttpResponseUtils.response("{\"voucherResponseStatus\": \"SUCCESS\"}", 200));

        client.setHttpClient(mockClient);
        jsonMapper = new ObjectMapper();
    }

    @Test
    public void requestForVoucherCode() throws IOException, InterruptedException {
        var response = client.requestForVoucherCode("0909123456");

        assertEquals(200, response.statusCode());
        var res = jsonMapper.readValue(response.body(), ResponseVoucherModel.class);
        assertNotNull(res);
        assertEquals(VoucherResponseStatus.SUCCESS, res.getVoucherResponseStatus());
    }
}
