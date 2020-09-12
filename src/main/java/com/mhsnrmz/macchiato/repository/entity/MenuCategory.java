package com.mhsnrmz.macchiato.repository.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class MenuCategory extends UniversalIdentifiableEntity {
    private String name;
    private Boolean available;

    @AttributeOverride(name = "serverStartTime", column = @Column(name = "start"))
    @AttributeOverride(name = "serveEndTime", column = @Column(name = "end"))
    private TimeDuration serveTime;
}
