package com.drjenterprise.expico.services.mappers;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.dto.request.bovines.BovineREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovineRES;
import com.drjenterprise.expico.entities.dto.response.owner.OwnerRES;
import com.drjenterprise.expico.entities.enums.BovineStatus;
import com.drjenterprise.expico.exceptions.NifNotFoundException;
import com.drjenterprise.expico.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BovineMapper {

    @Autowired
    private OwnerRepository ownerRepository;

    public BovineDAO convert(BovineREQ request) throws NifNotFoundException {
        BovineDAO bovineDAO = new BovineDAO();
        bovineDAO.setBovineInternalId(request.getBovineInternalId());
        bovineDAO.setBovineCode(request.getBovineCode());
        bovineDAO.setBovineBreed(request.getBovineBreed());
        bovineDAO.setBovineColor(request.getBovineColor());
        bovineDAO.setBovineGender(request.getBovineGender());
        bovineDAO.setBovineBirthDate(request.getBovineBirthDate());
        bovineDAO.setBovineName(request.getBovineName());
        bovineDAO.setBovineStatus(request.getBovineStatus());
        if(request.getBovineStatus() == BovineStatus.MORTO){
            bovineDAO.setDateOfDeath(request.getDateOfDeath());
            bovineDAO.setCauseOfDeath(request.getCauseOfDeath());
        }
        bovineDAO.setMothersCode(request.getMothersCode());
        bovineDAO.setFathersCode(request.getFathersCode());

        //For now, the owner will be a non required field, may update in the future
        //need to call a service to fetch the owner from the db, might do this somewhere else
        if(request.getLastKnownOwnerNif() != null){
            OwnerDAO ownerDAO = ownerRepository.findByOwnerNIF(request.getLastKnownOwnerNif()).orElse(null);
            if(ownerDAO == null){
                throw new NifNotFoundException();
            }
            else {
                bovineDAO.setLastKnownOwner(ownerDAO);
            }
        }
        return bovineDAO;
    }

    public BovineRES convert(BovineDAO dao){
        BovineRES response = new BovineRES();
        response.setBovineInternalId(dao.getBovineInternalId());
        response.setBovineCode(dao.getBovineCode());
        response.setBovineBreed(dao.getBovineBreed());
        response.setBovineColor(dao.getBovineColor());
        response.setBovineGender(dao.getBovineGender());
        response.setBovineBirthDate(dao.getBovineBirthDate());
        response.setBovineName(dao.getBovineName());
        response.setBovineStatus(dao.getBovineStatus());
        if(dao.getBovineStatus() == BovineStatus.MORTO){
            response.setCauseOfDeath(dao.getCauseOfDeath());
            response.setDateOfDeath(dao.getDateOfDeath());
        }
        response.setMothersCode(dao.getMothersCode());
        response.setFathersCode(dao.getFathersCode());
        //same here as above
        if(dao.getLastKnownOwner() != null){
            //Only show relevant attributes (check later for security issues)
            OwnerRES responseOwner = new OwnerRES();
            responseOwner.setOwnerNIF(dao.getLastKnownOwner().getOwnerNIF());
            responseOwner.setOwnerName(dao.getLastKnownOwner().getOwnerName());
            response.setLastKnownOwner(responseOwner);
        }
        return response;
    }
}
