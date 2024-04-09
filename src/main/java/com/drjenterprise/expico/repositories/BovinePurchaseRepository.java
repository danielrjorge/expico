package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.bovines.BovinePurchaseDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BovinePurchaseRepository extends JpaRepository<BovinePurchaseDao, Integer> {
}
