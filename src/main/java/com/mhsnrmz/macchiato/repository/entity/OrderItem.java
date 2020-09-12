package com.mhsnrmz.macchiato.repository.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class OrderItem extends UniversalIdentifiableEntity {

    @ManyToOne
    private Item item;
    private Integer count;
}
