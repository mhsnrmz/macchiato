package com.mhsnrmz.macchiato.repository.entity;

public interface IdentifiableEntity<T> {
    T getId();
    void setId(T id);
}
