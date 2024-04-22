package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dao.bovines.BovineSaleDao;
import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.dto.request.bovines.BovineSaleREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovineSaleRES;
import com.drjenterprise.expico.entities.enums.BovineStatus;
import com.drjenterprise.expico.initializer.ProfileOwner;
import com.drjenterprise.expico.services.BovineSaleService;
import com.drjenterprise.expico.services.BovineServices;
import com.drjenterprise.expico.services.FarmBovineService;
import com.drjenterprise.expico.services.OwnerServices;
import com.drjenterprise.expico.services.mappers.BovineSaleMapper;
import com.drjenterprise.expico.services.mappers.OwnerMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/bovines/sales")
public class BovineSaleController {

    private static final Logger logger = Logger.getLogger(BovineSaleController.class.getName());
    private final BovineSaleService bovineSaleService;
    private final BovineSaleMapper bovineSaleMapper;
    private final BovineServices bovineServices;
    private final FarmBovineService farmBovineService;
    private final OwnerServices ownerServices;
    private final OwnerMapper ownerMapper;
    private final ProfileOwner profileOwner;

    @Autowired
    public BovineSaleController(BovineSaleService bovineSaleService, BovineSaleMapper bovineSaleMapper,
                                BovineServices bovineServices, FarmBovineService farmBovineService, OwnerServices ownerServices, OwnerMapper ownerMapper, ProfileOwner profileOwner) {
        this.bovineSaleService = bovineSaleService;
        this.bovineSaleMapper = bovineSaleMapper;
        this.bovineServices = bovineServices;
        this.farmBovineService = farmBovineService;
        this.ownerServices = ownerServices;
        this.ownerMapper = ownerMapper;
        this.profileOwner = profileOwner;
    }

    @GetMapping("/")
    public ResponseEntity<List<BovineSaleRES>> getAllBovineSales(){

        List<BovineSaleRES> responseList = bovineSaleService.getAllBovineSales().stream()
                .map(bovineSaleMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BovineSaleRES> addBovineSale(@Valid @RequestBody BovineSaleREQ bovineSaleREQ){

        BovineDAO requestBovineDao = bovineServices.getBovineByCode(bovineSaleREQ.getBovineCode());

        if (requestBovineDao == null) {
            logger.severe("The bovine that is being sold does not exist in the Database.");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else if (requestBovineDao.getLastKnownOwner().getOwnerNIF() != profileOwner.getProfileOwnerNIF()){
            logger.severe("The bovine that is being sold does not belong to the profile owner!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            //update the bovine status
            updateBovineStatus(requestBovineDao);

            //update the bovine owner
            BovineDAO updateBovineDao = updateBovineOwner(requestBovineDao, bovineSaleREQ);

            BovineSaleDao newBovineDao = bovineSaleMapper.convert(bovineSaleREQ);
            //set the bovine to the Dao, done outside the mapper.
            newBovineDao.setBovine(requestBovineDao);

            BovineSaleDao addedBovineDao = bovineSaleService.createBovineSale(newBovineDao);
            if(addedBovineDao != null) {
                // remove bovine from the farm bovines
                farmBovineService.deleteFarmBovine(updateBovineDao);

                BovineSaleRES response = bovineSaleMapper.convert(addedBovineDao);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

    }

    private void updateBovineStatus(BovineDAO bovineDao) {
        bovineDao.setBovineStatus(BovineStatus.VENDIDO);
        bovineServices.updateBovine(bovineDao);
    }

    /**
     * Updates the bovine owner according to the NIF in the request. If no owner with this NIF exists in the database,
     * create one.
     * @param bovineDAO
     * @param saleREQ
     */
    private BovineDAO updateBovineOwner(BovineDAO bovineDAO, BovineSaleREQ saleREQ) {
        OwnerDAO convertedOwnerDao = ownerMapper.convert(saleREQ.getBuyer());
        OwnerDAO existingOwnerDao = ownerServices.getOwnerByNIF(convertedOwnerDao.getOwnerNIF());
        // might want in the future to check if owner comes in with more fields than are stored, update the owner
        if (existingOwnerDao != null) {
            bovineDAO.setLastKnownOwner(existingOwnerDao);
        } else {
            OwnerDAO newOwnerDao = ownerServices.createOwner(convertedOwnerDao);
            bovineDAO.setLastKnownOwner(newOwnerDao);
        }
        return bovineServices.updateBovine(bovineDAO);
    }
}
