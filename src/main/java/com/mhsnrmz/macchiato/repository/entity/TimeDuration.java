package com.mhsnrmz.macchiato.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@Data
public class TimeDuration {

    @Column(name = "start")
    private LocalTime start;

    @Column(name = "end")
    private LocalTime end;
}
