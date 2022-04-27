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

    /*    @OneToMany
        @JoinTable(name = "developers_in_projects",
                joinColumns = @JoinColumn(name = "group_fk"),
                inverseJoinColumns = @JoinColumn(name = "developer_fk"))
        private List<Developer> developers = new ArrayList<>();
    */
    @ManyToMany(mappedBy = "worksOnProjects")
    private List<Developer> developedByDevelopers = new ArrayList<>();


    public Project() {
    }

    public Project(String projectName, String projectDifficulty) {
        this.projectName = projectName;
        this.projectDifficulty = projectDifficulty;
    }

    public Project(String projectName, String projectDifficulty, List<Developer> developedByDevelopers) {
        this.projectName = projectName;
        this.projectDifficulty = projectDifficulty;
        this.developedByDevelopers = developedByDevelopers;
    }

    public void setDeveloper(Developer developer) {
        if (this.developedByDevelopers == null)
            this.developedByDevelopers = new ArrayList<>();

        if (!developedByDevelopers.contains(developer)) {
            developedByDevelopers.add(developer);
            developer.addProject(this);
        }
    }

    public List<Developer> getDevelopedByDevelopers() {
        return this.developedByDevelopers;
    }

    public void setDevelopedByDevelopers(List<Developer> developers) {
        this.developedByDevelopers = developedByDevelopers;
    }

    public void addDeveloper(Developer developer) {
        if(this.developedByDevelopers == null)
            this.developedByDevelopers = new ArrayList<>();

        if (!developedByDevelopers.contains(developer)) {
            developedByDevelopers.add(developer);
            developer.setProject(this);
        }
    }

    public List<Developer> getDevelopers() {
        return this.developedByDevelopers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developedByDevelopers = developers;
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
