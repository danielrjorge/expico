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
@Table(name = "b102")
public class BovineSaleDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_internal_id")
    private int saleInternalId;

    @OneToOne
    @JoinColumn (name = "bovine", nullable = false, referencedColumnName = "bovine_code")
    private BovineDAO bovine;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Digits(integer = 9, fraction = 0, message = "NIF must have exactly 9 digits.")
    @NotNull(message = "Nif is required.")
    @Column(name = "buyers_nif")
    private Integer buyersNif;

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

    public int getSaleInternalId() {
        return saleInternalId;
    }

    public void setSaleInternalId(int saleInternalId) {
        this.saleInternalId = saleInternalId;
    }

    public BovineDAO getBovine() {
        return bovine;
    }

    public void setBovine(BovineDAO bovine) {
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

    public FinancialMovementDao getFinancialMovement() {
        return financialMovement;
    }

    public void setFinancialMovement(FinancialMovementDao financialMovement) {
        this.financialMovement = financialMovement;
    }
}
