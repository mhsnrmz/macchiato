package com.mhsnrmz.macchiato.service.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Pagination {

    public Pageable buildPageable(int offset, int limit, String sortBy, SortDirection sortDirection) {
        return PageRequest.of(offset, limit, buildSortOrders(sortBy, sortDirection));
    }

    public Sort buildSortOrders(String sortBy, SortDirection sortDirection) {
        Sort sort;
        if (StringUtils.hasText(sortBy)) {
            sort = Sort.by(sortBy);
            if (sortDirection.equals(SortDirection.ASC)) {
                sort = sort.ascending();
            } else {
                sort = sort.descending();
            }
        } else {
            sort = Sort.unsorted();
        }
        return sort;
    }
}
