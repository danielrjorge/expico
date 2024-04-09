package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.financial.FinancialMovementDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialMovementRepository extends JpaRepository<FinancialMovementDao, Integer> {
}
