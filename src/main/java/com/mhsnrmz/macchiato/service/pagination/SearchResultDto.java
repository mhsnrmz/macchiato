package com.mhsnrmz.macchiato.service.pagination;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchResultDto<T> {
    List<T> items;
    private long totalCount;
    private int pageCount;

    public SearchResultDto(List<T> items, long totalCount, int pageCount) {
        this.items = items;
        this.totalCount = totalCount;
        this.pageCount = pageCount;
    }
}
