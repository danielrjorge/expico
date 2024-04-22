package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.land.LandDao;
import com.drjenterprise.expico.entities.dto.request.land.LandREQ;
import com.drjenterprise.expico.entities.dto.response.land.LandRES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandMapper {

    @Autowired
    private BovineMapper bovineMapper;

    public LandDao convert(LandREQ request) {
        LandDao dao = new LandDao();
        dao.setLandCode(request.getLandCode());
        dao.setGpsCoordinates(request.getGpsCoordinates());
        dao.setPasture(request.isPasture());
        dao.setTotalHa(request.getTotalHa());
        return dao;
    }

    public LandRES convert(LandDao dao) {
        LandRES response = new LandRES();
        response.setLandCode(dao.getLandCode());
        response.setGpsCoordinates(dao.getGpsCoordinates());
        response.setTotalHa(dao.getTotalHa());
        response.setPasture(dao.isPasture());
        if (dao.getBovinesInPasture() != null) {
            response.setBovinesInPasture(dao.getBovinesInPasture().stream().map(bovineMapper::convert).toList());
        }
        return response;
    }
}
