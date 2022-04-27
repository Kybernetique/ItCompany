package ru.ulstu.is.sbapp.developer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.developer.model.Company;
import ru.ulstu.is.sbapp.developer.model.Developer;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CompanyService {
    @PersistenceContext
    private EntityManager em ;


    @Transactional
    public Company addCompany(String companyName, String companyCountry) {
        if (!StringUtils.hasText(companyName) || !StringUtils.hasText(companyCountry)) {
            throw new IllegalArgumentException("Company name is null or empty");
        }
        final Company company = new Company(companyName, companyCountry);
        em.persist(company);
        return company;
    }

    @Transactional
    public Company setDeveloper(Long id, Developer developer){
        final Company currentCompany = findCompany(id);
        currentCompany.setDeveloper(developer);
        return em.merge(currentCompany);
    }


    @Transactional(readOnly = true)
    public Company findCompany(Long id) {
        final Company company = em.find(Company.class, id);
        if (company == null) {
            throw new EntityNotFoundException(String.format("Company with id [%s] is not found", id));
        }
        return company;
    }

    @Transactional(readOnly = true)
    public List<Company> findAllCompanies() {
        return em.createQuery("select c from Company c", Company.class).getResultList();
    }

    @Transactional
    public int developersCount(Long id) {
        final Company currentOwner = findCompany(id);
        return currentOwner.developersCount();
    }


    @Transactional
    public Company updateCompany(Long id, String companyName, String companyCountry) {
        if (!StringUtils.hasText(companyName) || !StringUtils.hasText(companyCountry)) {
            throw new IllegalArgumentException("Company name is null or empty");
        }
        final Company currentCompany = findCompany(id);
        currentCompany.setCompanyName(companyName);
        currentCompany.setCompanyCountry(companyCountry);
        return em.merge(currentCompany);
    }

    @Transactional
    public Company deleteCompany(Long id) {
        final Company currentCompany = findCompany(id);
        em.remove(currentCompany);
        return currentCompany;
    }

    @Transactional
    public void deleteAllCompanies() {
        em.createQuery("delete from Company").executeUpdate();
    }
}
