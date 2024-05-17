package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.initializer.ProfileOwner;
import com.drjenterprise.expico.repositories.BovineRepository;
import com.drjenterprise.expico.services.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BovineServices {
    private final BovineRepository bovineRepository;
    private final ProfileOwner profileOwner;
    private final OwnerMapper ownerMapper;
    private static final Logger logger = Logger.getLogger(BovineServices.class.getName());

    @Autowired
    public BovineServices(BovineRepository bovineRepository, ProfileOwner profileOwner, OwnerMapper ownerMapper){
        this.bovineRepository = bovineRepository;
        this.profileOwner = profileOwner;
        this.ownerMapper = ownerMapper;
    }

    public List<BovineDAO> getAllBovines(){
        return bovineRepository.findAll();
    }

    public List<BovineDAO> getAllOwnedBovines() {
        return bovineRepository.findAllByLastKnownOwner(ownerMapper.convert(profileOwner)).orElse(null);
    }

    public BovineDAO getBovineByInternalId(int id){
        return bovineRepository.findById(id).orElse(null);
    }

    public BovineDAO getBovineByCode(String bovineCode){
        BovineDAO existingDao = bovineRepository.findByBovineCode(bovineCode).orElse(null);
        if(existingDao != null){
            return existingDao;
        }
        else {
            logger.warning("The bovine code "+bovineCode+" does not exist in the database!");
            return null;
        }
    }

    public BovineDAO createBovine(BovineDAO bovineDAO){

        if(getBovineByCode(bovineDAO.getBovineCode()) == null){
            return bovineRepository.save(bovineDAO);
        }
        else {
            logger.warning("The bovine code already exists in the database!");
            return null;
        }

    }

    public BovineDAO updateBovine(BovineDAO updateBovineDao){
        BovineDAO existingBovine = getBovineByCode(updateBovineDao.getBovineCode());

        if(existingBovine != null){
            return bovineRepository.save(updateBovineDao);

        }
        else {
            logger.warning("No bovine exists in the database with the bovine code "+updateBovineDao.getBovineCode()+"!");
            return null;
        }
    }

    public ResponseEntity<Void> deleteBovineByInternalId(Integer id){
        BovineDAO bovineDAOToDelete = bovineRepository.findById(id).orElse(null);
        if (bovineDAOToDelete != null){
            bovineRepository.delete(bovineDAOToDelete);
            return ResponseEntity.ok().build();
        }
        else {
            logger.warning("No bovine exists in the database with the bovine internal id "+id+"!");
            return ResponseEntity.badRequest().build();
        }
    }

}
