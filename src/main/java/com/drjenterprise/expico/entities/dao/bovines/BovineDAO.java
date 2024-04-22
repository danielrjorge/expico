package com.drjenterprise.expico.entities.dao.bovines;

import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.enums.BovineBreed;
import com.drjenterprise.expico.entities.enums.BovineColor;
import com.drjenterprise.expico.entities.enums.BovineStatus;
import com.drjenterprise.expico.entities.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "b100")
public class BovineDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bovine_internal_id")
    private int bovineInternalId;

    @Column(name = "bovine_code", nullable = false, unique = true)
    private String bovineCode;

    @Column(name = "bovine_breed", nullable = false)
    @Enumerated(EnumType.STRING)
    private BovineBreed bovineBreed;

    @Column(name = "bovine_color")
    @Enumerated(EnumType.STRING)
    private BovineColor bovineColor;

    @Column(name = "bovine_gender")
    @Enumerated(EnumType.STRING)
    private Gender bovineGender;

    @Column(name = "bovine_birth_date", nullable = false)
    private LocalDate bovineBirthDate;

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
    private String mothersCode;

    @Column(name = "fathers_code")
    private String fathersCode;

    @ManyToOne
    @JoinColumn(name = "last_known_owner", referencedColumnName = "owner_nif")
    private OwnerDAO lastKnownOwner;


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

    public OwnerDAO getLastKnownOwner() {
        return lastKnownOwner;
    }

    public void setLastKnownOwner(OwnerDAO lastKnownOwner) {
        this.lastKnownOwner = lastKnownOwner;
    }
}
