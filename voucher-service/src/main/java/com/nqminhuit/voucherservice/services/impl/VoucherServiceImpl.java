package com.nqminhuit.voucherservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.nqminhuit.voucherservice.dtos.VoucherDto;
import com.nqminhuit.voucherservice.mappers.VoucherMapper;
import com.nqminhuit.voucherservice.repositories.VoucherRepository;
import com.nqminhuit.voucherservice.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherMapper voucherMapper;

    @Override
    public List<VoucherDto> findAllVouchersByPhoneNumber(String phoneNumber) {
        return voucherRepository.findAllByPhoneNumber(phoneNumber)
            .stream()
            .map(voucherMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void insertNewVoucher(VoucherDto voucherDto) {
        voucherRepository.save(voucherMapper.toEntity(voucherDto));
    }

}
