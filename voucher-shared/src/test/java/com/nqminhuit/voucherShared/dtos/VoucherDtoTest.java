package com.nqminhuit.voucherShared.dtos;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VoucherDtoTest {

    private static VoucherDto expected = new VoucherDto();

    static {
        expected.setPhoneNumber("0909123456");
        expected.setId(122L);
        expected.setVoucherCode("voucherCode");
    }

    @Test
    public void receiveCodeMsgBuilder() {
        var actual = VoucherDto.builder()
            .id(122L)
            .phoneNumber("0909123456")
            .voucherCode("voucherCode")
            .build();

        assertEquals(expected.toString(), actual.toString());
    }

}
