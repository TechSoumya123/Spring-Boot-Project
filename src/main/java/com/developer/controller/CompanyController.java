package com.developer.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.model.Company;
import com.developer.service.CompanyService;

@RestController
@RequestMapping(path = { "/company" })
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping(path = { "/fetch-All-Companies" })
	public ResponseEntity<?> fetchAllCompanies() {
		try {
			var listOfCompanies = companyService.fetchAllCompanies();
			return listOfCompanies.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
					: ResponseEntity.status(HttpStatus.OK).body(listOfCompanies);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(exception.getMessage());
		}
	}

	@PostMapping(path = { "/create-company" })
	public ResponseEntity<String> createNewCompany(@RequestBody Company company) {
		try {
			companyService.createCompany(company);
			return ResponseEntity.status(HttpStatus.CREATED).body("Company created successfully");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@PutMapping(path = { "/update-company/{id}" })
	public ResponseEntity<String> updateCompany(@PathVariable("id") Long companyId, @RequestBody Company company) {
		try {
			return companyService.updateCompany(companyId, company)
					? ResponseEntity.status(HttpStatus.OK).body("Company updated successfully ")
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No company found with this id " + companyId);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@DeleteMapping(path = { "/delete-companyBy/{id}" })
	public ResponseEntity<String> deleteCompanyById(@PathVariable("id") Long companyId) {
		try {
			return companyService.deleteCompanyById(companyId)
					? ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully")
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("No company found with this id " + companyId);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@GetMapping(path = { "/getCompanyBy/{id}" })
	public ResponseEntity<?> getCompanyById(@PathVariable("id") Long companyId) {
		try {
			Company company = companyService.getCompanyById(companyId);
			return Objects.isNull(company)
					? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found with this id " + companyId)
					: ResponseEntity.status(HttpStatus.OK).body(company);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(exception.getMessage());
		}
	}
}
