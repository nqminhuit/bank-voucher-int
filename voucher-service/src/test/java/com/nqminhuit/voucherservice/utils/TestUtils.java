package com.nqminhuit.voucherservice.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import com.nqminhuit.voucherservice.domain.Voucher;
import com.nqminhuit.voucherservice.repositories.VoucherRepository;

public class TestUtils {

    public static void insertVoucher(
        VoucherRepository voucherRepository, String phoneNumber, String voucherCode) {

        Voucher voucher = new Voucher();
        voucher.setPhoneNumber(phoneNumber);
        voucher.setVoucherCode(voucherCode);
        voucherRepository.save(voucher);
    }

    public static <K> void assertSamePhoneNumber(String expectPhoneNumber, List<K> vouchers) {
        Method methodGetPhoneNumber;
        try {
            methodGetPhoneNumber = vouchers.get(0).getClass().getMethod("getPhoneNumber", (Class<?>[]) null);
        }
        catch (NoSuchMethodException | SecurityException e) {
            fail("could not get method 'getPhoneNumber'");
            return;
        }

        var phoneNumbers = vouchers.stream().map(i -> {
            try {
                return methodGetPhoneNumber.invoke(i);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                fail("could not invoke method: " + methodGetPhoneNumber);
                return "";
            }
        }).collect(Collectors.toSet());
        assertEquals(1, phoneNumbers.size());
        assertEquals(expectPhoneNumber, phoneNumbers.iterator().next());
    }
}
