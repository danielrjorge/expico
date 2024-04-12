package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.bovines.BovineSaleDao;
import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import com.drjenterprise.expico.entities.dao.financial.VatAdjustmentDao;
import com.drjenterprise.expico.repositories.BovineSaleRepository;
import com.drjenterprise.expico.repositories.FinancialMovementRepository;
import com.drjenterprise.expico.repositories.VatAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BovineSaleService {

    private final BovineSaleRepository bovineSaleRepository;
    private final FinancialMovementRepository financialMovementRepository;
    private final VatAdjustmentRepository vatAdjustmentRepository;

    @Autowired
    public BovineSaleService(BovineSaleRepository bovineSaleRepository,
                                 FinancialMovementRepository financialMovementRepository,
                                 VatAdjustmentRepository vatAdjustmentRepository){
        this.bovineSaleRepository = bovineSaleRepository;
        this.financialMovementRepository = financialMovementRepository;
        this.vatAdjustmentRepository = vatAdjustmentRepository;
    }

    public List<BovineSaleDao> getAllBovineSales(){
        return bovineSaleRepository.findAll();
    }

    public BovineSaleDao createBovineSale(BovineSaleDao requestDao) {
        addFinancialMovementAndSaveVatAdjustment(requestDao);
        return bovineSaleRepository.save(requestDao);
    }

    private void addFinancialMovementAndSaveVatAdjustment(BovineSaleDao bovineSaleDao){
        FinancialMovementDao financialMovementDao = new FinancialMovementDao();
        financialMovementDao.setMovementDate(bovineSaleDao.getSaleDate());
        financialMovementDao.setMovementCredit(bovineSaleDao.getTotalPrice());
        FinancialMovementDao savedFinancialMovementDao = financialMovementRepository.save(financialMovementDao);

        VatAdjustmentDao vatAdjustmentDao = new VatAdjustmentDao();
        vatAdjustmentDao.setFinancialMovement(financialMovementDao);
        vatAdjustmentDao.setVatPercentage(bovineSaleDao.getVatPercentage());
        vatAdjustmentDao.setVatValueSale(calculateVatValueSale(bovineSaleDao));
        VatAdjustmentDao savedVatAdjustmentDao = vatAdjustmentRepository.save(vatAdjustmentDao);

        bovineSaleDao.setFinancialMovement(savedFinancialMovementDao);
    }

    private BigDecimal calculateVatValueSale(BovineSaleDao bovineSaleDao){
        BigDecimal totalPrice = bovineSaleDao.getTotalPrice();
        BigDecimal vat = bovineSaleDao.getVatPercentage();
        BigDecimal result = totalPrice.multiply(vat.divide(BigDecimal.valueOf(100)));
        return result;
    }

}
