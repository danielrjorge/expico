package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.financial.VatAdjustmentDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VatAdjustmentRepository extends JpaRepository<VatAdjustmentDao, Integer> {
}
