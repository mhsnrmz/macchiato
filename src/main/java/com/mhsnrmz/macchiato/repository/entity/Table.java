package com.mhsnrmz.macchiato.repository.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Table extends UniversalIdentifiableEntity {
    private Boolean empty;
    private Boolean reserved;
}
