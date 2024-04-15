package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.bovines.BovineButcherDao;
import com.drjenterprise.expico.entities.dto.request.bovines.BovineButcherREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovineButcherRES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BovineButcherMapper {

    @Autowired
    private BovineMapper bovineMapper;

    public BovineButcherDao convert(BovineButcherREQ request) {
        BovineButcherDao dao = new BovineButcherDao();
        //need to get the associated bovine outside the mapper
        dao.setButcherDate(request.getButcherDate());
        dao.setTotalWeight(request.getTotalWeight());
        dao.setNetWeight(request.getNetWeight());
        dao.setPricePerKilo(request.getPricePerKilo());
        dao.setTotalPrice(request.getTotalWeight().multiply(request.getPricePerKilo()));
        dao.setVatPercentage(request.getVatPercentage());
        return dao;
    }

    public BovineButcherRES convert(BovineButcherDao dao) {
        BovineButcherRES response = new BovineButcherRES();
        response.setButcherInternalId(dao.getButcherInternalId());
        response.setAssociatedBovine(bovineMapper.convert(dao.getAssociatedBovine()));
        response.setButcherDate(dao.getButcherDate());
        response.setTotalWeight(dao.getTotalWeight());
        response.setNetWeight(dao.getNetWeight());
        response.setPricePerKilo(dao.getPricePerKilo());
        response.setTotalPrice(dao.getTotalPrice());
        response.setVatPercentage(dao.getVatPercentage());
        response.setFinancialMovementDao(dao.getFinancialMovement());
        return response;
    }
}
