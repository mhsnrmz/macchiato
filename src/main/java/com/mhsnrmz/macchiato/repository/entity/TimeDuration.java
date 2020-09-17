package com.mhsnrmz.macchiato.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDuration {

    @Column(name = "start")
    private LocalTime start;

    @Column(name = "finish")
    private LocalTime finish;
}
