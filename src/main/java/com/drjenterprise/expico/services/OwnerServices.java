package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.repositories.OwnerRepository;
import com.drjenterprise.expico.services.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServices {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerServices(OwnerRepository ownerRepository, OwnerMapper ownerMapper){
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    public OwnerDAO createOwner(OwnerDAO request){

        OwnerDAO existingOwnerDAO = ownerRepository.findByOwnerNIF(request.getOwnerNIF()).orElse(null);

        if(existingOwnerDAO == null){
            return ownerRepository.save(request);
        }
        else {
            return null;
        }
    }

    public List<OwnerDAO> getAllOwners(){
        return ownerRepository.findAll();
    }

    public OwnerDAO getOwnerById(Integer id){
        OwnerDAO existingDao = ownerRepository.findById(id).orElse(null);
        if(existingDao != null){
            return existingDao;
        }
        else {
            return null;
        }
    }

    public OwnerDAO getOwnerByNIF(Integer nif){
        OwnerDAO existingDao = ownerRepository.findByOwnerNIF(nif).orElse(null);
        if(existingDao != null){
            return existingDao;
        }
        else {
            return null;
        }
    }
    public OwnerDAO updateOwner(OwnerDAO updatedOwnerDao){
        OwnerDAO existingOwner = ownerRepository.findByOwnerNIF(updatedOwnerDao.getOwnerNIF()).orElse(null);

        if(existingOwner != null){
            updatedOwnerDao.setOwnerInternalId(existingOwner.getOwnerInternalId());
            OwnerDAO savedDao = ownerRepository.save(updatedOwnerDao);
            return savedDao;
        }
        else {
            return null;
        }
    }

    public boolean deleteOwnerByNIF(Integer nif){

        OwnerDAO existingOwnerDAO = ownerRepository.findByOwnerNIF(nif).orElse(null);
        if(existingOwnerDAO != null){
            ownerRepository.deleteById(existingOwnerDAO.getOwnerInternalId());
            return true;
        }
        else {
            return false;
        }

    }

}
