package com.mhsnrmz.macchiato.service;

import com.mhsnrmz.macchiato.repository.TableRepository;
import com.mhsnrmz.macchiato.repository.entity.Table;
import com.mhsnrmz.macchiato.service.pagination.Pagination;
import com.mhsnrmz.macchiato.service.pagination.SearchResultDto;
import com.mhsnrmz.macchiato.service.pagination.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TableServices {
    
    @Autowired
    TableRepository repository;
    
    @Autowired
    Pagination pagination;

    public Table add(Table table) {
        table.setId(null);
        return repository.save(table);
    }

    public Table get(final Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SearchResultDto<Table> getAll(final Specification<Table> specs, final int offset, final int limit,
                                         final String sortBy, final String direction) {
        Page<Table> result = repository.
                findAll(specs, pagination.buildPageable(offset - 1, limit, sortBy, SortDirection.fromString(direction)));
        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return new SearchResultDto<>(result.getContent(), result.getTotalElements(), result.getTotalPages());
    }

    public Table update(final Long id, Table table) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        table.setId(id);
        return repository.save(table);
    }

    public void delete(final Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}
