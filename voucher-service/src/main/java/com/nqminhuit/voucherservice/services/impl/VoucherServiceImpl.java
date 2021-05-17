package com.nqminhuit.voucherservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.nqminhuit.voucherShared.dtos.VoucherDto;
import com.nqminhuit.voucherservice.domain.mappers.VoucherMapper;
import com.nqminhuit.voucherservice.repositories.VoucherRepository;
import com.nqminhuit.voucherservice.services.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {

    private static final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherMapper voucherMapper;

    @Override
    public List<VoucherDto> findAllVouchersByPhoneNumber(String phoneNumber) {
        return voucherRepository.findAllByPhoneNumber(phoneNumber).stream()
            .map(voucherMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void insertNewVoucher(VoucherDto voucherDto) {
        log.info("handle request to insert new voucher: {}", voucherDto);
        voucherRepository.save(voucherMapper.toEntity(voucherDto));
    }

}
