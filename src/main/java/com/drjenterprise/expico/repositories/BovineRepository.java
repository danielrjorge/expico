package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.Bovine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BovineRepository extends JpaRepository<Bovine, Integer> {
}
