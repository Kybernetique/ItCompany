package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.developer.model.Developer;
import ru.ulstu.is.sbapp.developer.service.DeveloperService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
class JpaDeveloperTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDeveloperTest.class);

    @Test
    void testDeveloperCreate() {
        developerService.deleteAllDevelopers();
        final Developer developer = developerService.addDeveloper("Иван", "Иванов");
        log.info(developer.toString());
        Assertions.assertNotNull(developer.getId());
    }

    @Autowired
    private DeveloperService developerService;

    @Test
    void testDeveloperRead() {
        developerService.deleteAllDevelopers();
        final Developer developer = developerService.addDeveloper("Иван", "Иванов");
        log.info(developer.toString());
        final Developer findDeveloper = developerService.findDeveloper(developer.getId());
        log.info(findDeveloper.toString());
        Assertions.assertEquals(developer, findDeveloper);
    }

    @Test
    void testDeveloperReadNotFound() {
        developerService.deleteAllDevelopers();
        Assertions.assertThrows(EntityNotFoundException.class, () -> developerService.findDeveloper(-1L));
    }

    @Test
    void testDeveloperReadAll() {
        developerService.deleteAllDevelopers();
        developerService.addDeveloper("Иван", "Иванов");
        developerService.addDeveloper("Петр", "Петров");
        final List<Developer> developers = developerService.findAllDevelopers();
        log.info(developers.toString());
        Assertions.assertEquals(developers.size(), 2);
    }

    @Test
    void testDeveloperReadAllEmpty() {
        developerService.deleteAllDevelopers();
        final List<Developer> developers = developerService.findAllDevelopers();
        log.info(developers.toString());
        Assertions.assertEquals(developers.size(), 0);
    }
}
