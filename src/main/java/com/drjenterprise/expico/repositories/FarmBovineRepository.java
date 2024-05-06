package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.bovines.FarmBovineDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmBovineRepository extends JpaRepository<FarmBovineDao, Integer> {
    FarmBovineDao findByBovineBovineCode(String bovineCode);
    List<FarmBovineDao> findAllByCurrentLandLandCode(String landCode);
}
