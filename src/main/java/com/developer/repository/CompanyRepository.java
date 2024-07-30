package com.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
