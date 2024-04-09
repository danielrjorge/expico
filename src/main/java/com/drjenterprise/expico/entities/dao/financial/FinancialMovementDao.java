package com.drjenterprise.expico.entities.dao.financial;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "f100")
public class FinancialMovementDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_internal_id")
    private int movementInternalId;

    @Column(name = "movement_date")
    private LocalDate movementDate;

    @DecimalMin(value = "0.00", inclusive = true, message = "Price must be greater than or equal to 0.")
    @Column(name = "movement_credit", precision = 10, scale = 2)
    private BigDecimal movementCredit;

    @DecimalMin(value = "0.00", inclusive = true, message = "Price must be greater than or equal to 0.")
    @Column(name = "movement_debit", precision = 10, scale = 2)
    private BigDecimal movementDebit;

    public int getMovementInternalId() {
        return movementInternalId;
    }

    public void setMovementInternalId(int movementInternalId) {
        this.movementInternalId = movementInternalId;
    }

    public LocalDate getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDate movementDate) {
        this.movementDate = movementDate;
    }

    public BigDecimal getMovementCredit() {
        return movementCredit;
    }

    public void setMovementCredit(BigDecimal movementCredit) {
        this.movementCredit = movementCredit;
    }

    public BigDecimal getMovementDebit() {
        return movementDebit;
    }

    public void setMovementDebit(BigDecimal movementDebit) {
        this.movementDebit = movementDebit;
    }
}
