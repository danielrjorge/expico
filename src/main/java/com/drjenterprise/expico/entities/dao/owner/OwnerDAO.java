package com.drjenterprise.expico.entities.dao.owner;

import jakarta.persistence.*;

@Entity
@Table(name = "o100")
public class OwnerDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_internal_id")
    private int ownerInternalId;

    @Column(name = "owner_gov_id")
    private int ownerGovId;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_nif", nullable = false, unique = true)
    private int ownerNIF;

    @Column(name = "owner_cell_number")
    private String ownerCellNumber;

    @Column(name = "owner_email")
    private String ownerEmail;

    public int getOwnerInternalId() {
        return ownerInternalId;
    }

    public void setOwnerInternalId(int ownerInternalId) {
        this.ownerInternalId = ownerInternalId;
    }

    public int getOwnerGovId() {
        return ownerGovId;
    }

    public void setOwnerGovId(int ownerGovId) {
        this.ownerGovId = ownerGovId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getOwnerNIF() {
        return ownerNIF;
    }

    public void setOwnerNIF(int ownerNIF) {
        this.ownerNIF = ownerNIF;
    }

    public String getOwnerCellNumber() {
        return ownerCellNumber;
    }

    public void setOwnerCellNumber(String ownerCellNumber) {
        this.ownerCellNumber = ownerCellNumber;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerInternalId +
                ", ownerGovId=" + ownerGovId +
                ", ownerName='" + ownerName + '\'' +
                ", ownerNIF=" + ownerNIF +
                ", ownerCellNumber='" + ownerCellNumber + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                '}';
    }

}
