package com.nqminhuit.voucherservice.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherservice.domain.Voucher;
import com.nqminhuit.voucherservice.repositories.mock_impl.VoucherMapperImpl;
import com.nqminhuit.voucherservice.services.impl.VoucherServiceImpl;
import com.nqminhuit.voucherservice.utils.TestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class VoucherServiceTest {

    @Autowired
    private VoucherRepository voucherRepository;

    private VoucherServiceImpl voucherService = new VoucherServiceImpl();

    @Test
    public void repositoryFindAllVouchersByPhoneNumber() {
        String phoneNumber = "0909123456";
        TestUtils.insertVoucher(voucherRepository, phoneNumber, "voucherCode1");
        TestUtils.insertVoucher(voucherRepository, phoneNumber, "voucherCode2");
        TestUtils.insertVoucher(voucherRepository, phoneNumber, "voucherCode3");

        var vouchers = voucherRepository.findAllByPhoneNumber(phoneNumber);
        assertEquals(3, vouchers.size());
        var codes = vouchers.stream().map(Voucher::getVoucherCode).collect(Collectors.toList());
        assertTrue(codes.contains("voucherCode1"));
        assertTrue(codes.contains("voucherCode2"));
        assertTrue(codes.contains("voucherCode3"));
        TestUtils.assertSamePhoneNumber(phoneNumber, vouchers);
    }

    @Test
    public void entityShouldContain4AuditFields() {
        String phoneNumber = "0909AuditField";
        TestUtils.insertVoucher(voucherRepository, phoneNumber, "voucherCode1");
        var voucher = voucherRepository.findAllByPhoneNumber(phoneNumber).get(0);

        assertEquals(voucher.getCreatedBy(), "system");
        assertEquals(voucher.getLastModifiedBy(), "system");
        var now = Date.from(ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS).toInstant());
        assertEquals(0, DateUtils.truncatedCompareTo(now, voucher.getCreatedDate(), Calendar.SECOND));
        assertEquals(0, DateUtils.truncatedCompareTo(now, voucher.getLastModifiedDate(), Calendar.SECOND));
    }

    @Test
    public void insertNewVoucher() {
        voucherService.setVoucherMapper(new VoucherMapperImpl());
        voucherService.setVoucherRepository(voucherRepository);
        var phoneNumber = "+84909876123";
        VoucherDto dto = VoucherDto.builder()
            .phoneNumber(phoneNumber)
            .voucherCode("voucherCode")
            .build();

        voucherService.insertNewVoucher(dto);

        var entity = voucherRepository.findAllByPhoneNumber(phoneNumber).get(0);
        assertEquals(phoneNumber, entity.getPhoneNumber());
        assertEquals("voucherCode", entity.getVoucherCode());
    }

}
