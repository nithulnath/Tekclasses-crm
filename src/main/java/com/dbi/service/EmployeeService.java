package com.dbi.service;

import com.dbi.model.Employee;

/**
 * 
 * @author BytesTree
 *
 */
public interface EmployeeService extends CRUDService<Employee> {
	String encoding(String pwd);
	String decoding(String pwd);

}
