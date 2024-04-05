package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.owner.OwnerREQ;
import com.drjenterprise.expico.entities.owner.OwnerRES;
import com.drjenterprise.expico.services.OwnerServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerServices ownerServices;

    @Autowired
    public OwnerController(OwnerServices ownerServices){
        this.ownerServices = ownerServices;
    }

    @GetMapping("/")
    public ResponseEntity<List<OwnerRES>> listAllOwners(){
        return new ResponseEntity<>(ownerServices.listAllOwners(), HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<OwnerDAO> getOwnerById(@PathVariable("id") Integer id){
        OwnerDAO existingOwnerDAO = ownerServices.getOwnerById(id);

        HttpStatus status = existingOwnerDAO == null ? HttpStatus.NOT_FOUND: HttpStatus.OK;

        return new ResponseEntity<>(existingOwnerDAO, status);
    }*/

    @GetMapping("/{nif}")
    public ResponseEntity<OwnerRES> getOwnerByNIF(@PathVariable("nif") Integer id) {
        OwnerRES existingOwnerRES = ownerServices.getOwnerByNIF(id);

        HttpStatus status = existingOwnerRES == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<>(existingOwnerRES, status);
    }

    @PostMapping("/")
    public ResponseEntity<OwnerRES> addOwner(@Valid @RequestBody OwnerREQ newOwnerREQ){

        OwnerRES addedOwnerRES = ownerServices.createOwner(newOwnerREQ);

        HttpStatus status = addedOwnerRES == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;

        return new ResponseEntity<>(addedOwnerRES, status);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateOwner(@Valid @RequestBody OwnerREQ updatedOwnerREQ){

        if(updatedOwnerREQ.getOwnerNIF() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The NIF was not specified in the body of the request");
        }

        OwnerRES ownerRES = ownerServices.updateOwner(updatedOwnerREQ);

        HttpStatus status = ownerRES == null ? HttpStatus.NOT_FOUND: HttpStatus.OK;

        return new ResponseEntity<>(ownerRES, status);
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
