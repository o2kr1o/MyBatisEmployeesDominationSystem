package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Employees;
import com.example.mapper.EmployeesMapper;
import com.example.model.EmployeeDetails;


@Service
public class EmployeeDetailsService implements UserDetailsService {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		/*
		 * if(!StringUtils.hasText(email)) { throw new
		 * UsernameNotFoundException("ユーザー名を入力して下さい"); }
		 */
		//ログイン時のユーザー取得
		Employees employees = employeesMapper.findByEmail(email);
		if(employees == null) {
			throw new UsernameNotFoundException("ユーザーが見つけられませんでした");
		}
		return new EmployeeDetails(employees);
	}

}