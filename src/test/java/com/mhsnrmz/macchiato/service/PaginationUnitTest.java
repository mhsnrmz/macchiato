package com.mhsnrmz.macchiato.service;

import com.mhsnrmz.macchiato.service.pagination.Pagination;
import com.mhsnrmz.macchiato.service.pagination.SortDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUnitTest {

    @Test
    public void testUnsorted() {
        Pagination pagination = new Pagination();
        Sort sort = pagination.buildSortOrders("", null);
        Assertions.assertTrue(sort.isUnsorted());
    }

    @Test
    public void testUnsortedNullSortBy() {
        Pagination pagination = new Pagination();
        Sort sort = pagination.buildSortOrders(null, null);
        Assertions.assertTrue(sort.isUnsorted());
    }

    @Test
    public void testUnsortedWithSpaceSortBy() {
        Pagination pagination = new Pagination();
        Sort sort = pagination.buildSortOrders("            ", null);
        Assertions.assertTrue(sort.isUnsorted());
    }

    @Test
    public void testUnsortedWithDirection() {
        Pagination pagination = new Pagination();
        Sort sort = pagination.buildSortOrders("            ", SortDirection.ASC);
        Assertions.assertTrue(sort.isUnsorted());
    }

    @Test
    public void testAscSorted() {
        Pagination pagination = new Pagination();
        Sort sort = pagination.buildSortOrders("sampleField", SortDirection.ASC);
        Assertions.assertTrue(sort.isSorted());
    }

    @Test
    public void testNullDirection() {
        Pagination pagination = new Pagination();
        Sort sort = pagination.buildSortOrders("sampleField", SortDirection.DESC);
        Assertions.assertTrue(sort.isSorted());
    }

    @Test
    public void testDescSorted() {
        Pagination pagination = new Pagination();
        Sort sort = pagination.buildSortOrders("sampleField", SortDirection.DESC);
        Assertions.assertTrue(sort.isSorted());
    }

    @Test
    public void testPagination() {
        Pagination pagination = new Pagination();
        Pageable pageable = pagination.buildPageable(0, 10, null, null);
        Assertions.assertTrue(pageable.isPaged());
        Assertions.assertFalse(pageable.hasPrevious());
        Assertions.assertEquals(0L, pageable.getOffset());
        Assertions.assertEquals(0L, pageable.getPageNumber());
    }

    @Test
    public void testPaginationWithPrevious() {
        Pagination pagination = new Pagination();
        Pageable pageable = pagination.buildPageable(1, 10, null, null);
        Assertions.assertTrue(pageable.isPaged());
        Assertions.assertTrue(pageable.hasPrevious());
        Assertions.assertEquals(10L, pageable.getOffset());
        Assertions.assertEquals(1L, pageable.getPageNumber());
    }
}
