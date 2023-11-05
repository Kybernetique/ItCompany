package ru.ulstu.is.sbapp.developer.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne()
    @JoinColumn(name = "company_fk")
    private Company company;

    @ManyToMany(mappedBy = "developers")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Project> projects = new ArrayList<>();


    public Developer() {
    }

    public int projectsCount() {
        return projects.size();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Project> setProjects(List<Project> worksOnProjects) {
        return this.projects = worksOnProjects;
    }


    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Developer(String firstName, String lastName, List<Project> projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.projects = projects;
    }

    public void addProject(Project project) {
        if (this.projects == null)
            this.projects = new ArrayList<>();

        if (!projects.contains(project)) {
            projects.add(project);
            project.setDeveloper(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
        if (!company.getDevelopers().contains(this)) {
            company.setDeveloper(this);
        }
    }

    public void setProject(Project project) {
        if (this.projects == null)
            this.projects = new ArrayList<>();

        if (!projects.contains(project)) {
            projects.add(project);
            project.setDeveloper(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Developer{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
