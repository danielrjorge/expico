package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dao.land.LandMovementDao;
import com.drjenterprise.expico.entities.dto.request.land.LandMovementREQ;
import com.drjenterprise.expico.exceptions.BovineNotFound;
import com.drjenterprise.expico.services.BovineServices;
import com.drjenterprise.expico.services.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandMovementMapper {

    private final BovineServices bovineServices;
    private final LandService landService;

    @Autowired
    public LandMovementMapper(BovineServices bovineServices, LandService landService) {
        this.bovineServices = bovineServices;
        this.landService = landService;
    }

    public LandMovementDao convert(LandMovementREQ request) throws BovineNotFound {
        LandMovementDao dao = new LandMovementDao();
        dao.setLandMovementDate(request.getLandMovementDate());
        //TODO Unify the fetching of the bovines and land either on the mappers or the controllers
        // Current idea is to have exceptions for this for clarity, but need to unify
        BovineDAO bovineDao = bovineServices.getBovineByCode(request.getBovineCode());
        if (bovineDao == null) {
            throw new BovineNotFound("No bovine with code '" + request.getBovineCode() + "' exists in the database.");
        }
        return null;
    }
}
