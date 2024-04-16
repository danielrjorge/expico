package com.drjenterprise.expico.entities.dto.request.bovines;

import com.drjenterprise.expico.entities.dto.request.owner.OwnerREQ;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BovineSaleREQ {
    private String bovineCode;
    private LocalDate saleDate;
    private OwnerREQ buyer;
    private BigDecimal totalPrice;
    private BigDecimal vatPercentage;

    public String getBovineCode() {
        return bovineCode;
    }

    public void setBovineCode(String bovineCode) {
        this.bovineCode = bovineCode;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public OwnerREQ getBuyer() {
        return buyer;
    }

    public void setBuyer(OwnerREQ buyer) {
        this.buyer = buyer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(BigDecimal vatPercentage) {
        this.vatPercentage = vatPercentage;
    }
}
