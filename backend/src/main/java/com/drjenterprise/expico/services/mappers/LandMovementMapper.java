package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.bovines.FarmBovineDao;
import com.drjenterprise.expico.entities.dao.land.LandDao;
import com.drjenterprise.expico.entities.dao.land.LandMovementDao;
import com.drjenterprise.expico.entities.dto.request.land.LandMovementREQ;
import com.drjenterprise.expico.entities.dto.response.land.LandMovementRES;
import com.drjenterprise.expico.exceptions.BovineNotFound;
import com.drjenterprise.expico.exceptions.LandNotFoundException;
import com.drjenterprise.expico.services.FarmBovineService;
import com.drjenterprise.expico.services.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandMovementMapper {

    private final FarmBovineService farmBovineService;
    private final BovineMapper bovineMapper;
    private final LandService landService;
    private final LandMapper landMapper;

    @Autowired
    public LandMovementMapper(FarmBovineService farmBovineService, BovineMapper bovineMapper, LandService landService,
                              LandMapper landMapper) {
        this.farmBovineService = farmBovineService;
        this.bovineMapper = bovineMapper;
        this.landService = landService;
        this.landMapper = landMapper;
    }

    public LandMovementDao convert(LandMovementREQ request) throws BovineNotFound, LandNotFoundException {
        LandMovementDao dao = new LandMovementDao();
        dao.setLandMovementDate(request.getLandMovementDate());
        // TODO Unify the fetching of the bovines and land either on the mappers or the controllers
        // Current idea is to have exceptions for this for clarity, but need to unify
        FarmBovineDao farmBovineDao = farmBovineService.getFarmBovineByCode(request.getBovineCode());
        if (farmBovineDao == null) {
            throw new BovineNotFound("No bovine with code '" + request.getBovineCode() + "' exists in the database.");
        } else {
            dao.setFarmBovine(farmBovineDao);
        }

        //The origin code can be NULL, for example, when first setting the land
        if (farmBovineDao.getCurrentLand() != null) {
            dao.setPreviousLand(farmBovineDao.getCurrentLand());
        }

        LandDao destinationLandDao = landService.getLandByCode(request.getDestinationLandCode());
        if (destinationLandDao == null) {
            throw new LandNotFoundException("No destination land exists with code '" + request.getDestinationLandCode() + "'.");
        }
        else {
            dao.setDestinationLand(destinationLandDao);
        }

        return dao;
    }

    public LandMovementRES convert(LandMovementDao dao) {
        LandMovementRES response = new LandMovementRES();
        response.setLandMovementInternalId(dao.getLandMovementInternalId());
        response.setBovine(bovineMapper.convert(dao.getFarmBovine().getBovine()));
        response.setLandMovementDate(dao.getLandMovementDate());
        if (dao.getPreviousLand() != null) {
            response.setOriginLand(landMapper.convert(dao.getPreviousLand()));
        }
        response.setDestinationLand(landMapper.convert(dao.getDestinationLand()));
        return response;
    }
}
