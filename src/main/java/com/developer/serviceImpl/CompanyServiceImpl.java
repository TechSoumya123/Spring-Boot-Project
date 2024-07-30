package com.developer.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.developer.model.Company;
import com.developer.repository.CompanyRepository;
import com.developer.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> fetchAllCompanies() {
		return companyRepository.findAll().stream().toList();
	}

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public boolean updateCompany(Long companyId, Company updateCompany) {
		Optional<Company> optionalCompany = companyRepository.findById(companyId);
		if (optionalCompany.isPresent()) {
			Company company = optionalCompany.get();
			company.setCompanyName(updateCompany.getCompanyName());
			company.setDescription(updateCompany.getDescription());
			company.setListOfJobs(updateCompany.getListOfJobs());
			companyRepository.save(company);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteCompanyById(Long companyId) {
		Optional<Company> byId = companyRepository.findById(companyId);
		if (byId.isPresent()) {
			companyRepository.deleteById(companyId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Company getCompanyById(Long companyId) {
		return companyRepository.findById(companyId).orElse(null);

	}

}
