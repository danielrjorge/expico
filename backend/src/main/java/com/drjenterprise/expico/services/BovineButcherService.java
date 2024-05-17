package com.drjenterprise.expico.services;

import com.drjenterprise.expico.entities.dao.bovines.BovineButcherDao;
import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import com.drjenterprise.expico.entities.dao.financial.VatAdjustmentDao;
import com.drjenterprise.expico.entities.enums.MovementType;
import com.drjenterprise.expico.repositories.BovineButcherRepository;
import com.drjenterprise.expico.repositories.FinancialMovementRepository;
import com.drjenterprise.expico.repositories.VatAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BovineButcherService {

    private final BovineButcherRepository bovineButcherRepository;
    private final FinancialMovementRepository financialMovementRepository;
    private final VatAdjustmentRepository vatAdjustmentRepository;

    @Autowired
    public BovineButcherService(BovineButcherRepository bovineButcherRepository,
                                FinancialMovementRepository financialMovementRepository,
                                VatAdjustmentRepository vatAdjustmentRepository) {
        this.bovineButcherRepository = bovineButcherRepository;
        this.financialMovementRepository = financialMovementRepository;
        this.vatAdjustmentRepository = vatAdjustmentRepository;
    }

    public List<BovineButcherDao> getAllBovineButchers(){
        return bovineButcherRepository.findAll();
    }

    public BovineButcherDao getButcherByBovineCode(String bovineCode){
        return bovineButcherRepository.findByAssociatedBovineBovineCode(bovineCode);
    }

    public BovineButcherDao addBovineButcher(BovineButcherDao requestDao) {
        addFinancialMovementAndSaveVatAdjustment(requestDao);
        return bovineButcherRepository.save(requestDao);
    }

    private void addFinancialMovementAndSaveVatAdjustment(BovineButcherDao bovineButcherDao){
        FinancialMovementDao financialMovementDao = new FinancialMovementDao();
        financialMovementDao.setMovementDate(bovineButcherDao.getButcherDate());
        financialMovementDao.setMovementDebit(bovineButcherDao.getTotalPrice());
        financialMovementDao.setMovementType(MovementType.ABATE);
        FinancialMovementDao savedFinancialMovementDao = financialMovementRepository.save(financialMovementDao);

        VatAdjustmentDao vatAdjustmentDao = new VatAdjustmentDao();
        vatAdjustmentDao.setFinancialMovement(financialMovementDao);
        vatAdjustmentDao.setVatPercentage(bovineButcherDao.getVatPercentage());
        vatAdjustmentDao.setVatValuePurchase(calculateVatValuePurchase(bovineButcherDao));
        VatAdjustmentDao savedVatAdjustmentDao = vatAdjustmentRepository.save(vatAdjustmentDao);

        bovineButcherDao.setFinancialMovement(savedFinancialMovementDao);
    }

    private BigDecimal calculateVatValuePurchase(BovineButcherDao bovineButcherDao){
        BigDecimal totalPrice = bovineButcherDao.getTotalPrice();
        BigDecimal vat = bovineButcherDao.getVatPercentage();
        BigDecimal result = totalPrice.multiply(vat.divide(BigDecimal.valueOf(100)));
        return result;
    }
}
