package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import com.drjenterprise.expico.repositories.FinancialMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialMovementService {

    @Autowired
    private FinancialMovementRepository financialMovementRepository;

    public List<FinancialMovementDao> getAllMovements(){
        return financialMovementRepository.findAll();
    }

    public FinancialMovementDao saveMovement(FinancialMovementDao dao){
        return financialMovementRepository.save(dao);
    }

    public FinancialMovementDao getMovement(Integer id){
        return financialMovementRepository.findById(id).orElse(null);
    }
}
