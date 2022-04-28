package ru.ulstu.is.sbapp.developer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.developer.model.Company;
import ru.ulstu.is.sbapp.developer.model.Developer;
import ru.ulstu.is.sbapp.developer.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DeveloperService {
    @PersistenceContext
    private EntityManager em;


    @Transactional
    public Developer addDeveloper(String firstName, String lastName) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Developer name is null or empty");
        }
        final Developer developer = new Developer(firstName, lastName);
        em.persist(developer);
        return developer;
    }

    @Transactional(readOnly = true)
    public Developer findDeveloper(Long id) {
        final Developer developer = em.find(Developer.class, id);
        if (developer == null) {
            throw new EntityNotFoundException(String.format("Developer with id [%s] is not found", id));
        }
        return developer;
    }

    @Transactional(readOnly = true)
    public List<Developer> findAllDevelopers() {
        return em.createQuery("select d from Developer d", Developer.class).getResultList();
    }

    @Transactional
    public Developer setProject(Long id, List<Project> projects) {
        final Developer currentDeveloper = findDeveloper(id);
        currentDeveloper.setWorksOnProjects(projects);
        return em.merge(currentDeveloper);
    }

    @Transactional(readOnly = true)
    public Project findProject(Long id) {
        final Project project = em.find(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException(String.format("Project with id [%s] is not found", id));
        }
        return project;
    }

    @Transactional
    public int projectsCount(Long id) {
        final Developer currentDeveloper = findDeveloper(id);
        return currentDeveloper.projectsCount();
    }

    @Transactional
    public Developer updateDeveloper(Long id, String firstName, String lastName) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Developer name is null or empty");
        }
        final Developer currentDeveloper = findDeveloper(id);
        currentDeveloper.setFirstName(firstName);
        currentDeveloper.setLastName(lastName);
        return em.merge(currentDeveloper);
    }

    @Transactional
    public Developer deleteDeveloper(Long id) {
        final Developer currentDeveloper = findDeveloper(id);
        em.remove(currentDeveloper);
        return currentDeveloper;
    }

    @Transactional
    public void deleteAllDevelopers() {
        em.createQuery("delete from Developer").executeUpdate();
    }
}
