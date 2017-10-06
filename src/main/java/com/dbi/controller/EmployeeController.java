
package com.dbi.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbi.model.AllDetailsOfEmployeeAtLogin;
import com.dbi.model.AllEmployeeDetails;
import com.dbi.model.CourseAssignedTo;
import com.dbi.model.CourseInterested;
import com.dbi.model.Employee;
import com.dbi.model.GetLeadDetails;
import com.dbi.model.Lead;
import com.dbi.model.LeadCourseInterested;
import com.dbi.model.LeadOwner;
import com.dbi.model.Performance;
import com.dbi.service.CourseAssignedToService;
import com.dbi.service.CourseAssignedToServiceDao;
import com.dbi.service.CourseMasterServiceDao;
import com.dbi.service.EmployeeService;
import com.dbi.service.EmployeeServiceDao;
import com.dbi.service.LeadCourseInterestedServiceDao;
import com.dbi.service.LeadOwnerServiceDao;
import com.dbi.service.LeadServiceDao;
import com.dbi.service.TargetService;
import com.dbi.service.TargetServiceDao;


@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private EmployeeServiceDao employeeservicedao;
	
	@Autowired
	private TargetService targetservice;
	
	@Autowired
	private TargetServiceDao targetServicedao;
	
	@Autowired
	private CourseMasterServiceDao cmservicedao;
	
	@Autowired
	private CourseAssignedToService courseAssignedtoService;
	
	@Autowired
	private CourseAssignedToServiceDao courseAssignedtoServicedao;
	
	@Autowired
	private LeadCourseInterestedServiceDao leadCourseinterestedServicedao;
	
	@Autowired
	private LeadServiceDao leadServicedao;
	
	@Autowired
	private LeadOwnerServiceDao leadOwnerserviceDao;

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ResponseEntity<?> addEmployee(@RequestBody String employee) {
		//to encode the password
		JSONObject obj;
		
		String encoded_password=null;
		Employee savedemp= new Employee();
		JSONArray coursearray;
		
		try {
			obj = new JSONObject(employee);
			if((obj.getString("e_role").toString()).equals("Admin")){
					//to encode the password entered from front end before saving into the database
					encoded_password =employeeService.encoding(obj.getString("e_password"));
		
					String createdDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					// inserting data into employee using the constructor
					Employee emp= new Employee(obj.getString("e_name").toString(),obj.getString("e_email").toString(),
				                    encoded_password,"Admin",
				                    0,createdDate,createdDate);
					employeeService.save(emp);
					logger.debug("Added:: " + employee);
					return new ResponseEntity<>(emp, HttpStatus.CREATED);
				}
			else{
					obj = new JSONObject(employee);
					//to encode the password entered from front end before saving into the database
					encoded_password =employeeService.encoding(obj.getString("e_password"));
		
					String createdDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					// inserting data into employee using the constructor
					Employee emp= new Employee(obj.getString("e_name").toString(),obj.getString("e_email").toString(),
				                    encoded_password,obj.getString("e_role").toString(),
				                    obj.getInt("e_target"),createdDate,createdDate);
					employeeService.save(emp);
					logger.debug("Added:: " + employee);
			
					String email=obj.getString("e_email");
		
					savedemp=employeeservicedao.Getemployee(email);
					//to store data to target table at the same time
		 
					//to store data to CourseAssignedTo table
					coursearray = obj.getJSONArray("e_courseAssigned");
					String[] cname=new String[coursearray.length()];
					Long[] cid = new Long[coursearray.length()];
					for(int i=0;i<coursearray.length();i++){
						cname[i]= coursearray.getString(i);
						cid[i] = cmservicedao.Getcourseid(cname[i]);
						CourseAssignedTo cat = new CourseAssignedTo(savedemp.getE_id(), cid[i],cname[i], createdDate, createdDate);
				 courseAssignedtoService.save(cat);
			 }
			 
			 
			 
			 // to display what all are added
			 JSONObject jobj=new JSONObject();
			 jobj.put("e_id", savedemp.getE_id());
			 jobj.put("e_email", savedemp.getE_email());
			 jobj.put("e_password", savedemp.getE_password());
			 jobj.put("e_role", savedemp.getE_role());
			 jobj.put("e_target", savedemp.getE_target());
			 jobj.put("e_courseAssigned", coursearray);
			 jobj.put("e_createdDate", createdDate);
			 jobj.put("e_modifiedDate", createdDate);
			 
		return new ResponseEntity<>(jobj.toString(), HttpStatus.CREATED);
		} }catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(" Exception ", HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	
	
	
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
	public boolean updateEmployee(@RequestBody String employee) {
		try {
			JSONArray coursearray;
		JSONObject emp = new JSONObject(employee);
		Employee savedemp= new Employee();
		Employee existingEmp = employeeService.getById(emp.getLong("e_id"));
		if (existingEmp == null) {
			logger.debug("Employee with id " + emp.getLong("e_id") + " does not exists");
			return false;
		} else {
			
			if((emp.getString("e_role").toString()).equals("Admin")){
				//to encode the password entered from front end before saving into the database
				String encoded_password =employeeService.encoding(emp.getString("e_password"));
				String modifiedDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String createdDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				// inserting data into employee using the constructor
				int a= employeeservicedao.updateEmployee(emp.getString("e_name").toString(),emp.getString("e_email").toString(),
			                    encoded_password,"Admin",
			                    0,modifiedDate,emp.getLong("e_id"));
				logger.debug("Added:: " + employee);
				return true;
			}
			else{
			String encoded_password=employeeService.encoding(emp.getString("e_password"));
			String modifiedDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			int a= employeeservicedao.updateEmployee(emp.getString("e_name"), emp.getString("e_email"),
					encoded_password,emp.getString("e_role"), emp.getInt("e_target"), modifiedDate,emp.getLong("e_id"));
			
			String email=emp.getString("e_email");
			
			savedemp=employeeservicedao.Getemployee(email);
			//to store data to target table at the same time
			
			
			
			int b = targetServicedao.updateTarget(savedemp.getE_name(),savedemp.getE_email(),savedemp.getE_role(),savedemp.getE_id()); 
			
		
			//to update CourseAssignedTO, we must first delete the previous table because if it has 5 courses and 
			//  while updating,if the course reduces to 3, the table entry will remain 5 and update will be done for 
			//first 3 course values only
			int del= courseAssignedtoServicedao.Deletecourseassignedto(emp.getLong("e_id"));
			//to store data to CourseAssignedTo table
			 String createddateofemployee = existingEmp.getE_createdDate();
			 coursearray = emp.getJSONArray("e_courseAssigned");
			 String[] cname=new String[coursearray.length()];
				Long[] cid = new Long[coursearray.length()];
			 for(int i=0;i<coursearray.length();i++){
				 cname[i]= coursearray.getString(i);
				 cid[i] = cmservicedao.Getcourseid(cname[i]);
				 CourseAssignedTo cat = new CourseAssignedTo(savedemp.getE_id(), cid[i],cname[i], createddateofemployee, modifiedDate);
				 courseAssignedtoService.save(cat);
			 }
			
			
			return true;
			}
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
	
	
	
	
	
	

	@RequestMapping(value = "/getEmployeeById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable("id") Long eid) {
		Employee employee = employeeService.getById(eid);
		if (employee == null) {
			logger.debug("Employee with id " + eid + " does not exists");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		
		// to get all details related to the employee
		
		String decoded_password=employeeService.decoding(employee.getE_password());
		List<CourseAssignedTo> courseassignedto = courseAssignedtoServicedao.getCourseassignedTobyEmpid(employee.getE_id());		
		AllEmployeeDetails allemployeedetails =	new AllEmployeeDetails(employee.getE_id(),employee.getE_name(),employee.getE_email(),
							decoded_password,employee.getE_role(),employee.getE_target(),courseassignedto,
							employee.getE_createdDate(),employee.getE_modifiedDate());
				
			
			Long[] leadid = leadOwnerserviceDao.getleadid(employee.getE_email());
			
			
			
			List<GetLeadDetails> getleaddetails = new ArrayList<GetLeadDetails>();
			for(int i=0;i<leadid.length;i++){
				Lead lead = leadServicedao.getallleadbyid(leadid[i]);
				List<LeadCourseInterested> leadcourseinterested = leadCourseinterestedServicedao.getleadcourseinterestedbyid(leadid[i]);
				
				 List<LeadOwner> leadOwner = new ArrayList<LeadOwner>();
	
				List<CourseInterested> courseInterested = new ArrayList<CourseInterested>();
				for(int j=0;j<leadcourseinterested.size();j++){
					Long courseid = leadcourseinterested.get(j).getC_id();// to get first courseid among multiple courses lead is interested in
					leadOwner = leadOwnerserviceDao.getLeadowner(leadid[i], courseid);// to get employees who are assigned with this course
					CourseInterested ci = new CourseInterested(leadcourseinterested.get(j).getLeadId(),
							   leadcourseinterested.get(j).getC_id(),
							   leadcourseinterested.get(j).getC_name(),
							   leadcourseinterested.get(j).getMode(),
							   leadcourseinterested.get(j).getCourseFee(),
							   leadcourseinterested.get(j).getFeeOffered(),
							   leadcourseinterested.get(j).getFeePaid(),
							   leadcourseinterested.get(j).getPaymentDate(),
							   leadcourseinterested.get(j).getPendingPayment(),
							   leadcourseinterested.get(j).getLeadStatus(),
							   leadOwner);
					courseInterested.add(j,ci );
					}
	
			getleaddetails.add(new GetLeadDetails(lead.getL_id(),lead.getL_name(),lead.getL_email(),lead.getL_contactNumber(),
				lead.getL_country(),lead.getL_comment(),lead.getL_source(),courseInterested,
				lead.getL_createdDate(),lead.getL_modifiedDate()));
		}
			
			AllDetailsOfEmployeeAtLogin alldetailsofemployeeatlogin = new AllDetailsOfEmployeeAtLogin(allemployeedetails,getleaddetails);
			logger.debug(" verified successfully ");
			return new ResponseEntity<>(alldetailsofemployeeatlogin, HttpStatus.OK);
	}
		
	
	
	
	
	
	

	@RequestMapping(value = "/getAllEmployees",method = RequestMethod.GET)
	public ResponseEntity<?> getAllEmployees() {
		List<Employee> employees = employeeService.getAll();
		if (employees.isEmpty()) {
			logger.debug("Employees does not exists");
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		Long[] empid = employeeservicedao.Getallemployeeid();
		List<AllEmployeeDetails> allemployeedetails = new ArrayList<AllEmployeeDetails>();
		for(int i=0;i<empid.length;i++){
			Employee employee = employeeservicedao.Getemployeebyid(empid[i]);
			String decodedpassword= employeeservicedao.decoding(employee.getE_password());
			List<CourseAssignedTo> courseassignedto = courseAssignedtoServicedao.getCourseassignedTobyEmpid(empid[i]);
			allemployeedetails.add(new AllEmployeeDetails(employee.getE_id(),employee.getE_name(),employee.getE_email(),
					decodedpassword,employee.getE_role(),employee.getE_target(),courseassignedto,
					employee.getE_createdDate(),employee.getE_modifiedDate()));
		}
		return new ResponseEntity<>(allemployeedetails, HttpStatus.OK);
	}

	
	
	
	
	
	

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE)
	public boolean deleteEmployee(@PathVariable("id") Long eid) {
		Employee employee = employeeService.getById(eid);
		if (employee == null) {
			logger.debug("Employee with id " + eid + " does not exists");
			return false;
		} else {
			employeeService.delete(eid);
			int del= courseAssignedtoServicedao.Deletecourseassignedto(eid);
			
			
			
			
			int deltarge= targetServicedao.deleteTarget(eid);
			
			
			
			logger.debug("Employee with id " + eid + " deleted");
			return true;
		}
 }
	
	
	
	
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	 public ResponseEntity<?> login(@RequestBody String json){
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			JSONObject object = new JSONObject();
			String password=null;
			Employee employee = employeeservicedao.loginFunction(jsonObject.getString("email").toString());
			if(employee == null){
				logger.debug("verifiction failed");
				return new ResponseEntity<>(" Username not found ",HttpStatus.NOT_FOUND);
				
			}
			password = employee.getE_password();	
			String decoded_password=employeeService.decoding(password);
			String input_password=employeeService.encoding(jsonObject.getString("password"));
			
			if((password).equals(input_password)){
				List<CourseAssignedTo> courseassignedto = courseAssignedtoServicedao.getCourseassignedTobyEmpid(employee.getE_id());		
			AllEmployeeDetails allemployeedetails =	new AllEmployeeDetails(employee.getE_id(),employee.getE_name(),employee.getE_email(),
								decoded_password,employee.getE_role(),employee.getE_target(),courseassignedto,
								employee.getE_createdDate(),employee.getE_modifiedDate());
					
				
				Long[] leadid = leadOwnerserviceDao.getleadid(employee.getE_email());
				
				
				
				List<GetLeadDetails> getleaddetails = new ArrayList<GetLeadDetails>();
				for(int i=0;i<leadid.length;i++){
					Lead lead = leadServicedao.getallleadbyid(leadid[i]);
					List<LeadCourseInterested> leadcourseinterested = leadCourseinterestedServicedao.getleadcourseinterestedbyid(leadid[i]);
					
					 List<LeadOwner> leadOwner = new ArrayList<LeadOwner>();
		
					List<CourseInterested> courseInterested = new ArrayList<CourseInterested>();
					for(int j=0;j<leadcourseinterested.size();j++){
						Long courseid = leadcourseinterested.get(j).getC_id();// to get first courseid among multiple courses lead is interested in
						leadOwner = leadOwnerserviceDao.getLeadowner(leadid[i], courseid);// to get employees who are assigned with this course
						CourseInterested ci = new CourseInterested(leadcourseinterested.get(j).getLeadId(),
								   leadcourseinterested.get(j).getC_id(),
								   leadcourseinterested.get(j).getC_name(),
								   leadcourseinterested.get(j).getMode(),
								   leadcourseinterested.get(j).getCourseFee(),
								   leadcourseinterested.get(j).getFeeOffered(),
								   leadcourseinterested.get(j).getFeePaid(),
								   leadcourseinterested.get(j).getPaymentDate(),
								   leadcourseinterested.get(j).getPendingPayment(),
								   leadcourseinterested.get(j).getLeadStatus(),
								   leadOwner);
						courseInterested.add(j,ci );
						}
		
				getleaddetails.add(new GetLeadDetails(lead.getL_id(),lead.getL_name(),lead.getL_email(),lead.getL_contactNumber(),
					lead.getL_country(),lead.getL_comment(),lead.getL_source(),courseInterested,
					lead.getL_createdDate(),lead.getL_modifiedDate()));
			}
				
				AllDetailsOfEmployeeAtLogin alldetailsofemployeeatlogin = new AllDetailsOfEmployeeAtLogin(allemployeedetails,getleaddetails);
				logger.debug(" verified successfully ");
				return new ResponseEntity<>(alldetailsofemployeeatlogin, HttpStatus.OK);
				}
			else
			{
				logger.debug("verifiction failed");
				return new ResponseEntity<>("Wrong username or password ",HttpStatus.NOT_FOUND);
			}
		 
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	 }
	 
	 
	 
	 
	 
	 
	 // this is to supply the eid, ename and course assigned to that employee 
	 @RequestMapping(value = "/getCourseAssignedToEmployeeById/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getCourseassignedToemployee(@PathVariable("id") Long eid) {
			// first we need to check if the eid is present in our table
		 Long empid = employeeservicedao.idPresentorNot(eid);
			if (empid == null) {
				logger.debug("Employee with id " + eid + " does not exists");
				return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
			}
			List<CourseAssignedTo> courseassignedto = courseAssignedtoServicedao.getCourseassignedTobyEmpid(eid);
			logger.debug("Found Employee:: ");
			return new ResponseEntity<>(courseassignedto, HttpStatus.OK);
		}
	 
	 
	





//employee performance by Id
@RequestMapping(value = "/getEmployeeperformanceByid/{id}", method = RequestMethod.GET)
public ResponseEntity<?> getEmployeeperformance(@PathVariable("id") Long eid) {
	Employee employee = employeeService.getById(eid);
	Performance performance = new Performance();
	
	if (employee == null) {
		logger.debug("Employee with id " + eid + " does not exists");
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	else if((employee.getE_role().equals("Marketing")) || (employee.getE_role().equals("marketing"))){
		List<Long> achievedtarget = leadServicedao.defaultAchieved(employee.getE_email());
		
		if(achievedtarget.get(0)!=null){
			
			int achieved= achievedtarget.get(0).intValue();
			performance=(new Performance(employee.getE_name(),employee.getE_target(),achieved));	
			return new ResponseEntity<>(performance,HttpStatus.OK);
			
			
		}
		performance = new Performance(employee.getE_name(),employee.getE_target(),0);	
		return new ResponseEntity<>(performance,HttpStatus.OK);
		
		}

		else if((employee.getE_role().equals("Sales")) || (employee.getE_role().equals("sales"))){
			List<Long> achievedtarget = leadServicedao.defaultAchievedforSales(employee.getE_email());
			
			if(achievedtarget.get(0)!=null){
				
				int achieved= achievedtarget.get(0).intValue();
				performance=(new Performance(employee.getE_name(),employee.getE_target(),achieved));	
				return new ResponseEntity<>(performance,HttpStatus.OK);
				
				
			}
			performance=(new Performance(employee.getE_name(),employee.getE_target(),0));	
			return new ResponseEntity<>(performance,HttpStatus.OK);
			
			
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}

}








