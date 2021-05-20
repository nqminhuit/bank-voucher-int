package com.nqminhuit.voucherservice.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.stream.Collectors;
import com.nqminhuit.voucherservice.domain.Voucher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class VoucherServiceTest {

    @Autowired
    private VoucherRepository voucherRepository;

    @Test
    public void findAllVouchersByPhoneNumber() {
        String phoneNumber = "0909123456";
        insertVoucher(phoneNumber, "voucherCode1");
        insertVoucher(phoneNumber, "voucherCode2");
        insertVoucher(phoneNumber, "voucherCode3");

        var vouchers = voucherRepository.findAllByPhoneNumber(phoneNumber);
        assertEquals(3, vouchers.size());
        var codes = vouchers.stream().map(Voucher::getVoucherCode).collect(Collectors.toList());
        assertTrue(codes.contains("voucherCode1"));
        assertTrue(codes.contains("voucherCode2"));
        assertTrue(codes.contains("voucherCode3"));
    }

    private void insertVoucher(String phoneNumber, String voucherCode) {
        Voucher voucher = new Voucher();
        voucher.setPhoneNumber(phoneNumber);
        voucher.setVoucherCode(voucherCode);
        voucherRepository.save(voucher);
    }

}
