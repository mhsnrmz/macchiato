package com.mhsnrmz.macchiato.repository;

import com.mhsnrmz.macchiato.repository.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long>, JpaSpecificationExecutor<MenuCategory> {
}
