package com.drjenterprise.expico.entities.dto.request.bovines;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BovineSaleREQ {
    private String bovineCode;
    private LocalDate saleDate;
    private Integer buyersNif;
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

    public Integer getBuyersNif() {
        return buyersNif;
    }

    public void setBuyersNif(Integer buyersNif) {
        this.buyersNif = buyersNif;
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
