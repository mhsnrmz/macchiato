package com.mhsnrmz.macchiato.service;

import com.mhsnrmz.macchiato.repository.OrderRepository;
import com.mhsnrmz.macchiato.repository.entity.Order;
import com.mhsnrmz.macchiato.service.pagination.Pagination;
import com.mhsnrmz.macchiato.service.pagination.SearchResultDto;
import com.mhsnrmz.macchiato.service.pagination.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    Pagination pagination;

    public Order add(Order order) {
        return repository.save(order);
    }

    public Order get(final Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SearchResultDto<Order> getAll(final Specification<Order> specs, final int offset, final int limit,
                                         final String sortBy, final String direction) {
        Page<Order> result = repository.
                findAll(specs, pagination.buildPageable(offset - 1, limit, sortBy, SortDirection.fromString(direction)));
        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return new SearchResultDto<>(result.getContent(), result.getTotalElements(), result.getTotalPages());
    }

    public Order update(final Long id, Order order) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        order.setId(id);
        return repository.save(order);
    }

    public void delete(final Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}
