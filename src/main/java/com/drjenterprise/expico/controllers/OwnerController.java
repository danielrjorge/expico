package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import com.drjenterprise.expico.entities.dto.request.owner.OwnerREQ;
import com.drjenterprise.expico.entities.dto.response.owner.OwnerRES;
import com.drjenterprise.expico.services.OwnerServices;
import com.drjenterprise.expico.services.mappers.OwnerMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerServices ownerServices;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerController(OwnerServices ownerServices, OwnerMapper ownerMapper){
        this.ownerServices = ownerServices;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<OwnerRES>> listAllOwners(){
        List<OwnerRES> responseList = ownerServices.getAllOwners().stream()
                .map(ownerMapper::convert)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OwnerRES> getOwnerById(@PathVariable("id") Integer id){
        OwnerDAO existingOwnerDao = ownerServices.getOwnerById(id);

        if (existingOwnerDao != null) {
            OwnerRES response = ownerMapper.convert(existingOwnerDao);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nif/{nif}")
    public ResponseEntity<OwnerRES> getOwnerByNIF(@PathVariable("nif") Integer nif) {
        OwnerDAO existingOwnerDao = ownerServices.getOwnerByNIF(nif);

        if (existingOwnerDao != null) {
            OwnerRES response = ownerMapper.convert(existingOwnerDao);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<OwnerRES> addOwner(@Valid @RequestBody OwnerREQ newOwnerREQ){

        OwnerDAO requestOwnerDao = ownerMapper.convert(newOwnerREQ);
        OwnerDAO addedOwnerDao = ownerServices.createOwner(requestOwnerDao);
        if (addedOwnerDao != null) {
            OwnerRES response = ownerMapper.convert(addedOwnerDao);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/")
    public ResponseEntity<?> updateOwner(@Valid @RequestBody OwnerREQ updatedOwnerREQ){

        if(updatedOwnerREQ.getOwnerNIF() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The NIF was not specified in the body of the request");
        }
        OwnerDAO requestOwnerDao = ownerMapper.convert(updatedOwnerREQ);
        OwnerDAO updatedOwnerDao = ownerServices.updateOwner(requestOwnerDao);

        if (updatedOwnerDao != null) {
            OwnerRES response = ownerMapper.convert(updatedOwnerDao);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{nif}")
    public ResponseEntity<Void> deleteOwnerByNIF(@PathVariable("nif") Integer nif){
        if(ownerServices.deleteOwnerByNIF(nif)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
