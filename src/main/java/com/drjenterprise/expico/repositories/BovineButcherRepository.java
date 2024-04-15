package com.drjenterprise.expico.repositories;

import com.drjenterprise.expico.entities.dao.bovines.BovineButcherDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BovineButcherRepository extends JpaRepository<BovineButcherDao, Integer> {
    BovineButcherDao findByAssociatedBovineBovineCode(String bovineCode);
}
