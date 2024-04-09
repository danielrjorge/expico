package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.bovines.BovinePurchaseDao;
import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import com.drjenterprise.expico.entities.dao.financial.VatAdjustmentDao;
import com.drjenterprise.expico.entities.dto.request.BovinePurchaseREQ;
import com.drjenterprise.expico.entities.dto.response.BovinePurchaseRES;
import com.drjenterprise.expico.entities.dto.response.BovineRES;
import com.drjenterprise.expico.exceptions.NifNotFoundException;
import com.drjenterprise.expico.repositories.BovinePurchaseRepository;
import com.drjenterprise.expico.repositories.FinancialMovementRepository;
import com.drjenterprise.expico.repositories.VatAdjustmentRepository;
import com.drjenterprise.expico.services.mappers.BovinePurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BovinePurchaseService {

    private static final Logger logger = Logger.getLogger(BovinePurchaseService.class.getName());
    private final BovinePurchaseRepository bovinePurchaseRepository;
    private final FinancialMovementRepository financialMovementRepository;
    private final VatAdjustmentRepository vatAdjustmentRepository;
    private final BovinePurchaseMapper bovinePurchaseMapper;
    private final BovineServices bovineServices;

    @Autowired
    public BovinePurchaseService(BovinePurchaseRepository bovinePurchaseRepository,
                                 FinancialMovementRepository financialMovementRepository,
                                 VatAdjustmentRepository vatAdjustmentRepository,
                                 BovinePurchaseMapper bovinePurchaseMapper,
                                 BovineServices bovineServices){
        this.bovinePurchaseRepository = bovinePurchaseRepository;
        this.financialMovementRepository = financialMovementRepository;
        this.vatAdjustmentRepository = vatAdjustmentRepository;
        this.bovinePurchaseMapper = bovinePurchaseMapper;
        this.bovineServices = bovineServices;
    }

    public List<BovinePurchaseRES> getAllBovinePurchases(){
        List<BovinePurchaseDao> bovinePurchaseDaoList = bovinePurchaseRepository.findAll();
        List<BovinePurchaseRES> bovinePurchaseRESList = new ArrayList<>();
        for(BovinePurchaseDao dao: bovinePurchaseDaoList){
            bovinePurchaseRESList.add(bovinePurchaseMapper.convert(dao));
        }
        return bovinePurchaseRESList;
    }

    public BovinePurchaseRES createBovinePurchase(BovinePurchaseREQ request) {

        try {
            BovineRES bovineRES = bovineServices.createBovine(request.getBovine());
            if(bovineRES != null){
                request.getBovine().setBovineInternalId(bovineRES.getBovineInternalId());
            }
            else {
                return null;
            }
            BovinePurchaseDao requestBovinePurchaseDao = bovinePurchaseMapper.convert(request);
            addFinancialMovementAndSaveVatAdjustment(requestBovinePurchaseDao);
            BovinePurchaseDao savedBovinePurchaseDao = bovinePurchaseRepository.save(requestBovinePurchaseDao);
            return bovinePurchaseMapper.convert(savedBovinePurchaseDao);
        } catch (NifNotFoundException e) {
            logger.severe(e.getMessage());
            return null;
        }

    }

    private void addFinancialMovementAndSaveVatAdjustment(BovinePurchaseDao bovinePurchaseDao){
        FinancialMovementDao financialMovementDao = new FinancialMovementDao();
        financialMovementDao.setMovementDate(bovinePurchaseDao.getPurchaseDate());
        financialMovementDao.setMovementCredit(bovinePurchaseDao.getTotalPrice());
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
