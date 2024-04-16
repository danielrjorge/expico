package com.drjenterprise.expico.initializer;

import org.springframework.stereotype.Component;

@Component
public class ProfileOwner {
    private int profileOwnerInternalId;
    private int profileOwnerGovId;
    private String profileOwnerName;
    private int profileOwnerNIF;
    private String profileOwnerCellNumber;
    private String profileOwnerEmail;

    public int getProfileOwnerInternalId() {
        return profileOwnerInternalId;
    }

    public void setProfileOwnerInternalId(int profileOwnerInternalId) {
        this.profileOwnerInternalId = profileOwnerInternalId;
    }

    public int getProfileOwnerGovId() {
        return profileOwnerGovId;
    }

    public void setProfileOwnerGovId(int profileOwnerGovId) {
        this.profileOwnerGovId = profileOwnerGovId;
    }

    public String getProfileOwnerName() {
        return profileOwnerName;
    }

    public void setProfileOwnerName(String profileOwnerName) {
        this.profileOwnerName = profileOwnerName;
    }

    public int getProfileOwnerNIF() {
        return profileOwnerNIF;
    }

    public void setProfileOwnerNIF(int profileOwnerNIF) {
        this.profileOwnerNIF = profileOwnerNIF;
    }

    public String getProfileOwnerCellNumber() {
        return profileOwnerCellNumber;
    }

    public void setProfileOwnerCellNumber(String profileOwnerCellNumber) {
        this.profileOwnerCellNumber = profileOwnerCellNumber;
    }

    public String getProfileOwnerEmail() {
        return profileOwnerEmail;
    }

    public void setProfileOwnerEmail(String profileOwnerEmail) {
        this.profileOwnerEmail = profileOwnerEmail;
    }
}
