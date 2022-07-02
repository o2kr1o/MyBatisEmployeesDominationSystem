package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Areas;
import com.example.entity.Departments;
import com.example.entity.Employees;
import com.example.model.SearchForm;
import com.example.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeesService {
	@Autowired
	private final EmployeesRepository employeesRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public List<Employees> findAll() {
		return employeesRepository.findAll();
	}

	public int create(Employees employees) {
		employees.setPassword(passwordEncoder.encode(employees.getPassword()));
		return employeesRepository.create(employees);
	}

	public int update(Employees employees) {
		return employeesRepository.update(employees);
	}

	@Transactional(readOnly = true)
	public Employees get(Long id) {
		return employeesRepository.get(id);
	}

	public int delete(Long id) {
		return employeesRepository.delete(id);
	}

	public List<Employees> search(SearchForm serachForm) {
		return employeesRepository.search(serachForm);
	}

	public List<Departments> getDeptsName() {
		return employeesRepository.getDeptsName();
	}

	public List<Areas> getAreasName() {
		return employeesRepository.getAreasName();
	}
}
