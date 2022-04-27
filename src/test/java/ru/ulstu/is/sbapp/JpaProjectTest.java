package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.developer.model.Project;
import ru.ulstu.is.sbapp.developer.service.ProjectService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaProjectTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDeveloperTest.class);
    @Autowired
    private ProjectService projectService;


    @Test
    void testProjectCreate() {
        projectService.deleteAllProjects();
        final Project project = projectService.addProject("Иван", "Иванов");
        log.info(project.toString());
        Assertions.assertNotNull(project.getId());
    }


    @Test
    void testProjectRead() {
        projectService.deleteAllProjects();
        final Project project = projectService.addProject("Иван", "Иванов");
        log.info(project.toString());
        final Project findDeveloper = projectService.findProject(project.getId());
        log.info(findDeveloper.toString());
        Assertions.assertEquals(project, findDeveloper);
    }

    @Test
    void testProjectReadNotFound() {
        projectService.deleteAllProjects();
        Assertions.assertThrows(EntityNotFoundException.class, () -> projectService.findProject(-1L));
    }

    @Test
    void testProjectReadAll() {
        projectService.deleteAllProjects();
        projectService.addProject("Иван", "Иванов");
        projectService.addProject("Петр", "Петров");
        final List<Project> projects = projectService.findAllProjects();
        log.info(projects.toString());
        Assertions.assertEquals(projects.size(), 2);
    }

    @Test
    void testProjectReadAllEmpty() {
        projectService.deleteAllProjects();
        final List<Project> developers = projectService.findAllProjects();
        log.info(developers.toString());
        Assertions.assertEquals(developers.size(), 0);
    }
}
