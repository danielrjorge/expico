package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.Bovine;
import com.drjenterprise.expico.services.BovineServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bovines")
public class BovineController {

    private final BovineServices bovineServices;

    @Autowired
    public BovineController(BovineServices bovineServices){
        this.bovineServices = bovineServices;
    }

    @GetMapping("/")
    public List<Bovine> getAllBovines(){
        return bovineServices.getAllEntities();
    }

    @GetMapping("/{id}")
    public Bovine getBovineById(@PathVariable("id") Integer id){
        return bovineServices.getEntityById(id);
    }

    @PostMapping("/")
    public Bovine saveBovine(@RequestBody Bovine bovine){
        return bovineServices.createEntity(bovine);
    }

    @PutMapping("/")
    public Bovine updateBovine(@RequestBody Bovine bovine){
        return bovineServices.updateEntity(bovine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBovine(@PathVariable("id") Integer id){
        return bovineServices.deleteEntity(id);
    }
}
