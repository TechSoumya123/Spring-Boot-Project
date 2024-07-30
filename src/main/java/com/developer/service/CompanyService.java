package com.developer.service;

import java.util.List;

import com.developer.model.Company;

public interface CompanyService {

	List<Company> fetchAllCompanies();

	boolean updateCompany(Long companyId, Company company);

	void createCompany(Company company);

	boolean deleteCompanyById(Long companyId);

	Company getCompanyById(Long companyId);
}
