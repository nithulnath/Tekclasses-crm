package com.dbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dbi.controller.AdminDashboardController;
import com.dbi.controller.CourseController;
import com.dbi.controller.EmployeeController;
import com.dbi.controller.LeadController;
/**
 * 
 * @author BytesTree
 *
 */


@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackageClasses = {EmployeeController.class, AdminDashboardController.class, CourseController.class, LeadController.class},
basePackages = {"com.dbi","com.dbi.controller", "com.dbi.model","com.dbi.repository","com.dbi.service"})
public class Application extends org.springframework.boot.web.support.SpringBootServletInitializer {

	@Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       return application.sources(Application.class);
 }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
