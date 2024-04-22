package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.bovines.FarmBovineDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmBovineRepository extends JpaRepository<FarmBovineDao, Integer> {
    FarmBovineDao findByBovineBovineCode(String bovineCode);
}
