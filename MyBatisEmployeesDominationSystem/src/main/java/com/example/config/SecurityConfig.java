package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.EmployeeDetailsService;

@Configuration
@MapperScan("com.example.mapper") // Mapperの属するパッケージを指定
public class SecurityConfig {
	@Autowired
	private EmployeeDetailsService employeeDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(employeeDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// リクエスト認証が必要か定義
		http.authorizeRequests()
				/*
				 * 認証が必要なく参照できる hasRoleは順番が大事
				 */
				.antMatchers("/js/**", "/css/**/**", "/regist", "/login").permitAll()
//				.antMatchers("/user/id_search", "/user/allUser").hasRole("ROLE_ADMIN")
//				.antMatchers("/user/userDetail/{id}").hasRole("ROLE_EMPLOYEE")
//				.antMatchers("/user/menu" ,  "/user/{id}/edit", "/user/{id}").hasAnyRole("ROLE_ADMIN","")
//				.anyRequest().authenticated()
				.and()
				.formLogin()
				// ログインページのURL
				.loginPage("/login")
				.loginProcessingUrl("/login")
				// ログイン成功時の遷移先
				.defaultSuccessUrl("/regist", true)
				.failureUrl("/login-error")
				// ログイン時のキー
				.usernameParameter("email")
				// ログイン時のパスワード
				.passwordParameter("password")
				.and()
				.logout()
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
		return http.build();
	}
}
