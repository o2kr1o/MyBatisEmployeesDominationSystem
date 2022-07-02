package com.example.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.entity.Areas;
import com.example.entity.Departments;
import com.example.entity.Employees;
import com.example.mapper.EmployeesMapper;
import com.example.model.SearchForm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeesRepository {

  private final EmployeesMapper employeesMapper;

  public List<Employees> findAll() {
    return employeesMapper.findAll();
  }

  public Employees get(Long id) {
    return employeesMapper.get(id);
  }

  public int create(Employees employees) {
    return employeesMapper.insert(employees);
  }

  public int update(Employees employees) {
    return employeesMapper.update(employees);
  }

  public int delete(Long id) {
    return employeesMapper.delete(id);
  }

  public List<Employees> search(SearchForm serachForm) {
	return employeesMapper.search(serachForm);
  }

  public List<Departments> getDeptsName() {
	  return employeesMapper.getDeptsName();
  }

  public List<Areas> getAreasName() {
	  return employeesMapper.getAreasName();
  }

  public Employees findByEmail(String email) {
	  return employeesMapper.findByEmail(email);
  }
}
