package com.drjenterprise.expico.entities.dao.financial;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

@Entity
@Table(name = "f101")
public class VatAdjustmentDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vat_internal_id")
    private int vatInternalId;

    @OneToOne
    @JoinColumn(name = "movement_id", referencedColumnName = "movement_internal_id")
    private FinancialMovementDao financialMovement;

    @DecimalMin(value = "0", message = "VAT percentage must be greater or equal to 0.")
    @DecimalMax(value = "100", message = "VAT percentage must be less than or equal to 100.")
    @Column(name = "vat_percentage", precision = 3)
    private BigDecimal vatPercentage;

    @DecimalMin(value = "0.00", inclusive = true, message = "Vat value must be greater than or equal to 0.")
    @Column(name = "vat_value_purchase", precision = 10, scale = 2)
    private BigDecimal vatValuePurchase;

    @DecimalMin(value = "0.00", inclusive = true, message = "Vat value must be greater than or equal to 0.")
    @Column(name = "vat_value_sale", precision = 10, scale = 2)
    private BigDecimal vatValueSale;

    public int getVatInternalId() {
        return vatInternalId;
    }

    public void setVatInternalId(int vatInternalId) {
        this.vatInternalId = vatInternalId;
    }

    public FinancialMovementDao getFinancialMovement() {
        return financialMovement;
    }

    public void setFinancialMovement(FinancialMovementDao financialMovement) {
        this.financialMovement = financialMovement;
    }

    public BigDecimal getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(BigDecimal vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public BigDecimal getVatValuePurchase() {
        return vatValuePurchase;
    }

    public void setVatValuePurchase(BigDecimal vatValuePurchase) {
        this.vatValuePurchase = vatValuePurchase;
    }

    public BigDecimal getVatValueSale() {
        return vatValueSale;
    }

    public void setVatValueSale(BigDecimal vatValueSale) {
        this.vatValueSale = vatValueSale;
    }
}
