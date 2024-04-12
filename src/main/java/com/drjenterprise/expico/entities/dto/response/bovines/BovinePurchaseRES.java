package com.drjenterprise.expico.entities.dto.response.bovines;

import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BovinePurchaseRES {
    private BovineRES bovine;
    private LocalDate purchaseDate;
    private Integer sellersNif;
    private BigDecimal totalPrice;
    private BigDecimal vatPercentage;

    private FinancialMovementDao movement;

    public BovineRES getBovine() {
        return bovine;
    }

    public void setBovine(BovineRES bovine) {
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

    public FinancialMovementDao getMovement() {
        return movement;
    }

    public void setMovement(FinancialMovementDao movement) {
        this.movement = movement;
    }
}
