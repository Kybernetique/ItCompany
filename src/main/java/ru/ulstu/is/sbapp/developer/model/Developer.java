package ru.ulstu.is.sbapp.developer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(name = "joined_developer_project", joinColumns = @JoinColumn(name = "developer_fk"), inverseJoinColumns = @JoinColumn(name = "project_fk"))
    private List<Project> worksOnProjects = new ArrayList<>();

    public Developer() {
    }


    public List<Project> getWorksOnProjects() {
        return worksOnProjects;
    }

    public List<Project> setWorksOnProjects(List<Project> worksOnProjects) {
        return this.worksOnProjects = worksOnProjects;
    }


    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
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
