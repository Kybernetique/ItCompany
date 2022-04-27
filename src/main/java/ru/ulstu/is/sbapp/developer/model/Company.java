package ru.ulstu.is.sbapp.developer.model;

import javax.persistence.*;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String companyName;
    private String companyCountry;

    @ManyToMany
    @JoinTable(name = "joined_company_project",
            joinColumns = @JoinColumn(name = "company_fk"),
            inverseJoinColumns = @JoinColumn(name = "project_fk"))
    private List<Project> worksOnProjects = new ArrayList<>();

    public Company()
    {
    }

    public Company(String companyName, String companyCountry )
    {
        this.companyName = companyName;
        this.companyCountry = companyCountry;
    }

    public Long getId()
    {
        return id;
    }

    public List<Project> getWorksOnProjects() {
        return worksOnProjects;
    }

    public List<Project> setWorksOnProjects(List<Project> worksOnProjects) {
        return this.worksOnProjects = worksOnProjects;
    }


    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyCountry()
    {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry)
    {
        this.companyCountry = companyCountry;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return "Company{" + "id=" + id + ", companyName='" + companyName + '\'' + ", companyCountry='" + companyCountry + '\'' + '}';
    }
}

