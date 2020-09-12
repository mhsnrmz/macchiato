package com.mhsnrmz.macchiato.service;

import com.mhsnrmz.macchiato.repository.ItemRepository;
import com.mhsnrmz.macchiato.repository.entity.Item;
import com.mhsnrmz.macchiato.service.pagination.Pagination;
import com.mhsnrmz.macchiato.service.pagination.SearchResultDto;
import com.mhsnrmz.macchiato.service.pagination.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MenuItemServices {

    @Autowired
    ItemRepository repository;

    @Autowired
    Pagination pagination;

    public Item add(Item item) {
        item.setId(null);
        return repository.save(item);
    }

    public Item get(final Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SearchResultDto<Item> getAll(final Specification<Item> specs, int offset, int limit, String sortBy,
                                        String direction) {
        Page<Item> result = repository.
                findAll(specs, pagination.buildPageable(offset - 1, limit, sortBy, SortDirection.fromString(direction)));
        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return new SearchResultDto<>(result.getContent(), result.getTotalElements(), result.getTotalPages());
    }

    public Item update (final Long id, Item item) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        item.setId(id);
        return repository.save(item);
    }

    public void delete (final Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}
