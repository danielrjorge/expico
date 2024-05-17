package com.drjenterprise.expico.entities.dao.bovines;

import com.drjenterprise.expico.entities.dao.land.LandDao;
import jakarta.persistence.*;

@Entity
@Table(name = "b104")
public class FarmBovineDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int farmBovineInternalId;

    @OneToOne
    @JoinColumn(name = "bovine_code", referencedColumnName = "bovine_code")
    private BovineDAO bovine;

    @ManyToOne
    @JoinColumn(name = "current_land", referencedColumnName = "land_code")
    private LandDao currentLand;

    public int getFarmBovineInternalId() {
        return farmBovineInternalId;
    }

    public void setFarmBovineInternalId(int farmBovineInternalId) {
        this.farmBovineInternalId = farmBovineInternalId;
    }

    public BovineDAO getBovine() {
        return bovine;
    }

    public void setBovine(BovineDAO bovine) {
        this.bovine = bovine;
    }

    public LandDao getCurrentLand() {
        return currentLand;
    }

    public void setCurrentLand(LandDao currentLand) {
        this.currentLand = currentLand;
    }
}
