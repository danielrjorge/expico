package com.drjenterprise.expico.entities.dto.request.bovines;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BovineButcherREQ {

    private String bovineCode;
    private LocalDate butcherDate;
    private BigDecimal totalWeight;
    private BigDecimal netWeight;
    private BigDecimal pricePerKilo;
    private BigDecimal vatPercentage;

    public String getBovineCode() {
        return bovineCode;
    }

    public void setBovineCode(String bovineCode) {
        this.bovineCode = bovineCode;
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

    public BigDecimal getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(BigDecimal vatPercentage) {
        this.vatPercentage = vatPercentage;
    }
}
