package ru.ulstu.is.sbapp.developer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String projectName;
    private String projectDifficulty;

    @OneToMany
    @JoinTable(name = "developers_in_projects",
            joinColumns = @JoinColumn(name = "group_fk"),
            inverseJoinColumns = @JoinColumn(name = "developer_fk"))
    private List<Developer> developers = new ArrayList<>();

    @ManyToMany(mappedBy = "worksOnProjects")
    private List<Company> developedByDevelopers = new ArrayList<>();

    public Project() {
    }

    public List<Company> getDevelopedByDevelopers() {
        return this.developedByDevelopers;
    }

    public void setDevelopedByDevelopers(List<Developer> developers) {
        this.developedByDevelopers = developedByDevelopers;
    }

    public List<Developer> getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public Project(String projectName, String projectDifficulty) {
        this.projectName = projectName;
        this.projectDifficulty = projectDifficulty;
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
