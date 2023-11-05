package ru.ulstu.is.sbapp.developer.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String projectName;
    private String projectDifficulty;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "projects_developers",
            joinColumns = @JoinColumn(name = "project_fk"),
            inverseJoinColumns = @JoinColumn(name = "developer_fk"))
    private List<Developer> developers = new ArrayList<>();


    public Project() {
    }

    public Project(String projectName, String projectDifficulty) {
        this.projectName = projectName;
        this.projectDifficulty = projectDifficulty;
    }

    public Project(String projectName, String projectDifficulty, List<Developer> developers) {
        this.projectName = projectName;
        this.projectDifficulty = projectDifficulty;
        this.developers = developers;
    }

    public void setDeveloper(Developer developer) {
        if (this.developers == null)
            this.developers = new ArrayList<>();

        if (!developers.contains(developer)) {
            developers.add(developer);
            developer.addProject(this);
        }
    }

    public int developersCount() {
        return developers.size();
    }

    public List<Developer> getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(List<Developer> developers) {
        if (this.developers == null)
            this.developers = new ArrayList<>();

        for (Developer dev : developers)
            setDeveloper(dev);
    }


    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDifficulty() {
        return projectDifficulty;
    }

    public void setProjectDifficulty(String projectDifficulty) {
        this.projectDifficulty = projectDifficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project Project = (Project) o;
        return Objects.equals(id, Project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", projectName='" + projectName + '\'' + ", projectDifficulty='" + projectDifficulty + '\'' + '}';
    }

    public void removeAllDevelopers() {
    }

}
