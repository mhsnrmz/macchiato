package com.mhsnrmz.macchiato.repository;

import com.mhsnrmz.macchiato.repository.entity.MenuCategory;
import com.mhsnrmz.macchiato.repository.entity.TimeDuration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MenuCategoryRepositoryTest {

    @Autowired
    private MenuCategoryRepository repository;

    @Test
    public void whenMenuCategoryData_thenCreateNewMenuCategoryAndGet() {
        MenuCategory data = createSampleData();

        MenuCategory added = repository.save(data);

        MenuCategory foundEntity = repository.findById(added.getId()).orElseThrow(EntityExistsException::new);
        Assertions.assertEquals(added.getId(), foundEntity.getId());
    }

    private MenuCategory createSampleData() {
        MenuCategory category = new MenuCategory();
        category.setAvailable(true);
        category.setName("Breakfast");
        category.setServeTime(new TimeDuration(LocalTime.of(11, 30), LocalTime.of(8, 00)));
        return category;
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenGetMenuCategoryData_thenReturnNotFound() {
        repository.findById(10l).orElseThrow(EntityNotFoundException::new);
    }

    //TODO: add search test

    @Test
    public void whenUpdateMenuCategoryData_thenGetUpdatedData() {
        MenuCategory data = createSampleData();
        MenuCategory added = repository.save(data);
        final String name = "Lunch";
        added.setName(name);

        MenuCategory updated = repository.save(added);

        Assertions.assertEquals(added.getId(), updated.getId());
        Assertions.assertEquals(name, updated.getName());
    }

    @Test
    public void whenCreateMenuCategoryData_thenDeleteIt() {
        MenuCategory data = createSampleData();

        MenuCategory added = repository.save(data);

        repository.deleteById(added.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenCreateMenuCategoryData_thenGetAndThrowException() {
        MenuCategory data = createSampleData();

        MenuCategory added = repository.save(data);

        repository.deleteById(added.getId());
        repository.findById(added.getId()).orElseThrow(EntityNotFoundException::new);
    }
}
