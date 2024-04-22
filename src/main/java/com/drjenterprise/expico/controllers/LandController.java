package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.land.LandDao;
import com.drjenterprise.expico.entities.dto.request.land.LandREQ;
import com.drjenterprise.expico.entities.dto.response.land.LandRES;
import com.drjenterprise.expico.services.LandService;
import com.drjenterprise.expico.services.mappers.LandMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/land")
public class LandController {
    private static final Logger logger = Logger.getLogger(LandController.class.getName());
    private final LandService landService;
    private final LandMapper landMapper;

    @Autowired
    public LandController(LandService landService, LandMapper landMapper) {
        this.landService = landService;
        this.landMapper = landMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<LandRES>> getAllLand() {

        List<LandRES> responseList = landService.getAllLands().stream()
                .map(landMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<LandRES> getLandByCode(@PathVariable("code") String code) {
        LandDao existingLandDao = landService.getLandByCode(code);

        if (existingLandDao != null) {
            LandRES response = landMapper.convert(existingLandDao);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<LandRES> addLand(@Valid @RequestBody LandREQ landREQ) {
        LandDao landToAddDao = landMapper.convert(landREQ);
        LandDao savedLandDao = landService.addLand(landToAddDao);

        if(savedLandDao != null) {
            LandRES response = landMapper.convert(savedLandDao);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<LandRES> updateLand(@Valid @RequestBody LandREQ landREQ) {
        LandDao landWithUpdateDao = landMapper.convert(landREQ);
        LandDao updatedLandDao = landService.updateLand(landWithUpdateDao);

        if (updatedLandDao != null) {
            LandRES response = landMapper.convert(updatedLandDao);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteLandByCode(@PathVariable("code") String code) {
        return landService.deleteLandByCode(code);
    }

}
