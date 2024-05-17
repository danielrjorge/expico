package com.drjenterprise.expico.entities.dao.land;

import com.drjenterprise.expico.entities.dao.bovines.FarmBovineDao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "l101")
public class LandMovementDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int landMovementInternalId;

    @NotNull
    private LocalDate landMovementDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "farm_bovine")
    private FarmBovineDao farmBovine;

    @ManyToOne
    @JoinColumn(name = "origin_land")
    private LandDao previousLand;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destination_land")
    private LandDao destinationLand;

    public int getLandMovementInternalId() {
        return landMovementInternalId;
    }

    public void setLandMovementInternalId(int landMovementInternalId) {
        this.landMovementInternalId = landMovementInternalId;
    }

    public LocalDate getLandMovementDate() {
        return landMovementDate;
    }

    public void setLandMovementDate(LocalDate landMovementDate) {
        this.landMovementDate = landMovementDate;
    }

    public FarmBovineDao getFarmBovine() {
        return farmBovine;
    }

    public void setFarmBovine(FarmBovineDao farmBovine) {
        this.farmBovine = farmBovine;
    }

    public LandDao getPreviousLand() {
        return previousLand;
    }

    public void setPreviousLand(LandDao previousLand) {
        this.previousLand = previousLand;
    }

    public LandDao getDestinationLand() {
        return destinationLand;
    }

    public void setDestinationLand(LandDao destinationLand) {
        this.destinationLand = destinationLand;
    }
}
