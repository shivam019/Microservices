package com.shivam.companyms.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    //Update the Company and return the same company object
    Boolean updateCompany(Company company, Long id);

    void createCompany(Company company);

    Boolean deleteCompany(Long id);

    Company getCompanyById(Long id);

}
