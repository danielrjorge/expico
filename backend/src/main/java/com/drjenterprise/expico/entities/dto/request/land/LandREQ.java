package com.drjenterprise.expico.entities.dto.request.land;

import java.math.BigDecimal;

public class LandREQ {
    private String landCode;
    private String gpsCoordinates;
    private BigDecimal totalHa;
    private boolean isPasture;

    // might want to add the bovines in the request, for now I don't think it's needed
    // we just add the bovines to the pasture after

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public BigDecimal getTotalHa() {
        return totalHa;
    }

    public void setTotalHa(BigDecimal totalHa) {
        this.totalHa = totalHa;
    }

    public boolean isPasture() {
        return isPasture;
    }

    public void setPasture(boolean pasture) {
        isPasture = pasture;
    }
}
