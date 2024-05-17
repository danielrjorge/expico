package com.drjenterprise.expico.entities.dao.bovines;

import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "b101")
public class BovinePurchaseDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_internal_id")
    private Integer purchaseInternalId;

    @OneToOne
    @JoinColumn (name = "bovine", nullable = false, referencedColumnName = "bovine_code")
    private BovineDAO bovine;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Digits(integer = 9, fraction = 0, message = "NIF must have exactly 9 digits.")
    @NotNull(message = "Nif is required.")
    @Column(name = "sellers_nif")
    private Integer sellersNif;

    @DecimalMin(value = "0.00", inclusive = true, message = "Price must be greater than or equal to 0.")
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @DecimalMin(value = "0", message = "VAT percentage must be greater or equal to 0.")
    @DecimalMax(value = "100", message = "VAT percentage must be less than or equal to 100.")
    @Column(name = "vat_percentage", precision = 3)
    private BigDecimal vatPercentage;

    @OneToOne
    @JoinColumn(name = "movement_id", referencedColumnName = "movement_internal_id")
    @NotNull(message = "No financial movement was associated to this bovine purchase.")
    private FinancialMovementDao financialMovement;

    public Integer getPurchaseInternalId() {
        return purchaseInternalId;
    }

    public void setPurchaseInternalId(Integer purchaseInternalId) {
        this.purchaseInternalId = purchaseInternalId;
    }

    public BovineDAO getBovine() {
        return bovine;
    }

    public void setBovine(BovineDAO bovine) {
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

    public FinancialMovementDao getFinancialMovement() {
        return financialMovement;
    }

    public void setFinancialMovement(FinancialMovementDao financialMovement) {
        this.financialMovement = financialMovement;
    }
}
