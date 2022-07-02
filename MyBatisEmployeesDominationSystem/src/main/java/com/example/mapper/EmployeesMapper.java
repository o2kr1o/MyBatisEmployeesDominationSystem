package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Areas;
import com.example.entity.Departments;
import com.example.entity.Employees;
import com.example.model.SearchForm;

@Mapper
public interface EmployeesMapper {
	List<Employees> findAll();
	Employees get(@Param("id") Long id);
	int insert(@Param("employees") Employees employees);
	int update(@Param("employees") Employees employees);
	int delete(@Param("id") Long id);
	List<Employees> search (SearchForm serachForm);
	List<Departments> getDeptsName();
	List<Areas> getAreasName();
	Employees findByEmail(String email);
}
