package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dto.request.bovines.BovineREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovineRES;
import com.drjenterprise.expico.exceptions.NifNotFoundException;
import com.drjenterprise.expico.services.BovineServices;
import com.drjenterprise.expico.services.mappers.BovineMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/bovines")
public class BovineController {

    private static final Logger logger = Logger.getLogger(BovineController.class.getName());

    private final BovineServices bovineServices;
    private final BovineMapper bovineMapper;

    @Autowired
    public BovineController(BovineServices bovineServices, BovineMapper bovineMapper){
        this.bovineServices = bovineServices;
        this.bovineMapper = bovineMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BovineRES>> getAllBovines(){

        List<BovineRES> responseList = bovineServices.getAllBovines().stream()
                .map(bovineMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/owned")
    public ResponseEntity<List<BovineRES>> getOwnedBovines(){
        List<BovineRES> responseList = bovineServices.getAllOwnedBovines().stream()
                .map(bovineMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BovineRES> getBovineByInternalId(@PathVariable("id") Integer id){
        BovineDAO existingBovineDao = bovineServices.getBovineByInternalId(id);
        if(existingBovineDao == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            BovineRES existingBovineRES = bovineMapper.convert(existingBovineDao);
            return new ResponseEntity<>(existingBovineRES, HttpStatus.OK);
        }
    }

    @GetMapping("/code/{bovineCode}")
    public ResponseEntity<BovineRES> getBovineByCode(@PathVariable("bovineCode") String bovineCode){
        BovineDAO existingBovineDao = bovineServices.getBovineByCode(bovineCode);
        if(existingBovineDao == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            BovineRES existingBovineRES = bovineMapper.convert(existingBovineDao);
            return new ResponseEntity<>(existingBovineRES, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BovineRES> addBovine(@Valid @RequestBody BovineREQ bovineREQ) {

        try {
            BovineDAO newBovineDAO = bovineMapper.convert(bovineREQ);
            BovineDAO addedBovineDao = bovineServices.createBovine(newBovineDAO);
            if(addedBovineDao != null) {
                BovineRES addedBovineResponse = bovineMapper.convert(addedBovineDao);
                return new ResponseEntity<>(addedBovineResponse, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (NifNotFoundException e) {
            logger.severe(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateBovine(@RequestBody BovineREQ updatedBovineREQ){
        if(updatedBovineREQ.getBovineCode() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Bovine Code was not specified in the body of the request");
        }

        try {
            BovineDAO existingBovineDao = bovineServices.getBovineByCode(updatedBovineREQ.getBovineCode());
            if (existingBovineDao == null) {
                logger.severe("Error trying to update a bovine that doesn't exist in the database.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            updatedBovineREQ.setBovineInternalId(existingBovineDao.getBovineInternalId());
            BovineDAO updatedBovineDAO = bovineServices.updateBovine(bovineMapper.convert(updatedBovineREQ));

            if(updatedBovineDAO != null) {
                BovineRES updatedBovineResponse = bovineMapper.convert(updatedBovineDAO);
                return new ResponseEntity<>(updatedBovineResponse, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (NifNotFoundException e) {
            logger.severe(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteBovine(@PathVariable("id") Integer id){
        return bovineServices.deleteBovineByInternalId(id);
    }
}
