package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.owner.OwnerDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerDAO, Integer> {
    Optional<OwnerDAO> findByOwnerNIF(int nif);
}
