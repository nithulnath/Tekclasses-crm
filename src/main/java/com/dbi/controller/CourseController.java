


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

import com.dbi.model.AllCourseDetails;
import com.dbi.model.CourseDetails;
import com.dbi.model.CourseMaster;
import com.dbi.model.DisplayAllCourses;
import com.dbi.model.IdAndMode;
import com.dbi.model.IdNameEmail;
import com.dbi.model.ModeAndPrice;
import com.dbi.model.ToGetCourseDetails;
import com.dbi.service.AllCourseDetailsService;
import com.dbi.service.CourseAssignedToServiceDao;
import com.dbi.service.CourseDetailsService;
import com.dbi.service.CourseMasterService;
import com.dbi.service.CourseMasterServiceDao;
import com.dbi.service.CourseModeMasterService;
import com.dbi.service.EmployeeServiceDao;


@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {

	final static Logger logger = Logger.getLogger(CourseController.class);
 
	
	@Autowired
	CourseDetailsService courseDetailsservice;
	
	@Autowired
	CourseMasterService courseMasterservice;

	@Autowired
	CourseModeMasterService coursemodeMasterservice;

	@Autowired
	CourseMasterServiceDao courseMasterserviceDao;
	
	@Autowired
	AllCourseDetailsService allCoursedetailsService;
	
	@Autowired
	CourseAssignedToServiceDao courseassignedtoservicedao;
	
	@Autowired
	EmployeeServiceDao employeeservicedao;
	
	
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public ResponseEntity<?> addCourse(@RequestBody String jsn) {
		
		
		String name;
		String duration;
		String createdDate;
		String modifiedDate;
		
		 JSONObject jsnobject;
		 JSONObject mandp;
		 JSONObject finalobj;
		 JSONObject object;
		 JSONObject cmodeandprice;
		 try {
		
			 	jsnobject = new JSONObject(jsn);
				createdDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				modifiedDate=createdDate;
				name= jsnobject.getString("c_name");
				duration=jsnobject.getString("c_duration");
		  
				CourseMaster cm = new CourseMaster(name,createdDate,modifiedDate);
				courseMasterservice.save(cm);
				Long courseId = courseMasterserviceDao.Getcourseid(name);
					 object = new JSONObject(jsn);
					 cmodeandprice = object.getJSONObject("c_modeandprice");
					 
					 Long onlineid= courseMasterserviceDao.Getonlineid();
						Long selfpacedid= courseMasterserviceDao.Getselfpacedid();
						Long modes[]={onlineid,selfpacedid};
					 
					 JSONArray jsonArray = cmodeandprice.getJSONArray("record");
					 String mode[]= new String[jsonArray.length()];
						int price[]= new int[jsonArray.length()];
					 for (int i=0; i<jsonArray.length(); i++) {
		    		  mandp = jsonArray.getJSONObject(i);
				      mode[i]= mandp.getString("mode");
					  price[i]=mandp.getInt("price");
					  CourseDetails cd1=new CourseDetails(courseId,modes[i],price[i],duration,createdDate,modifiedDate);
					  courseDetailsservice.save(cd1);
				
					 }
			   
					 AllCourseDetails acd = new AllCourseDetails(courseId,name,mode,price,duration,createdDate,modifiedDate); 
					 allCoursedetailsService.save(acd);
		

				finalobj=new JSONObject();
				finalobj.put("c_id", courseId);
				finalobj.put("c_name", name);
				finalobj.put("c_modeandprice",cmodeandprice);
				finalobj.put("c_duration", duration);
				finalobj.put("createdDate", createdDate);
				finalobj.put("modifiedDate", modifiedDate);
		
			
		 	} catch (JSONException e) {
			 //TODO Auto-generated catch block
		 		e.printStackTrace();
		 		return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		 	}
		logger.debug("Added:: " );
		return new ResponseEntity<>( finalobj.toString(), HttpStatus.CREATED);
			
	}
	
	
	@RequestMapping(value = "/updateCourse", method = RequestMethod.PUT)
	public boolean updateCourse(@RequestBody String jsn) {
		String name;
		String duration;
		String createdDate;
		String modifiedDate;
		
		 JSONObject finalobj;
		JSONObject jsnobject;
		 JSONObject mandp;
		 JSONObject object;
		 JSONObject cmodeandprice;
			     try{
			    	 
			    	 
			    	 jsnobject = new JSONObject(jsn);
			 		Long courseid=jsnobject.getLong("c_id");
			 		CourseMaster existing = courseMasterservice.getById(courseid);
			 		if (existing == null) {
			 			logger.debug("Course with id " + courseid + " does not exists");
			 			return false;
			 		} else {
					modifiedDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					name= jsnobject.getString("c_name");
					duration=jsnobject.getString("c_duration");
			  
					int a=courseMasterserviceDao.updatetable(name,modifiedDate,courseid);
					createdDate = existing.getCm_createdDate();
					
					 object = new JSONObject(jsn);
					 cmodeandprice = object.getJSONObject("c_modeandprice");
					Long onlineid= courseMasterserviceDao.Getonlineid();
					Long selfpacedid= courseMasterserviceDao.Getselfpacedid();
					Long modes[]={onlineid,selfpacedid};
				 
				 JSONArray jsonArray = cmodeandprice.getJSONArray("record");
				 String mode[]= new String[jsonArray.length()];
					int price[]= new int[jsonArray.length()];
				 for (int i=0; i<jsonArray.length(); i++) {
	    		  mandp = jsonArray.getJSONObject(i);
			      mode[i]= mandp.getString("mode");
				  price[i]=mandp.getInt("price");
				
				int c= courseMasterserviceDao.updatecoursedetails1(price[i],duration,modifiedDate,courseid,modes[i]);
				 }	
					
				 int d =courseMasterserviceDao.Getallcoursedetails(name, mode,price,duration, modifiedDate, courseid);
					
				 
					finalobj=new JSONObject(); 
					finalobj.put("c_id", courseid);
					finalobj.put("c_name",name);
					finalobj.put("c_modeandprice",cmodeandprice);
					finalobj.put("c_duration", duration);
					finalobj.put("createdDate", createdDate);
					finalobj.put("modifiedDate", modifiedDate);
					return true;
			 		}
			     }
			     catch (JSONException e) {
					 //TODO Auto-generated catch block
				 		e.printStackTrace();
				 		return false;
				 	}	 	
			       	
	}

	@RequestMapping(value = "/getCourseById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCourse(@PathVariable("id") Long courseid) {
		CourseMaster cm = courseMasterservice.getById(courseid);
		 try{
		if (cm == null) {
			logger.debug("Course with id " + courseid + " does not exists");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Course:: " + cm);
		

		List<ToGetCourseDetails> coursedetails = courseMasterserviceDao.Getcoursedetails(courseid);
		int prices[]=new int[coursedetails.size()];
		Long modeids[]=new Long[coursedetails.size()];
		String duration[]= new String[coursedetails.size()];
		for(int i=0; i<coursedetails.size();i++){
		   prices[i]= coursedetails.get(i).getPrice();
		   modeids[i]= coursedetails.get(i).getModeid();
		   duration[i]= coursedetails.get(i).getDuration();
		}
		
		int onlineprice=0;
		int selfpacedprice=0;
		Long omode=null;
		Long smode=null;
		String durtn=" ";
		
		 JSONObject modeandprice=new JSONObject();
		if(coursedetails.size()>1){
			onlineprice=prices[0];
			selfpacedprice=prices[1];
			omode=modeids[0];
			smode=modeids[1];
			durtn=duration[0];
			modeandprice.put("mode1", "Online");
			 modeandprice.put("Onlineprice", onlineprice);
			 modeandprice.put("mode2", "SelfPaced");
			 modeandprice.put("Selfpacedprice", selfpacedprice);
			
		}
		else{
			onlineprice=prices[0];
			omode=modeids[0];
			durtn=duration[0];
			modeandprice.put("mode1", "Online");
			 modeandprice.put("Onlineprice", onlineprice);
		}
		
		 JSONObject obj=new JSONObject();
		 obj.put("cid",courseid);
		 obj.put("cname", cm.getCm_name());
		 obj.put("cmodeandprice", modeandprice);
		 obj.put("cduration", durtn);
		 
		 
			return new ResponseEntity<>(obj.toString(), HttpStatus.OK);
		 }
		 catch(JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 return new ResponseEntity<>("Exception", HttpStatus.BAD_REQUEST);
		 }
		
		
	
	}


	@RequestMapping(value = "/getAllCourses",method = RequestMethod.GET)
	public ResponseEntity<?> getAllCourses() {
		List<CourseMaster> cmaster = courseMasterservice.getAll();
		
		if (cmaster.isEmpty()) {
			logger.debug("Courses does not exists");
			return new ResponseEntity<List<CourseMaster>>(HttpStatus.NO_CONTENT);
		}
		
		List<AllCourseDetails> all=courseMasterserviceDao.getEntirecourseDetails();
		List<int[]> allprices = courseMasterserviceDao.Getallprices();
		String[] modearray={"Online","SelfPaced"};
		int[] arrayinsideallprices=null;
		String[] arrayinsidearrayofmodes=null;
		List<String[]> listofmodes = courseMasterserviceDao.Getallmodes();
		Long[] employeeID;
		
		List<DisplayAllCourses> displayallcourses = new ArrayList<DisplayAllCourses>();
		int[] ids={1,2};
		for(int i=0;i<all.size();i++){
			List<IdAndMode> first=new ArrayList<IdAndMode>();
			List<ModeAndPrice> second=new ArrayList<ModeAndPrice>();
			 arrayinsideallprices=null;
			 arrayinsidearrayofmodes = null;
		     arrayinsideallprices = allprices.get(i); 
		     arrayinsidearrayofmodes = listofmodes.get(i); 
		 
		   for(int j=0;j<arrayinsideallprices.length;j++){
			   //first we need to store mode id and modes list to the class IdAndMode
			   // same way we store modes and prices list of each course into class ModeAndPrice 
			   List<IdAndMode> idandmode= new ArrayList<IdAndMode>();
				List<ModeAndPrice> modeandprice= new ArrayList<ModeAndPrice>();
			     idandmode.add(new IdAndMode(ids[j],arrayinsidearrayofmodes[j]));
			     modeandprice.add(new ModeAndPrice(modearray[j],arrayinsideallprices[j]));
			     first.addAll(idandmode);
			      second.addAll( modeandprice);
		      
		       }  
		  
		    employeeID = courseassignedtoservicedao.getemployeeid(all.get(i).getC_id());
		    List<IdNameEmail> names = new ArrayList<IdNameEmail>();
		    //to display all the employee names assigned with this course
		   for(int k=0;k<employeeID.length;k++){
			 //names = employeeservicedao.empIdnameEmail(employeeID[k]);
			 names.addAll(k, employeeservicedao.empIdnameEmail(employeeID[k]));
		   }
		   
		   
		   displayallcourses.add(new DisplayAllCourses(all.get(i).getC_id(),all.get(i).getC_name(),
			first,second,all.get(i).getC_duration(),names,all.get(i).getC_createdDate(), all.get(i).getC_modifiedDate()));
		   //now we will store whole data in class DisplayAllCourses and print it
		   
		}
		return new ResponseEntity<>(displayallcourses, HttpStatus.OK);
			   
		
		}
		 
   

	@RequestMapping(value = "/deleteCourse/{id}", method = RequestMethod.DELETE)
	public boolean deleteCourse(@PathVariable("id") Long courseid) {
		CourseMaster coursemaster = courseMasterservice.getById(courseid);
		if (coursemaster == null) {
			logger.debug("Course with id " + courseid + " does not exists");
			return false;
		} else {
			
			int b=courseMasterserviceDao.deletecoursedetails(courseid);
			int a=courseMasterserviceDao.deletecoursemaster(courseid);
			int c= courseMasterserviceDao.deleteAllCourseDetails(courseid);
			return true;
		}
}
}




