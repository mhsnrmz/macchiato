package com.mhsnrmz.macchiato.repository.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@javax.persistence.Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Order extends UniversalIdentifiableEntity {

    @OneToMany
    private Set<OrderItem> items;

    @ManyToOne
    private Table table;

    private BigDecimal totalPrice;
}
