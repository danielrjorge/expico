package com.drjenterprise.expico.entities.dao.land;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "l100")
public class LandDao {

    //TODO need to improve the entity with more fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pastureInternalId;
    @NotNull
    @Column(name = "land_code", unique = true)
    private String landCode;
    private String gpsCoordinates;
    @DecimalMin(value = "0.00", inclusive = true, message = "Total Ha area must be greater than or equal to 0.")
    private BigDecimal totalHa;
    @NotNull(message = "The 'pasture' field is required")
    @JsonProperty("pasture")
    private boolean isPasture;

    public int getPastureInternalId() {
        return pastureInternalId;
    }

    public void setPastureInternalId(int pastureInternalId) {
        this.pastureInternalId = pastureInternalId;
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(String gps_coordinates) {
        this.gpsCoordinates = gps_coordinates;
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
