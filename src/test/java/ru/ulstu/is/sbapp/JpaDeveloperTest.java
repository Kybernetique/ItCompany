package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.developer.model.Company;
import ru.ulstu.is.sbapp.developer.model.Developer;
import ru.ulstu.is.sbapp.developer.model.Project;
import ru.ulstu.is.sbapp.developer.service.CompanyService;
import ru.ulstu.is.sbapp.developer.service.DeveloperService;
import ru.ulstu.is.sbapp.developer.service.ProjectService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
class JpaDeveloperTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDeveloperTest.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    DeveloperService developerService;

    @Autowired
    ProjectService projectService;

    // One-To-Many
    @Test
    void testDeveloperCreate() {
        companyService.deleteAllCompanies();
        developerService.deleteAllDevelopers();
        projectService.deleteAllProjects();

        Company company0 = companyService.addCompany("Oracle", "USA");
        Company company1 = companyService.addCompany("Microsoft", "USA");

        Developer developer0 = developerService.addDeveloper("John", "Doe");
        Developer developer1 = developerService.addDeveloper("Jane", "Doe");
        Developer developer2 = developerService.addDeveloper("John", "Roe");
        Developer developer3 = developerService.addDeveloper("Jane", "Roe");
        List<Developer> developers = developerService.findAllDevelopers();

        // One-To-Many
        companyService.setDeveloper(company0.getId(), developer0);
        companyService.setDeveloper(company0.getId(), developer1);
        companyService.setDeveloper(company1.getId(), developer2);
        companyService.setDeveloper(company1.getId(), developer3);

        Project project0 = projectService.addProject("Java Virtual Machine", "Very Hard");
        Project project1 = projectService.addProject("Python Interpreter", "Medium");
        Project project2 = projectService.addProject("Speech Recognizer", "Hard");
        Project project3 = projectService.addProject("C++ Compiler", "Hardcore");
        List<Project> projects = projectService.findAllProjects();

        // Many-To-Many
        developerService.setProject(developer0.getId(), projects.subList(0, 1));
        developerService.setProject(developer0.getId(), projects.subList(2, 3));
        projectService.setDeveloper(project2.getId(), developers.subList(0, 1));

        var developerFind1 = developerService.findDeveloper(developer1.getId());
        log.info(developer1.toString());
        log.info(developerFind1.toString());
        Assertions.assertEquals(developer1, developerFind1);

        var developerFind3 = developerService.findDeveloper(developer3.getId());
        log.info(developer3.toString());
        log.info(developer3.toString());
        Assertions.assertEquals(developer3, developerFind3);

        var companyFind1 = companyService.findCompany(company1.getId());
        log.info(company1.toString());
        log.info(companyFind1.toString());
        Assertions.assertEquals(company1, companyFind1);

        var projectFind1 = projectService.findProject(project1.getId());
        log.info(project1.toString());
        log.info(projectFind1.toString());
        Assertions.assertEquals(project1, projectFind1);

        log.info(project0.toString());
        log.info(project1.toString());
        log.info(project2.toString());
        log.info(project3.toString());
        Assertions.assertEquals(projectService.findAllProjects().size(), 4);
    }

/*    // Many-To-Many
    @Test
    void testProjectCreate() {
        companyService.deleteAllCompanies();
        developerService.deleteAllDevelopers();
        projectService.deleteAllProjects();

        Project project0 = projectService.addProject("Java Virtual Machine", "Very Hard");
        Project project1 = projectService.addProject("Python Interpreter", "Medium");
        Project project2 = projectService.addProject("Speech Recognizer", "Hard");
        Project project3 = projectService.addProject("C++ Compiler", "Hardcore");
        List<Project> projects = projectService.findAllProjects();

        Developer developer0 = developerService.addDeveloper("John", "Doe");
        Developer developer1 = developerService.addDeveloper("Jane", "Doe");
        Developer developer2 = developerService.addDeveloper("John", "Roe");
        Developer developer3 = developerService.addDeveloper("Jane", "Roe");
        List<Developer> developers = developerService.findAllDevelopers();

        Company company0 = companyService.addCompany("Sun Microsystems", "USA");
        Company company1 = companyService.addCompany("Bell Labs", "USA");

        developerService.setProject(developer0.getId(), project0);
        developerService.setProject(developer1.getId(), project1);
        developerService.setProject(developer2.getId(), project2);
        developerService.setProject(developer3.getId(), project3);

        log.info(project0.toString());
        log.info(project1.toString());
        log.info(project2.toString());
        log.info(project3.toString());
        Assertions.assertEquals(projects.size(), 4);

    }*/

    @Test
    void testDeveloperRead() {
        log.info("testDeveloperRead");
        developerService.deleteAllDevelopers();
        final Developer developer = developerService.addDeveloper("John", "Doe");
        log.info(developer.toString());
        final Developer findDeveloper = developerService.findDeveloper(developer.getId());
        log.info(findDeveloper.toString());
        Assertions.assertEquals(developer, findDeveloper);
    }

    @Test
    void testDeveloperReadNotFound() {
        log.info("testDeveloperReadNotFound");
        developerService.deleteAllDevelopers();
        Assertions.assertThrows(EntityNotFoundException.class, () -> developerService.findDeveloper(-1L));
    }

    @Test
    void testDeveloperReadAll() {
        log.info("testDeveloperReadAll");
        developerService.deleteAllDevelopers();
        developerService.addDeveloper("John", "Doe");
        developerService.addDeveloper("Jane", "Doe");
        final List<Developer> developers = developerService.findAllDevelopers();
        log.info(developers.toString());
        Assertions.assertEquals(developers.size(), 2);
    }

    @Test
    void testDeveloperReadAllEmpty() {
        log.info("testDeveloperReadAllEmpty");
        developerService.deleteAllDevelopers();
        final List<Developer> developers = developerService.findAllDevelopers();
        log.info(developers.toString());
        Assertions.assertEquals(developers.size(), 0);
    }
}
