package com.abc.blogger.security;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

	@Bean
	 public FilterRegistrationBean filter(){
		 FilterRegistrationBean registrationBean= new FilterRegistrationBean();
		 registrationBean.setName("authorizationFilter");
		 
		 SecurityFilter s= new SecurityFilter();
		 
		 registrationBean.setFilter(s);
		 
		 registrationBean.addUrlPatterns("/*");
		 
		 registrationBean.setOrder(1);
		 
		 return registrationBean;
		 
		 
	 }
}
