

package com.dbi.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dbi.model.Employee;
import com.dbi.model.IdNameEmail;
import com.dbi.model.Target;
import com.dbi.repository.EmployeeRepository;

/**
 * 
 * @author BytesTree
 *
 */

@Service
@EnableScheduling

public class EmployeeServiceDao implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	@Autowired
	private TargetService targetService;
	
	
	
	
	public String encoding(String pwd){
		 String encoded_pwd = Base64.getEncoder().encodeToString(pwd.getBytes(StandardCharsets.UTF_8));
		 return encoded_pwd;
	}
	
	@Override
	public String decoding(String psd) {
		// TODO Auto-generated method stub
		String decoded_pwd=null;
		try {
			decoded_pwd = new String(Base64.getDecoder().decode(psd),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return decoded_pwd;
	}
		
	
	@Override
	public Employee save(Employee entity) {
		return employeeRepository.save(entity);
	}

	@Override
	public Employee getById(Serializable id) {
		return employeeRepository.findOne((Long) id);
	}

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		employeeRepository.delete((Long) id);
	}


	
	
	public Employee loginFunction(String email){
		return employeeRepository.loginfunction(email);
	}

	public Employee Getemployee(String email){
		return employeeRepository.getemployee(email);
	}
	
	public int updateEmployee(String name,String email, String password, String role, int target, String modifiedDate, Long id){
		return employeeRepository.updateemployee(name, email, password, role, target, modifiedDate, id);
	}
	
	public List<Employee> Getemployees(){
		return employeeRepository.getemployees();
	}
    
	
	public Long idPresentorNot(Long eid){
		return employeeRepository.idpresentornot(eid);
	}
	
	@Scheduled(cron=" 0 0 0 1 * *  ")
	  public void cronTasktoinsertTargettable(){
	    List<Employee> emp = employeeRepository.getemp();
	    for(int i=0;i<emp.size();i++){
	    	Target t = new Target(emp.get(i).getE_id(),emp.get(i).getE_name(),emp.get(i).getE_email(),emp.get(i).getE_target(),
	    			   emp.get(i).getE_role(),emp.get(i).getE_createdDate());
	    	
	    	targetService.save(t);
	    		
	    }
	    
	  }
	
	 @Scheduled(cron=" 0 1 0 1 * *  ")
	  public void cronTask(){
	    int a = employeeRepository.targettozero();
	  }
	 
	 public Long[] Getallemployeeid(){
		 return employeeRepository.getallemployeeid();
	 }
	 
	 public Employee Getemployeebyid(Long eid){
		 return employeeRepository.getemployeebyid(eid);
	 }
	 
	 public List<IdNameEmail> empIdnameEmail(Long eid){
		 return employeeRepository.empIdnameEmail(eid);
	 }
}
