package com.mhsnrmz.macchiato.repository.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Item extends UniversalIdentifiableEntity {
    private String itemName;
    private BigDecimal price;
    private LocalTime serveTime;
    private Boolean available;

    @Lob
    private String material;

    @ManyToOne
    private MenuCategory category;
}
