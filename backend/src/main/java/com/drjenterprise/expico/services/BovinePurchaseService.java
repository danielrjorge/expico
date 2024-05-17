package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.bovines.BovinePurchaseDao;
import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import com.drjenterprise.expico.entities.dao.financial.VatAdjustmentDao;
import com.drjenterprise.expico.entities.enums.MovementType;
import com.drjenterprise.expico.repositories.BovinePurchaseRepository;
import com.drjenterprise.expico.repositories.FinancialMovementRepository;
import com.drjenterprise.expico.repositories.VatAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BovinePurchaseService {

    private final BovinePurchaseRepository bovinePurchaseRepository;
    private final FinancialMovementRepository financialMovementRepository;
    private final VatAdjustmentRepository vatAdjustmentRepository;

    @Autowired
    public BovinePurchaseService(BovinePurchaseRepository bovinePurchaseRepository,
                                 FinancialMovementRepository financialMovementRepository,
                                 VatAdjustmentRepository vatAdjustmentRepository){
        this.bovinePurchaseRepository = bovinePurchaseRepository;
        this.financialMovementRepository = financialMovementRepository;
        this.vatAdjustmentRepository = vatAdjustmentRepository;
    }

    public List<BovinePurchaseDao> getAllBovinePurchases(){
        return bovinePurchaseRepository.findAll();
    }

    public BovinePurchaseDao createBovinePurchase(BovinePurchaseDao requestDao) {

        addFinancialMovementAndSaveVatAdjustment(requestDao);
        return bovinePurchaseRepository.save(requestDao);

    }

    private void addFinancialMovementAndSaveVatAdjustment(BovinePurchaseDao bovinePurchaseDao){
        FinancialMovementDao financialMovementDao = new FinancialMovementDao();
        financialMovementDao.setMovementDate(bovinePurchaseDao.getPurchaseDate());
        financialMovementDao.setMovementDebit(bovinePurchaseDao.getTotalPrice());
        financialMovementDao.setMovementType(MovementType.COMPRA);
        FinancialMovementDao savedFinancialMovementDao = financialMovementRepository.save(financialMovementDao);

        VatAdjustmentDao vatAdjustmentDao = new VatAdjustmentDao();
        vatAdjustmentDao.setFinancialMovement(financialMovementDao);
        vatAdjustmentDao.setVatPercentage(bovinePurchaseDao.getVatPercentage());
        vatAdjustmentDao.setVatValuePurchase(calculateVatValuePurchase(bovinePurchaseDao));
        VatAdjustmentDao savedVatAdjustmentDao = vatAdjustmentRepository.save(vatAdjustmentDao);

        bovinePurchaseDao.setFinancialMovement(savedFinancialMovementDao);
    }

    private BigDecimal calculateVatValuePurchase(BovinePurchaseDao bovinePurchaseDao){
        BigDecimal totalPrice = bovinePurchaseDao.getTotalPrice();
        BigDecimal vat = bovinePurchaseDao.getVatPercentage();
        BigDecimal result = totalPrice.multiply(vat.divide(BigDecimal.valueOf(100)));
        return result;
    }

}
