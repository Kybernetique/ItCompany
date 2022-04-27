package ru.ulstu.is.sbapp.developer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.developer.model.Developer;
import ru.ulstu.is.sbapp.developer.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProjectService
{
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Project addProject(String projectName, String projectDifficulty) {
        if (!StringUtils.hasText(projectName) || !StringUtils.hasText(projectDifficulty)) {
            throw new IllegalArgumentException("Project name is null or empty");
        }
        final Project project = new Project(projectName, projectDifficulty);
        em.persist(project);
        return project;
    }

    @Transactional(readOnly = true)
    public Project findProject(Long id) {
        final Project project = em.find(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException(String.format("Project with id [%s] is not found", id));
        }
        return project;
    }

    @Transactional(readOnly = true)
    public List<Project> findAllProjects() {
        return em.createQuery("select p from Project p", Project.class)
                .getResultList();
    }

    @Transactional
    public Project updateProject(Long id, String projectName, String projectDifficulty) {
        if (!StringUtils.hasText(projectName) || !StringUtils.hasText(projectDifficulty)) {
            throw new IllegalArgumentException("Project name is null or empty");
        }
        final Project currentProject = findProject(id);
        currentProject.setProjectName(projectName);
        currentProject.setProjectDifficulty(projectDifficulty);
        return em.merge(currentProject);
    }

    @Transactional
    public Project deleteProject(Long id) {
        final Project currentProject = findProject(id);
        currentProject.removeAllDevelopers();
        em.persist(currentProject);
        em.remove(currentProject);
        return currentProject;
    }

    public Project setDeveloper(Long id, List<Developer> developers) {
        final Project currentProject = findProject(id);
        currentProject.setDevelopedByDevelopers(developers);
        return em.merge(currentProject);
    }

    @Transactional
    public void deleteAllProjects() {
        em.createQuery("delete from Project").executeUpdate();
    }
}
