package com.drjenterprise.expico.entities;

import com.drjenterprise.expico.entities.enums.BovineBreed;
import com.drjenterprise.expico.entities.enums.BovineColor;
import com.drjenterprise.expico.entities.enums.BovineStatus;
import com.drjenterprise.expico.entities.enums.Gender;
import com.drjenterprise.expico.entities.owner.OwnerDAO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "b100")
public class Bovine {
    @Id
    @GeneratedValue
    @Column(name = "bovine_id")
    private int bovineId;

    @Column(name = "bovine_prefix", nullable = false, length = 2)
    private String bovinePrefix;

    @Column(name = "bovine_code", nullable = false)
    private int bovineCode;

    @Column(name = "breed", nullable = false)
    @Enumerated(EnumType.STRING)
    private BovineBreed breed;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private BovineColor color;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "bovine_name")
    private String bovineName;

    @Column(name = "bovine_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BovineStatus bovineStatus;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    @Column(name = "cause_of_death")
    private String causeOfDeath;

    @Column(name = "mothers_code")
    private int mothersCode;

    @Column(name = "fathers_code", nullable = false)
    private int fathersCode;

    @PrimaryKeyJoinColumn(name = "last_known_owner_id")
    @OneToOne(targetEntity = OwnerDAO.class)
    private int lastKnownOwnerId;

    public int getBovineId() {
        return bovineId;
    }

    public void setBovineId(int bovineId) {
        this.bovineId = bovineId;
    }

    public String getBovinePrefix() {
        return bovinePrefix;
    }

    public void setBovinePrefix(String bovinePrefix) {
        this.bovinePrefix = bovinePrefix;
    }

    public int getBovineCode() {
        return bovineCode;
    }

    public void setBovineCode(int bovineCode) {
        this.bovineCode = bovineCode;
    }

    public BovineBreed getBreed() {
        return breed;
    }

    public void setBreed(BovineBreed breed) {
        this.breed = breed;
    }

    public BovineColor getColor() {
        return color;
    }

    public void setColor(BovineColor color) {
        this.color = color;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public int getMothersCode() {
        return mothersCode;
    }

    public void setMothersCode(int mothersCode) {
        this.mothersCode = mothersCode;
    }

    public int getFathersCode() {
        return fathersCode;
    }

    public void setFathersCode(int fathersCode) {
        this.fathersCode = fathersCode;
    }

    public int getLastKnownOwnerId() {
        return lastKnownOwnerId;
    }

    public void setLastKnownOwnerId(int lastKnownOwnerId) {
        this.lastKnownOwnerId = lastKnownOwnerId;
    }

    @Override
    public String toString() {
        return "Bovine{" +
                "bovineId=" + bovineId +
                ", bovinePrefix='" + bovinePrefix + '\'' +
                ", bovineCode=" + bovineCode +
                ", breed=" + breed +
                ", color=" + color +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", bovineName='" + bovineName + '\'' +
                ", bovineStatus=" + bovineStatus +
                ", dateOfDeath=" + dateOfDeath +
                ", causeOfDeath='" + causeOfDeath + '\'' +
                ", mothersCode=" + mothersCode +
                ", fathersCode=" + fathersCode +
                ", lastKnownOwnerId=" + lastKnownOwnerId +
                '}';
    }
}
