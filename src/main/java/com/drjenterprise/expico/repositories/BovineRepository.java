package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.BovineDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BovineRepository extends JpaRepository<BovineDAO, Integer> {
    Optional<BovineDAO> findByBovineCode(String code);
}
