package com.nqminhuit.voucherintservice.clients;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VoucherProviderClientTests {

    private VoucherProviderClient client;

    @BeforeEach
    public void setup() {
        client = new VoucherProviderClient();
    }

    @Test
    public void requestForVoucherCode() throws IOException, InterruptedException {
        var response = client.requestForVoucherCode();
    }
}
