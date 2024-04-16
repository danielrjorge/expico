package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dao.bovines.BovinePurchaseDao;
import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.dto.request.bovines.BovinePurchaseREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovinePurchaseRES;
import com.drjenterprise.expico.exceptions.NifNotFoundException;
import com.drjenterprise.expico.initializer.ProfileOwner;
import com.drjenterprise.expico.services.BovinePurchaseService;
import com.drjenterprise.expico.services.BovineServices;
import com.drjenterprise.expico.services.OwnerServices;
import com.drjenterprise.expico.services.mappers.BovineMapper;
import com.drjenterprise.expico.services.mappers.BovinePurchaseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/bovines/purchases")
public class BovinePurchaseController {

    private static final Logger logger = Logger.getLogger(BovinePurchaseController.class.getName());

    private final BovinePurchaseService bovinePurchaseService;
    private final BovinePurchaseMapper bovinePurchaseMapper;
    private final BovineServices bovineServices;
    private final BovineMapper bovineMapper;
    private final OwnerServices ownerServices;
    private final ProfileOwner profileOwner;

    @Autowired
    public BovinePurchaseController(BovinePurchaseService bovinePurchaseService,
                                    BovinePurchaseMapper bovinePurchaseMapper, BovineServices bovineServices, BovineMapper bovineMapper, OwnerServices ownerServices, ProfileOwner profileOwner) {
        this.bovinePurchaseService = bovinePurchaseService;
        this.bovinePurchaseMapper = bovinePurchaseMapper;
        this.bovineServices = bovineServices;
        this.bovineMapper = bovineMapper;
        this.ownerServices = ownerServices;
        this.profileOwner = profileOwner;
    }

    @GetMapping("/")
    public ResponseEntity<List<BovinePurchaseRES>> getAllBovinePurchases(){

        List<BovinePurchaseRES> responseList = bovinePurchaseService.getAllBovinePurchases().stream()
                .map(bovinePurchaseMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BovinePurchaseRES> addBovinePurchase(@Valid @RequestBody BovinePurchaseREQ bovinePurchaseREQ){

        try {
            BovinePurchaseDao newBovinePurchaseDao = bovinePurchaseMapper.convert(bovinePurchaseREQ);
            BovineDAO savedBovineDao = bovineServices.createBovine(newBovinePurchaseDao.getBovine());
            if(savedBovineDao == null) {
                logger.severe("There was an error creating a bovine from the bovine purchase request!");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            // Add the profile owner as the owner of the bought bovine and update the bovine
            OwnerDAO profileOwnerDao = ownerServices.getOwnerByNIF(profileOwner.getProfileOwnerNIF());
            savedBovineDao.setLastKnownOwner(profileOwnerDao);
            bovineServices.updateBovine(savedBovineDao);

            newBovinePurchaseDao.setBovine(savedBovineDao);

            BovinePurchaseDao addedBovinePurchaseRES = bovinePurchaseService.createBovinePurchase(newBovinePurchaseDao);
            if(addedBovinePurchaseRES != null){
                BovinePurchaseRES response = bovinePurchaseMapper.convert(addedBovinePurchaseRES);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (NifNotFoundException e) {
            logger.severe(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
