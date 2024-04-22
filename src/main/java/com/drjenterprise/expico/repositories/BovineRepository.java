package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.bovines.BovineDAO;
import com.drjenterprise.expico.entities.dao.owner.OwnerDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BovineRepository extends JpaRepository<BovineDAO, Integer> {
    Optional<BovineDAO> findByBovineCode(String code);
    Optional<List<BovineDAO>> findAllByLastKnownOwner(OwnerDAO owner);
}
