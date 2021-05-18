package com.nqminhuit.voucherservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import com.nqminhuit.voucherShared.domain.BaseAuditEntity;

@Entity
public class Voucher extends BaseAuditEntity {

    @Id
    @SequenceGenerator(name = "seq_voucher_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_voucher_id")
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "voucher_code", nullable = false)
    private String voucherCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    @Override
    public String toString() {
        return "\"Voucher\": {\"id\": \"" + id + "\", \"phoneNumber\": \"" + phoneNumber
            + "\", \"voucherCode\": \"" + voucherCode + "\"}";
    }

}
