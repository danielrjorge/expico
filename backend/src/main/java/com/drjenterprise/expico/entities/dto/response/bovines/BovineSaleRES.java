package com.drjenterprise.expico.entities.dto.response.bovines;

import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BovineSaleRES {
    private BovineRES bovine;
    private LocalDate saleDate;
    private Integer buyersNif;
    private BigDecimal totalPrice;
    private BigDecimal vatPercentage;

    private FinancialMovementDao movement;

    public BovineRES getBovine() {
        return bovine;
    }

    public void setBovine(BovineRES bovine) {
        this.bovine = bovine;
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

    public FinancialMovementDao getMovement() {
        return movement;
    }

    public void setMovement(FinancialMovementDao movement) {
        this.movement = movement;
    }
}
