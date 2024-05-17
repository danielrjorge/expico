package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.bovines.BovineSaleDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BovineSaleRepository extends JpaRepository<BovineSaleDao, Integer> {
}
