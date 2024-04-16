package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.bovines.BovineSaleDao;
import com.drjenterprise.expico.entities.dto.request.bovines.BovineSaleREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovineSaleRES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BovineSaleMapper {

    @Autowired
    private BovineMapper bovineMapper;

    public BovineSaleDao convert(BovineSaleREQ request){
        BovineSaleDao dao = new BovineSaleDao();
        dao.setSaleDate(request.getSaleDate());
        dao.setBuyersNif(request.getBuyer().getOwnerNIF());
        dao.setTotalPrice(request.getTotalPrice());
        dao.setVatPercentage(request.getVatPercentage());
        return dao;
    }

    public BovineSaleRES convert(BovineSaleDao dao) {
        BovineSaleRES response = new BovineSaleRES();
        response.setBovine(bovineMapper.convert(dao.getBovine()));
        response.setSaleDate(dao.getSaleDate());
        response.setTotalPrice(dao.getTotalPrice());
        response.setVatPercentage(dao.getVatPercentage());
        response.setMovement(dao.getFinancialMovement());
        response.setBuyersNif(dao.getBuyersNif());
        return response;
    }

}
