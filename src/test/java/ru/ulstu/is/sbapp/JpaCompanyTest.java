package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.developer.model.Company;
import ru.ulstu.is.sbapp.developer.service.CompanyService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaCompanyTest {
    private static final Logger log = LoggerFactory.getLogger(JpaDeveloperTest.class);

    @Autowired
    private CompanyService companyService;

    @Test
    void testCompanyCreate() {
        companyService.deleteAllCompanies();
        final Company company = companyService.addCompany("Microsoft", "USA");
        log.info(company.toString());
        Assertions.assertNotNull(company.getId());
    }

    @Test
    void testCompanyRead() {
        companyService.deleteAllCompanies();
        final Company company = companyService.addCompany("Microsoft", "USA");
        log.info(company.toString());
        final Company findDeveloper = companyService.findCompany(company.getId());
        log.info(findDeveloper.toString());
        Assertions.assertEquals(company, findDeveloper);
    }

    @Test
    void testCompanyReadNotFound() {
        companyService.deleteAllCompanies();
        Assertions.assertThrows(EntityNotFoundException.class, () -> companyService.findCompany(-1L));
    }

    @Test
    void testCompanyReadAll() {
        companyService.deleteAllCompanies();
        companyService.addCompany("Microsoft", "USA");
        companyService.addCompany("Sun Microsystems", "USA");
        final List<Company> companies = companyService.findAllCompanies();
        log.info(companies.toString());
        Assertions.assertEquals(companies.size(), 2);
    }

    @Test
    void testCompanyReadAllEmpty() {
        companyService.deleteAllCompanies();
        final List<Company> companies = companyService.findAllCompanies();
        log.info(companies.toString());
        Assertions.assertEquals(companies.size(), 0);
    }
}
