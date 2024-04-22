package com.drjenterprise.expico.entities.dto.response.land;

import com.drjenterprise.expico.entities.dto.response.bovines.BovineRES;

import java.math.BigDecimal;
import java.util.List;

public class LandRES {
    private String landCode;
    private String gpsCoordinates;
    private BigDecimal totalHa;
    private boolean isPasture;

    private List<BovineRES> bovinesInPasture;

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

    public List<BovineRES> getBovinesInPasture() {
        return bovinesInPasture;
    }

    public void setBovinesInPasture(List<BovineRES> bovinesInPasture) {
        this.bovinesInPasture = bovinesInPasture;
    }
}
