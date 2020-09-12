package com.mhsnrmz.macchiato.repository.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@MappedSuperclass
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Order extends UniversalIdentifiableEntity {

    @OneToMany
    private List<OrderItem> items;

    @ManyToOne
    private Table table;

    private BigDecimal totalPrice;
}