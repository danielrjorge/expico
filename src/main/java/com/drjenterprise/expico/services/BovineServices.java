package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.Bovine;
import com.drjenterprise.expico.repositories.BovineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BovineServices {
    private final BovineRepository bovineRepository;

    @Autowired
    public BovineServices(BovineRepository bovineRepository){
        this.bovineRepository = bovineRepository;
    }

    public List<Bovine> getAllEntities(){
        return bovineRepository.findAll();
    }

    public Bovine createEntity(Bovine bovine){
        return bovineRepository.save(bovine);
    }

    public Bovine updateEntity(Bovine bovine){
        return bovineRepository.save(bovine);
    }

    public ResponseEntity<Void> deleteEntity(Integer id){
        Bovine bovineToDelete = bovineRepository.findById(id).orElse(null);
        if (bovineToDelete != null){
            bovineRepository.delete(bovineToDelete);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    public Bovine getEntityById(int id){
        return bovineRepository.findById(id).orElse(null);
    }
}
