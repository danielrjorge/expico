package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.land.LandDao;
import com.drjenterprise.expico.repositories.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class LandService {

    private static final Logger logger = Logger.getLogger(LandService.class.getName());
    private final LandRepository landRepository;

    @Autowired
    public LandService(LandRepository landRepository) {
        this.landRepository = landRepository;
    }

    public List<LandDao> getAllLands() {
        return landRepository.findAll();
    }

    public LandDao getLandByCode(String code) {
        LandDao existingLandDao = landRepository.findByLandCode(code).orElse(null);
        if(existingLandDao != null) {
            return existingLandDao;
        }
        else {
            logger.warning("The land with code '"+code+"' does not exist in the database!");
            return null;
        }
    }

    public LandDao addLand(LandDao landDao) {
        if(getLandByCode(landDao.getLandCode()) == null) {
            return landRepository.save(landDao);
        }
        else {
            logger.warning("A land with this land code already exists in the database!");
            return null;
        }
    }

    public LandDao updateLand(LandDao updatedLandDao) {
        LandDao existingLandDao = landRepository.findByLandCode(updatedLandDao.getLandCode()).orElse(null);

        if(existingLandDao != null) {
            // We shouldn't be able to change the bovines in the pasture through here, this is just for other info
            //TODO need to check what happens when I update a land that has bovines in it
            // Maybe we can just have the bovine store the land it's in, not the land storing the bovines
            return landRepository.save(updatedLandDao);
        }
        else {
            logger.warning("No land exists in the database with the land code "+updatedLandDao.getLandCode()+"!");
            return null;
        }
    }

    public ResponseEntity<Void> deleteLandByCode(String code) {
        LandDao landDaoToDelete = landRepository.findByLandCode(code).orElse(null);
        if (landDaoToDelete != null) {
            landRepository.delete(landDaoToDelete);
            return ResponseEntity.ok().build();
        }
        else {
            logger.warning("The land with code '" + code + "' does not exist in the database!");
            return ResponseEntity.badRequest().build();
        }
    }
}
