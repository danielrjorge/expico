package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.land.LandDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandRepository extends JpaRepository<LandDao, Integer> {
    Optional<LandDao> findByLandCode(String code);
}
