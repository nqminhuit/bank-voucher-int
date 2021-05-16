package com.nqminhuit.voucherintservice.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voucher.provider.models.ResponseVoucherModel;
import com.voucher.provider.models.enumerations.VoucherResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VoucherProviderClientTests {

    private VoucherProviderClient client;
    private ObjectMapper jsonMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        client = new VoucherProviderClient();
    }

    @Test
    public void requestForVoucherCode() throws IOException, InterruptedException {
        var response = client.requestForVoucherCode();

        assertEquals(200, response.statusCode());
        var res = jsonMapper.readValue(response.body(), ResponseVoucherModel.class);
        assertNotNull(res);
        assertEquals(VoucherResponseStatus.SUCCESS, res.getVoucherResponseStatus());
    }
}
