package com.drjenterprise.expico.entities.dto.response.bovines;

import com.drjenterprise.expico.entities.dto.response.owner.OwnerRES;
import com.drjenterprise.expico.entities.enums.BovineBreed;
import com.drjenterprise.expico.entities.enums.BovineColor;
import com.drjenterprise.expico.entities.enums.BovineStatus;
import com.drjenterprise.expico.entities.enums.Gender;

import java.time.LocalDate;

public class BovineRES {
    private int bovineInternalId;
    private String bovineCode;
    private BovineBreed bovineBreed;
    private BovineColor bovineColor;
    private Gender bovineGender;
    private LocalDate bovineBirthDate;
    private String bovineName;
    private BovineStatus bovineStatus;
    private LocalDate dateOfDeath;
    private String causeOfDeath;
    private String mothersCode;
    private String fathersCode;
    private OwnerRES lastKnownOwner;

    public int getBovineInternalId() {
        return bovineInternalId;
    }

    public void setBovineInternalId(int bovineInternalId) {
        this.bovineInternalId = bovineInternalId;
    }

    public String getBovineCode() {
        return bovineCode;
    }

    public void setBovineCode(String bovineCode) {
        this.bovineCode = bovineCode;
    }

    public BovineBreed getBovineBreed() {
        return bovineBreed;
    }

    public void setBovineBreed(BovineBreed bovineBreed) {
        this.bovineBreed = bovineBreed;
    }

    public BovineColor getBovineColor() {
        return bovineColor;
    }

    public void setBovineColor(BovineColor bovineColor) {
        this.bovineColor = bovineColor;
    }

    public Gender getBovineGender() {
        return bovineGender;
    }

    public void setBovineGender(Gender bovineGender) {
        this.bovineGender = bovineGender;
    }

    public LocalDate getBovineBirthDate() {
        return bovineBirthDate;
    }

    public void setBovineBirthDate(LocalDate bovineBirthDate) {
        this.bovineBirthDate = bovineBirthDate;
    }

    public String getBovineName() {
        return bovineName;
    }

    public void setBovineName(String bovineName) {
        this.bovineName = bovineName;
    }

    public BovineStatus getBovineStatus() {
        return bovineStatus;
    }

    public void setBovineStatus(BovineStatus bovineStatus) {
        this.bovineStatus = bovineStatus;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public String getMothersCode() {
        return mothersCode;
    }

    public void setMothersCode(String mothersCode) {
        this.mothersCode = mothersCode;
    }

    public String getFathersCode() {
        return fathersCode;
    }

    public void setFathersCode(String fathersCode) {
        this.fathersCode = fathersCode;
    }

    public OwnerRES getLastKnownOwner() {
        return lastKnownOwner;
    }

    public void setLastKnownOwner(OwnerRES lastKnownOwner) {
        this.lastKnownOwner = lastKnownOwner;
    }
}
