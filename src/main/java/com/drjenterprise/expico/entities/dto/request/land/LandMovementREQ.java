package com.drjenterprise.expico.entities.dto.request.land;

import java.time.LocalDate;

public class LandMovementREQ {

    private LocalDate landMovementDate;
    private String bovineCode;
    private String destinationLandCode;

    public LocalDate getLandMovementDate() {
        return landMovementDate;
    }

    public void setLandMovementDate(LocalDate landMovementDate) {
        this.landMovementDate = landMovementDate;
    }

    public String getBovineCode() {
        return bovineCode;
    }

    public void setBovineCode(String bovineCode) {
        this.bovineCode = bovineCode;
    }

    public String getDestinationLandCode() {
        return destinationLandCode;
    }

    public void setDestinationLandCode(String destinationLandCode) {
        this.destinationLandCode = destinationLandCode;
    }
}
