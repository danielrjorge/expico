package com.drjenterprise.expico.entities.dto.response.land;

import com.drjenterprise.expico.entities.dto.response.bovines.BovineRES;

import java.time.LocalDate;

public class LandMovementRES {

    private int landMovementInternalId;
    private LocalDate landMovementDate;
    private BovineRES bovine;
    private LandRES originLand;
    private LandRES destinationLand;

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

    public BovineRES getBovine() {
        return bovine;
    }

    public void setBovine(BovineRES bovine) {
        this.bovine = bovine;
    }

    public LandRES getOriginLand() {
        return originLand;
    }

    public void setOriginLand(LandRES originLand) {
        this.originLand = originLand;
    }

    public LandRES getDestinationLand() {
        return destinationLand;
    }

    public void setDestinationLand(LandRES destinationLand) {
        this.destinationLand = destinationLand;
    }
}
