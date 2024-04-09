package com.drjenterprise.expico.controllers;

import com.drjenterprise.expico.entities.dto.request.BovinePurchaseREQ;
import com.drjenterprise.expico.entities.dto.response.BovinePurchaseRES;
import com.drjenterprise.expico.services.BovinePurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bovines/purchases")
public class BovinePurchaseController {

    @Autowired
    private BovinePurchaseService bovinePurchaseService;

    @GetMapping("/")
    public ResponseEntity<List<BovinePurchaseRES>> getAllBovinePurchases(){
        return new ResponseEntity<>(bovinePurchaseService.getAllBovinePurchases(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BovinePurchaseRES> addBovine(@Valid @RequestBody BovinePurchaseREQ bovinePurchaseREQ){

        BovinePurchaseRES addedBovinePurchaseRES = bovinePurchaseService.createBovinePurchase(bovinePurchaseREQ);

        HttpStatus status = addedBovinePurchaseRES == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;

        return new ResponseEntity<>(addedBovinePurchaseRES, status);
    }
}
