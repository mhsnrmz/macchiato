package com.mhsnrmz.macchiato.service.pagination;

import org.springframework.util.StringUtils;

public enum SortDirection {
    ASC, DESC;

    public static SortDirection fromString(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        for (SortDirection sortDirection : SortDirection.values()) {
            if (sortDirection.name().equalsIgnoreCase(value)) {
                return sortDirection;
            }
        }
        throw new IllegalArgumentException("Invalid sort direction.");
    }
}
