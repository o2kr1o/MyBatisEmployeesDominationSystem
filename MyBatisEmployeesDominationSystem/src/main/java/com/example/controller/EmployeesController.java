package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.entity.Areas;
import com.example.entity.Departments;
import com.example.entity.Employees;
import com.example.model.GroupOrder;
import com.example.model.SearchForm;
import com.example.service.EmployeesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EmployeesController {
    private final EmployeesService employeesService;

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@GetMapping("/top")
	public String top(Model model) {
		List<Employees> empList = employeesService.findAll();
		model.addAttribute("empList", empList);
		return "user/top";
	}

	@PostMapping("/top")
	public String search(SearchForm serachForm, Model model) {
		List<Employees> empList = employeesService.search(serachForm);
		model.addAttribute("empList", empList);
		return "user/top";
	}

	@GetMapping("/regist")
	public String regist(Employees employees, Model model) {
		List<Departments> deptsList = employeesService.getDeptsName();
		model.addAttribute("deptsList", deptsList);
		List<Areas> areasList = employeesService.getAreasName();
		model.addAttribute("areasList", areasList);
		return "user/regist";
	}

	@PostMapping("/regist")
	public String create(@Validated(GroupOrder.class) Employees employees, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "user/regist";
		} else {
			employeesService.create(employees);
			return "redirect:/top";
		}
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Employees employees = employeesService.get(id);
		model.addAttribute("employees", employees);
		List<Departments> deptsList = employeesService.getDeptsName();
		model.addAttribute("deptsList", deptsList);
		List<Areas> areasList = employeesService.getAreasName();
		model.addAttribute("areasList", areasList);
		return "user/edit";
	}

	@PutMapping("/edit/{id}")
	public String edit(@PathVariable Long id, @Validated(GroupOrder.class) Employees employees, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "user/edit";
		} else {
			employeesService.update(employees);
			return "redirect:/top";
		}
	}

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		Employees empInfo = employeesService.get(id);
		model.addAttribute("empInfo", empInfo);
		return "user/detail";
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		employeesService.delete(id);
		return "redirect:/top";
	}
}
