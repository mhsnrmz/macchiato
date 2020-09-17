package com.mhsnrmz.macchiato.service;

import com.mhsnrmz.macchiato.repository.MenuCategoryRepository;
import com.mhsnrmz.macchiato.repository.entity.MenuCategory;
import com.mhsnrmz.macchiato.repository.entity.TimeDuration;
import com.mhsnrmz.macchiato.service.pagination.Pagination;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MenuCategoryServicesTest {

    private static final long VALID_ID = 1L;
    private static final long INVALID_ID = 100L;

    @TestConfiguration
    static class MenuCategoryServicesTestContextConfiguration {
        @Bean
        public MenuCategoryServices menuCategoryServices() {
            return new MenuCategoryServices();
        }
    }

    @Autowired
    private MenuCategoryServices menuCategoryServices;

    @MockBean
    private MenuCategoryRepository repository;

    @MockBean
    private Pagination pagination;

    @Before
    public void setUp() {
        MenuCategory existingEntity = new MenuCategory();
        existingEntity.setId(1L);
        existingEntity.setAvailable(true);
        existingEntity.setName("Breakfast");
        existingEntity.setServeTime(new TimeDuration(LocalTime.of(11, 30), LocalTime.of(8, 00)));

        when(repository.findById(VALID_ID)).thenReturn(Optional.of(existingEntity));
        when(repository.findById(INVALID_ID)).thenThrow(EntityNotFoundException.class);
        when(repository.existsById(VALID_ID)).thenReturn(true);
        when(repository.existsById(INVALID_ID)).thenReturn(false);
        when(repository.save(Mockito.any(MenuCategory.class))).thenReturn(existingEntity);
    }

    @Test
    public void whenValidId_thenMenuCategoryServiceShouldBeFound() {
        MenuCategory found = menuCategoryServices.get(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertNotNull(found.getName());
    }

    @Test
    public void whenAddNewMenuCategory_thenMenuCategoryServiceShouldReturnAndBeFound() {
        MenuCategory saved = menuCategoryServices.add(new MenuCategory());
        assertNotNull(saved);
        assertNotNull(saved.getId());
        MenuCategory found = menuCategoryServices.get(saved.getId());
        assertNotNull(found);
    }

    //TODO: add test for search after developing

    @Test
    public void whenUpdateMenuCategory_thenMenuCategoryServiceShouldReturnUpdatedEntity() {
        final String name = "Dinner";
        MenuCategory updating = menuCategoryServices.get(VALID_ID);
        updating.setName(name);
        MenuCategory updated = menuCategoryServices.update(VALID_ID, updating);
        assertNotNull(updated);
        assertEquals(VALID_ID, updated.getId());
        assertEquals(name, updated.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenUpdateInvalidMenuCategoryId_thenMenuCategoryServiceShouldThrowException() {
        menuCategoryServices.update(INVALID_ID, new MenuCategory());
    }

    @Test
    public void whenDeleteMenuCategory_thenMenuCategoryServiceShouldDeleteEntity() {
        menuCategoryServices.delete(VALID_ID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenDeleteInvalidMenuCategoryId_thenMenuCategoryServiceShouldThrowException() {
        menuCategoryServices.delete(INVALID_ID);
    }
}
