package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.land.LandMovementDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandMovementRepository extends JpaRepository<LandMovementDao, Long> {
}
