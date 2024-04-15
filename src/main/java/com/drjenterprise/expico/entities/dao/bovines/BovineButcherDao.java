package com.drjenterprise.expico.entities.dao.bovines;

import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "b103")
public class BovineButcherDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "butcher_internal_id")
    private int butcherInternalId;

    @OneToOne
    @JoinColumn(name = "associated_bovine")
    private BovineDAO associatedBovine;

    @Column(name = "butcher_date")
    private LocalDate butcherDate;

    @DecimalMin(value = "0.00", inclusive = false, message = "Total weight must be greater than 0.")
    @Column(name = "total_weight", precision = 6, scale = 2)
    private BigDecimal totalWeight;

    @DecimalMin(value = "0.00", inclusive = false, message = "Net weight must be greater than 0.")
    @Column(name = "net_weight", precision = 6, scale = 2)
    private BigDecimal netWeight;

    @DecimalMin(value = "0.00", inclusive = true, message = "Price per kilo must be greater or equal to 0.")
    @Column(name = "price_per_kilo", precision = 4, scale = 2)
    private BigDecimal pricePerKilo;

    @Formula("total_weight * price_per_kilo")
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @DecimalMin(value = "0", message = "VAT percentage must be greater or equal to 0.")
    @DecimalMax(value = "100", message = "VAT percentage must be less than or equal to 100.")
    @Column(name = "vat_percentage", precision = 3)
    private BigDecimal vatPercentage;

    @OneToOne
    @JoinColumn(name = "movement_id", referencedColumnName = "movement_internal_id")
    @NotNull(message = "No financial movement was associated to this bovine butcher.")
    private FinancialMovementDao financialMovement;

    public int getButcherInternalId() {
        return butcherInternalId;
    }

    public void setButcherInternalId(int butcherInternalId) {
        this.butcherInternalId = butcherInternalId;
    }

    public BovineDAO getAssociatedBovine() {
        return associatedBovine;
    }

    public void setAssociatedBovine(BovineDAO associatedBovine) {
        this.associatedBovine = associatedBovine;
    }

    public LocalDate getButcherDate() {
        return butcherDate;
    }

    public void setButcherDate(LocalDate butcherDate) {
        this.butcherDate = butcherDate;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
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

    public FinancialMovementDao getFinancialMovement() {
        return financialMovement;
    }

    public void setFinancialMovement(FinancialMovementDao financialMovement) {
        this.financialMovement = financialMovement;
    }
}
