package com.mhsnrmz.macchiato.repository;

import com.mhsnrmz.macchiato.repository.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TableRepository extends JpaRepository<Table, Long>, JpaSpecificationExecutor<Table> {
}
