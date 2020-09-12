package com.mhsnrmz.macchiato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface ItemRepository extends JpaRepository<MenuItem, Long> {
}
