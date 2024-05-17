package com.drjenterprise.expico.entities.dto.request.bovines;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BovinePurchaseREQ {
    private BovineREQ bovine;
    private LocalDate purchaseDate;
    private Integer sellersNif;
    private BigDecimal totalPrice;
    private BigDecimal vatPercentage;

    public BovineREQ getBovine() {
        return bovine;
    }

    public void setBovine(BovineREQ bovine) {
        this.bovine = bovine;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getSellersNif() {
        return sellersNif;
    }

    public void setSellersNif(Integer sellersNif) {
        this.sellersNif = sellersNif;
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
