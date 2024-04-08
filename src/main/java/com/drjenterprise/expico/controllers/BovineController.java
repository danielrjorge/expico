package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dto.request.BovineREQ;
import com.drjenterprise.expico.entities.dto.response.BovineRES;
import com.drjenterprise.expico.services.BovineServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bovines")
public class BovineController {

    private final BovineServices bovineServices;

    @Autowired
    public BovineController(BovineServices bovineServices){
        this.bovineServices = bovineServices;
    }

    @GetMapping("/")
    public ResponseEntity<List<BovineRES>> getAllBovines(){
        return new ResponseEntity<>(bovineServices.getAllBovines(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BovineRES> getBovineByInternalId(@PathVariable("id") Integer id){
        BovineRES existingBovineRES = bovineServices.getBovineByInternalId(id);

        HttpStatus status = existingBovineRES == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<>(existingBovineRES, status);
    }

    @GetMapping("/code/{bovineCode}")
    public ResponseEntity<BovineRES> getBovineByCode(@PathVariable("bovineCode") String bovineCode){
        BovineRES existingBovineRES = bovineServices.getBovineByCode(bovineCode);

        HttpStatus status = existingBovineRES == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<>(existingBovineRES, status);
    }

    @PostMapping("/")
    public ResponseEntity<BovineRES> addBovine(@Valid @RequestBody BovineREQ bovineREQ){

        BovineRES addedBovineRES = bovineServices.createBovine(bovineREQ);

        HttpStatus status = addedBovineRES == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;

        return new ResponseEntity<>(addedBovineRES, status);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateBovine(@RequestBody BovineREQ updatedBovineREQ){
        if(updatedBovineREQ.getBovineCode() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Bovine Code was not specified in the body of the request");
        }

        BovineRES bovineRES = bovineServices.updateBovine(updatedBovineREQ);

        HttpStatus status = bovineRES == null ? HttpStatus.NOT_FOUND: HttpStatus.OK;

        return new ResponseEntity<>(bovineRES, status);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteBovine(@PathVariable("id") Integer id){
        return bovineServices.deleteBovineByInternalId(id);
    }
}
