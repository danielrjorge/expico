package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dao.bovines.FarmBovineDao;
import com.drjenterprise.expico.repositories.FarmBovineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class FarmBovineService {

    private static final Logger logger = Logger.getLogger(FarmBovineService.class.getName());

    private final FarmBovineRepository farmBovineRepository;

    @Autowired
    public FarmBovineService(FarmBovineRepository farmBovineRepository) {
        this.farmBovineRepository = farmBovineRepository;
    }

    public List<FarmBovineDao> getAllFarmBovines(){
        return farmBovineRepository.findAll();
    }

    public List<FarmBovineDao> getAllFarmBovinesByLand(String landCode) {
        return farmBovineRepository.findAllByCurrentLandLandCode(landCode);
    }

    public FarmBovineDao getFarmBovineByCode(String code) {
        return farmBovineRepository.findByBovineBovineCode(code);
    }

    public FarmBovineDao addFarmBovine(BovineDAO bovine) {
        FarmBovineDao existingFarmBovine = farmBovineRepository.findByBovineBovineCode(bovine.getBovineCode());
        if (existingFarmBovine != null) {
            logger.severe("A bovine with the code '" + bovine.getBovineCode() + "' is already part of the farm!");
            return null;
        }
        else {
            FarmBovineDao newFarmBovine = new FarmBovineDao();
            // this bovine has to exist in the b100 table
            newFarmBovine.setBovine(bovine);
            return farmBovineRepository.save(newFarmBovine);
        }
    }

    public FarmBovineDao updateFarmBovine(FarmBovineDao farmBovineDao) {
        FarmBovineDao existingFarmBovine = farmBovineRepository.findByBovineBovineCode(farmBovineDao.getBovine().getBovineCode());
        if (existingFarmBovine == null) {
            logger.severe("UPDATE ERROR: A bovine with the code '" + farmBovineDao.getBovine().getBovineCode() + "' is not part of the farm!");
            return null;
        }
        else {
            return farmBovineRepository.save(farmBovineDao);
        }

    }

    public boolean deleteFarmBovine(BovineDAO bovineDAO) {
        FarmBovineDao existingFarmBovine = farmBovineRepository.findByBovineBovineCode(bovineDAO.getBovineCode());

        if (existingFarmBovine != null) {
            farmBovineRepository.delete(existingFarmBovine);
            return true;
        }
        else {
            logger.severe("A bovine with the code '" + bovineDAO.getBovineCode() + "' does not exist in the farm bovine table! Delete failed!");
            return false;
        }
    }
}
