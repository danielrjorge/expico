package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.bovines.BovineButcherDao;
import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dto.request.bovines.BovineButcherREQ;
import com.drjenterprise.expico.entities.dto.response.bovines.BovineButcherRES;
import com.drjenterprise.expico.entities.enums.BovineStatus;
import com.drjenterprise.expico.services.BovineButcherService;
import com.drjenterprise.expico.services.BovineServices;
import com.drjenterprise.expico.services.FarmBovineService;
import com.drjenterprise.expico.services.mappers.BovineButcherMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/bovines/butchers")
public class BovineButcherController {

    private static final Logger logger = Logger.getLogger(BovineButcherController.class.getName());

    private final BovineButcherService bovineButcherService;
    private final BovineButcherMapper bovineButcherMapper;
    private final BovineServices bovineServices;
    private final FarmBovineService farmBovineService;

    @Autowired
    public BovineButcherController(BovineButcherService bovineButcherService, BovineButcherMapper bovineButcherMapper, BovineServices bovineServices, FarmBovineService farmBovineService) {
        this.bovineButcherService = bovineButcherService;
        this.bovineButcherMapper = bovineButcherMapper;
        this.bovineServices = bovineServices;
        this.farmBovineService = farmBovineService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BovineButcherRES>> getAllBovineButchers(){
        List<BovineButcherRES> responseList = bovineButcherService.getAllBovineButchers().stream()
                .map(bovineButcherMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BovineButcherRES> addBovineButcher(@Valid @RequestBody BovineButcherREQ request) {
        BovineDAO requestBovineDao = bovineServices.getBovineByCode(request.getBovineCode());
        BovineButcherDao existingButcherDao = bovineButcherService.getButcherByBovineCode(request.getBovineCode());

        if (requestBovineDao == null) {
            logger.severe("The bovine that is being butchered does not exist in the Database.");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else if(existingButcherDao != null) {
            logger.severe("The bovine in the request was already butchered on " + existingButcherDao.getButcherDate());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            //update the bovine status
            updateBovineStatusAndRemoveFromFarm(requestBovineDao);

            BovineButcherDao newButcherDao = bovineButcherMapper.convert(request);
            //set the bovine to the Dao, done outside the mapper.
            newButcherDao.setAssociatedBovine(requestBovineDao);

            BovineButcherDao addedButcherDao = bovineButcherService.addBovineButcher(newButcherDao);
            if(addedButcherDao != null) {
                BovineButcherRES response = bovineButcherMapper.convert(addedButcherDao);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
    }

    private void updateBovineStatusAndRemoveFromFarm(BovineDAO bovineDao) {
        bovineDao.setBovineStatus(BovineStatus.ABATIDO);
        BovineDAO updatedBovine = bovineServices.updateBovine(bovineDao);

        // Remove from the farm bovines
        farmBovineService.deleteFarmBovine(updatedBovine);
    }
}
