package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dto.request.BovineREQ;
import com.drjenterprise.expico.entities.dto.response.BovineRES;
import com.drjenterprise.expico.exceptions.NifNotFoundException;
import com.drjenterprise.expico.repositories.BovineRepository;
import com.drjenterprise.expico.services.mappers.BovineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BovineServices {
    private final BovineRepository bovineRepository;
    private final BovineMapper bovineMapper;
    private static final Logger logger = Logger.getLogger(BovineServices.class.getName());

    @Autowired
    public BovineServices(BovineRepository bovineRepository, BovineMapper bovineMapper){
        this.bovineRepository = bovineRepository;
        this.bovineMapper = bovineMapper;
    }

    public List<BovineRES> getAllBovines(){
        List<BovineDAO> bovineDAOList = bovineRepository.findAll();
        List<BovineRES> responseList = new ArrayList<>();
        for(BovineDAO dao: bovineDAOList){
            responseList.add(bovineMapper.convert(dao));
        }
        return responseList;
    }

    public BovineRES getBovineByInternalId(int id){
        BovineDAO existingDao = bovineRepository.findById(id).orElse(null);
        if(existingDao != null){
            return bovineMapper.convert(existingDao);
        }
        else {
            return null;
        }
    }

    public BovineRES getBovineByCode(String bovineCode){
        BovineDAO existingDao = bovineRepository.findByBovineCode(bovineCode).orElse(null);
        if(existingDao != null){
            return bovineMapper.convert(existingDao);
        }
        else {
            logger.warning("The bovine code "+bovineCode+"does not exist in the database!");
            return null;
        }
    }

    public BovineRES createBovine(BovineREQ request){

        BovineDAO existingBovineDAO = bovineRepository.findByBovineCode(request.getBovineCode()).orElse(null);

        if(existingBovineDAO == null){
            try {
                BovineDAO requestBovineDao = bovineMapper.convert(request);
                BovineDAO savedBovineDao = bovineRepository.save(requestBovineDao);
                return bovineMapper.convert(savedBovineDao);
            } catch (NifNotFoundException e) {
                logger.severe(e.getMessage());
                return null;
            }
        }
        else {
            logger.warning("The bovine code already exists in the database!");
            return null;
        }
    }

    public BovineRES updateBovine(BovineREQ updateBovineREQ){
        BovineDAO existingBovine = bovineRepository.findByBovineCode(updateBovineREQ.getBovineCode()).orElse(null);

        if(existingBovine != null){
            updateBovineREQ.setBovineInternalId(existingBovine.getBovineInternalId());
            try{
                BovineDAO savedDao = bovineRepository.save(bovineMapper.convert(updateBovineREQ));
                return bovineMapper.convert(savedDao);
            } catch (NifNotFoundException e) {
                logger.severe(e.getMessage());
                return null;
            }

        }
        else {
            logger.warning("No bovine exists in the database with the bovine code "+updateBovineREQ.getBovineCode()+"!");
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
