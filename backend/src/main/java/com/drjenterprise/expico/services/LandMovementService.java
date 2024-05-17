package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.land.LandMovementDao;
import com.drjenterprise.expico.repositories.LandMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandMovementService {

    private final LandMovementRepository landMovementRepository;

    @Autowired
    public LandMovementService(LandMovementRepository landMovementRepository) {
        this.landMovementRepository = landMovementRepository;
    }

    public List<LandMovementDao> getAllLandMovements() {
        return landMovementRepository.findAll();
    }

    public LandMovementDao addLandMovement(LandMovementDao landMovementDao) {
        return landMovementRepository.save(landMovementDao);
    }
}
