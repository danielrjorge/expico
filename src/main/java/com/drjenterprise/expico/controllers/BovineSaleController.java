package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dao.bovines.BovineSaleDao;
import com.drjenterprise.expico.entities.dto.request.bovines.BovineSaleREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovineSaleRES;
import com.drjenterprise.expico.exceptions.NifNotFoundException;
import com.drjenterprise.expico.services.BovineSaleService;
import com.drjenterprise.expico.services.BovineServices;
import com.drjenterprise.expico.services.mappers.BovineSaleMapper;
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

    @Autowired
    public BovineSaleController(BovineSaleService bovineSaleService, BovineSaleMapper bovineSaleMapper,
                                BovineServices bovineServices) {
        this.bovineSaleService = bovineSaleService;
        this.bovineSaleMapper = bovineSaleMapper;
        this.bovineServices = bovineServices;
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
        }
        else {
            try {
                BovineSaleDao newBovineDao = bovineSaleMapper.convert(bovineSaleREQ);
                //set the bovine to the Dao, done outside the mapper.
                newBovineDao.setBovine(requestBovineDao);

                BovineSaleDao addedBovineDao = bovineSaleService.createBovineSale(newBovineDao);
                if(addedBovineDao != null) {
                    BovineSaleRES response = bovineSaleMapper.convert(addedBovineDao);
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                else {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            } catch (NifNotFoundException e) {
                logger.severe(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

    }
}
