package com.dbi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbi.model.Employee;
import com.dbi.model.IdNameEmail;
/**
 * 
 * @author BytesTree
 *
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("SELECT(emp) FROM Employee emp where e_email= ?1" )
	Employee loginfunction(String email);
	
	@Query("SELECT(e) FROM Employee e WHERE e.e_email= ?1 ")
	Employee getemployee(String email);
	
	@Query("UPDATE Employee SET e_name=?1, e_email=?2, e_password=?3, e_role=?4, e_target=?5, e_modifiedDate=?6 WHERE e_id=?7")
	@Modifying
	@Transactional
	int updateemployee(String name,String email, String password, String role, int target, String modifiedDate, Long id);
	
	@Query("SELECT e_id, e_name, e_email,e_password, e_role, e_target, e_createdDate, e_modifiedDate FROM Employee  ")
	List<Employee> getemployees();
	
	
	@Query("SELECT e_id FROM Employee where e_id= ?1" )
	Long idpresentornot(Long e_id);
	
	@Query(" UPDATE Employee SET e_target=0 ")
	@Modifying
	@Transactional
	int targettozero();
	
	@Query(" SELECT(e) FROM Employee e ")
	List<Employee> getemp();
	
	@Query("SELECT e.e_id FROM Employee e ")
	Long[] getallemployeeid();
	
	@Query(" SELECT(e) FROM Employee e WHERE e.e_id=?1 ")
	Employee getemployeebyid(Long eid);
	
	@Query(" SELECT new com.dbi.model.IdNameEmail(e_id,e_name,e_email) FROM Employee  WHERE e_id=?1 ")
	List<IdNameEmail> empIdnameEmail(Long eid);
	
	
}