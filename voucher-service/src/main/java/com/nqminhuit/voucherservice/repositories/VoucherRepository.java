package com.nqminhuit.voucherservice.repositories;

import java.util.List;
import com.nqminhuit.voucherservice.domain.Voucher;
import org.springframework.data.repository.CrudRepository;

public interface VoucherRepository extends CrudRepository<Voucher, Long>  {

    List<Voucher> findAllByPhoneNumber(String phoneNumber);

}
