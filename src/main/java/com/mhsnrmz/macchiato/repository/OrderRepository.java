package com.mhsnrmz.macchiato.repository;

import com.mhsnrmz.macchiato.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
