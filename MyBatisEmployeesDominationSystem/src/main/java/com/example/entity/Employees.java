package com.example.entity;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.example.model.ValidGroup1;
import com.example.model.ValidGroup2;

import lombok.Data;

@Data
public class Employees {
	@NotNull
	private Long id;
	@NotNull
	private Long dept_id;
	@NotNull
	private Long area_id;
	@NotNull
	private Long role_id;
	@NotBlank(groups=ValidGroup1.class)
	@Length(min=4, max=12, groups=ValidGroup2.class)
	@Pattern(regexp="^[a-zA-Z0-9]+$", groups=ValidGroup2.class)
	private String password;
	@NotBlank(groups=ValidGroup1.class)
	private String name;
	@NotBlank(groups=ValidGroup1.class)
	private String email;
	@NotBlank(groups=ValidGroup1.class)
	@Pattern(regexp="^[0-9]{1,4}-[0-9]{1,4}-[0-9]{4}+$", groups=ValidGroup2.class)
	private String phone;
	private String gender = "男";
	private String status;
	@NotNull
	private Date start_date;
	private Date update_date;

	private EmployeesRole employeesRole;
}
