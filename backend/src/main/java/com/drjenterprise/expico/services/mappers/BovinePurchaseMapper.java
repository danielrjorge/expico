package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.bovines.BovinePurchaseDao;
import com.drjenterprise.expico.entities.dto.request.bovines.BovinePurchaseREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovinePurchaseRES;
import com.drjenterprise.expico.exceptions.NifNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BovinePurchaseMapper {

    @Autowired
    private BovineMapper bovineMapper;

    public BovinePurchaseDao convert(BovinePurchaseREQ request) throws NifNotFoundException {
        BovinePurchaseDao dao = new BovinePurchaseDao();
        dao.setBovine(bovineMapper.convert(request.getBovine()));
        dao.setPurchaseDate(request.getPurchaseDate());
        dao.setSellersNif(request.getSellersNif());
        dao.setTotalPrice(request.getTotalPrice());
        dao.setVatPercentage(request.getVatPercentage());
        return dao;
    }

    public BovinePurchaseRES convert(BovinePurchaseDao dao) {
        BovinePurchaseRES response = new BovinePurchaseRES();
        response.setBovine(bovineMapper.convert(dao.getBovine()));
        response.setPurchaseDate(dao.getPurchaseDate());
        response.setTotalPrice(dao.getTotalPrice());
        response.setVatPercentage(dao.getVatPercentage());
        response.setMovement(dao.getFinancialMovement());
        response.setSellersNif(dao.getSellersNif());
        return response;
    }

}
