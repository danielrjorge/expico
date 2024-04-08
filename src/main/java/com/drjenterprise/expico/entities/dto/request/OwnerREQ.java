package com.drjenterprise.expico.entities.dto.request;

import jakarta.validation.constraints.Email;

public class OwnerREQ {
    private Integer ownerId;
    private Integer ownerGovId;
    private String ownerName;
    private Integer ownerNIF;
    private String ownerCellNumber;
    @Email(message = "Invalid email format")
    private String ownerEmail;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerGovId() {
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

    public Integer getOwnerNIF() {
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
}
