package com.drjenterprise.expico.entities;

import com.drjenterprise.expico.entities.enums.BovineBreed;
import com.drjenterprise.expico.entities.enums.BovineColor;
import com.drjenterprise.expico.entities.enums.BovineStatus;
import com.drjenterprise.expico.entities.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "b100")
public class Bovine {
    @Id
    @Column(name="bovinecode")
    private int bovineCode;

    @Column(name="breed")
    @Enumerated(EnumType.STRING)
    private BovineBreed breed;

    @Column(name="color")
    @Enumerated(EnumType.STRING)
    private BovineColor color;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birthdate")
    private LocalDate birthDate;

    @Column(name="bovinename")
    private String bovineName;

    @Column(name="bovinestatus")
    @Enumerated(EnumType.STRING)
    private BovineStatus bovineStatus;

    @Column(name="dateofdeath")
    private LocalDate dateOfDeath;

    @Column(name="causeofdeath")
    private String causeOfDeath;

    @Column(name="motherscode")
    private int mothersCode;

    @Column(name="fatherscode")
    private int fathersCode;

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

    @Override
    public String toString() {
        return "Bovine{" +
                "bovineCode=" + bovineCode +
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
                '}';
    }
}
