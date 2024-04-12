package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.dto.request.owner.OwnerREQ;
import com.drjenterprise.expico.entities.dto.response.owner.OwnerRES;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    public OwnerDAO convert(OwnerREQ request){
        OwnerDAO ownerDAO = new OwnerDAO();
        if(request.getOwnerId() != null){
            ownerDAO.setOwnerInternalId(request.getOwnerId());
        }
        if(request.getOwnerGovId() != null){
            ownerDAO.setOwnerGovId(request.getOwnerGovId());
        }
        ownerDAO.setOwnerName(request.getOwnerName());
        ownerDAO.setOwnerNIF(request.getOwnerNIF());
        ownerDAO.setOwnerCellNumber(request.getOwnerCellNumber());
        ownerDAO.setOwnerEmail(request.getOwnerEmail());

        return ownerDAO;
    }

    public OwnerRES convert(OwnerDAO dao){
        OwnerRES response = new OwnerRES();
        response.setOwnerGovId(dao.getOwnerGovId());
        response.setOwnerName(dao.getOwnerName());
        response.setOwnerNIF(dao.getOwnerNIF());
        response.setOwnerCellNumber(dao.getOwnerCellNumber());
        response.setOwnerEmail(dao.getOwnerEmail());
        return response;
    }
}
