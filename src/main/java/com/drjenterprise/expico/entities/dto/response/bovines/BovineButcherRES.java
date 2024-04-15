package com.drjenterprise.expico.entities.dto.response.bovines;

import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BovineButcherRES {

    private int butcherInternalId;
    private BovineRES associatedBovine;
    private LocalDate butcherDate;
    private BigDecimal totalWeight;
    private BigDecimal netWeight;
    private BigDecimal pricePerKilo;
    private BigDecimal totalPrice;
    private BigDecimal vatPercentage;

    //For debugging
    private FinancialMovementDao financialMovementDao;

    public int getButcherInternalId() {
        return butcherInternalId;
    }

    public void setButcherInternalId(int butcherInternalId) {
        this.butcherInternalId = butcherInternalId;
    }

    public BovineRES getAssociatedBovine() {
        return associatedBovine;
    }

    public void setAssociatedBovine(BovineRES associatedBovine) {
        this.associatedBovine = associatedBovine;
    }

    public LocalDate getButcherDate() {
        return butcherDate;
    }

    public void setButcherDate(LocalDate butcherDate) {
        this.butcherDate = butcherDate;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
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

    public FinancialMovementDao getFinancialMovementDao() {
        return financialMovementDao;
    }

    public void setFinancialMovementDao(FinancialMovementDao financialMovementDao) {
        this.financialMovementDao = financialMovementDao;
    }
}
