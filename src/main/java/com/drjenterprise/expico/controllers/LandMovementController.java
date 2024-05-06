package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.bovines.FarmBovineDao;
import com.drjenterprise.expico.entities.dao.land.LandMovementDao;
import com.drjenterprise.expico.entities.dto.request.land.LandMovementREQ;
import com.drjenterprise.expico.entities.dto.response.land.LandMovementRES;
import com.drjenterprise.expico.exceptions.BovineNotFound;
import com.drjenterprise.expico.exceptions.LandNotFoundException;
import com.drjenterprise.expico.services.FarmBovineService;
import com.drjenterprise.expico.services.LandMovementService;
import com.drjenterprise.expico.services.LandService;
import com.drjenterprise.expico.services.mappers.LandMovementMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/movement")
public class LandMovementController {
    private static final Logger logger = Logger.getLogger(LandMovementController.class.getName());

    private final LandMovementService landMovementService;
    private final LandMovementMapper landMovementMapper;
    private final FarmBovineService farmBovineService;
    private final LandService landService;

    @Autowired
    public LandMovementController(LandMovementService landMovementService, LandMovementMapper landMovementMapper, FarmBovineService farmBovineService, LandService landService) {
        this.landMovementService = landMovementService;
        this.landMovementMapper = landMovementMapper;
        this.farmBovineService = farmBovineService;
        this.landService = landService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LandMovementRES>> getAllLandMovements() {
        List<LandMovementRES> responseList = landMovementService.getAllLandMovements().stream()
                .map(landMovementMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<LandMovementRES> addLandMovement(@Valid @RequestBody LandMovementREQ request) {
        try {
            LandMovementDao landMovementDao = landMovementMapper.convert(request);

            //update farm bovine current land
            FarmBovineDao requestFarmBovineDao = landMovementDao.getFarmBovine();

            requestFarmBovineDao.setCurrentLand(landMovementDao.getDestinationLand());

            FarmBovineDao updatedFarmBovineDao = farmBovineService.updateFarmBovine(requestFarmBovineDao);

            if (updatedFarmBovineDao == null) {
                logger.severe("Couldn't update the farm bovine with the new land.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            LandMovementDao savedLandMovement = landMovementService.addLandMovement(landMovementDao);

            if(savedLandMovement != null) {
                LandMovementRES response = landMovementMapper.convert(landMovementDao);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

            else {
                logger.severe("Error creating a new land movement.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

        } catch (BovineNotFound | LandNotFoundException e) {
            logger.severe(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
