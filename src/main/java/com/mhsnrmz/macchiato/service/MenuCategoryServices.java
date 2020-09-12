package com.mhsnrmz.macchiato.service;

import com.mhsnrmz.macchiato.repository.MenuCategoryRepository;
import com.mhsnrmz.macchiato.repository.entity.MenuCategory;
import com.mhsnrmz.macchiato.service.pagination.Pagination;
import com.mhsnrmz.macchiato.service.pagination.SearchResultDto;
import com.mhsnrmz.macchiato.service.pagination.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class MenuCategoryServices {

    @Autowired
    MenuCategoryRepository repository;

    @Autowired
    Pagination pagination;

    public MenuCategory add(MenuCategory menuCategory) {
        menuCategory.setId(null);
        return repository.save(menuCategory);
    }

    public MenuCategory get(final Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SearchResultDto<MenuCategory> getAll(final Specification<MenuCategory> specs, int offset, int limit,
                                                String sortBy, String direction) {
        Page<MenuCategory> result = repository.
                findAll(specs, pagination.buildPageable(offset - 1, limit, sortBy, SortDirection.fromString(direction)));
        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return new SearchResultDto<>(result.getContent(), result.getTotalElements(), result.getTotalPages());
    }

    public MenuCategory update (final Long id, MenuCategory menuCategory) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        menuCategory.setId(id);
        return repository.save(menuCategory);
    }

    public void delete (final Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}
