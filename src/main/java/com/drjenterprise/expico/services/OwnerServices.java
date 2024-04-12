package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.dto.request.owner.OwnerREQ;
import com.drjenterprise.expico.entities.dto.response.owner.OwnerRES;
import com.drjenterprise.expico.repositories.OwnerRepository;
import com.drjenterprise.expico.services.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public OwnerRES createOwner(OwnerREQ request){

        OwnerDAO existingOwnerDAO = ownerRepository.findByOwnerNIF(request.getOwnerNIF()).orElse(null);

        if(existingOwnerDAO == null){
            OwnerDAO requestOwnerDao = ownerMapper.convert(request);
            OwnerDAO savedOwnerDao = ownerRepository.save(requestOwnerDao);
            return ownerMapper.convert(savedOwnerDao);
        }
        else {
            return null;
        }
    }

    public List<OwnerRES> listAllOwners(){
        List<OwnerDAO> daoList = ownerRepository.findAll();
        List<OwnerRES> resList = new ArrayList<>();

        for (OwnerDAO dao: daoList){
            resList.add(ownerMapper.convert(dao));
        }

        return resList;
    }

    public OwnerRES getOwnerById(Integer id){
        OwnerDAO existingDao = ownerRepository.findById(id).orElse(null);
        if(existingDao != null){
            return ownerMapper.convert(existingDao);
        }
        else {
            return null;
        }
    }

    public OwnerRES getOwnerByNIF(Integer nif){
        OwnerDAO existingDao = ownerRepository.findByOwnerNIF(nif).orElse(null);
        if(existingDao != null){
            return ownerMapper.convert(existingDao);
        }
        else {
            return null;
        }
    }
    public OwnerRES updateOwner(OwnerREQ updatedOwnerREQ){
        OwnerDAO existingOwner = ownerRepository.findByOwnerNIF(updatedOwnerREQ.getOwnerNIF()).orElse(null);

        if(existingOwner != null){
            updatedOwnerREQ.setOwnerId(existingOwner.getOwnerInternalId());
            OwnerDAO savedDao = ownerRepository.save(ownerMapper.convert(updatedOwnerREQ));
            return ownerMapper.convert(savedDao);
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
