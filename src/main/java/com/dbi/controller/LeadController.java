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

import com.dbi.model.CourseInterested;
import com.dbi.model.GetLeadDetails;
import com.dbi.model.Lead;
import com.dbi.model.LeadCourseInterested;
import com.dbi.model.LeadOwner;
import com.dbi.service.CourseMasterServiceDao;
import com.dbi.service.LeadCourseInterestedService;
import com.dbi.service.LeadCourseInterestedServiceDao;
import com.dbi.service.LeadOwnerService;
import com.dbi.service.LeadOwnerServiceDao;
import com.dbi.service.LeadService;
import com.dbi.service.LeadServiceDao;


@CrossOrigin
@RestController
@RequestMapping("/lead")
public class LeadController {

	final static Logger logger = Logger.getLogger(LeadController.class);

	@Autowired
	LeadService leadService;
	
	@Autowired
	LeadServiceDao leadServicedao;
	
	@Autowired
	LeadCourseInterestedService leadCourseinterestedService;
	
	@Autowired
	LeadCourseInterestedServiceDao leadCourseinterestedServicedao;
	
	@Autowired
	CourseMasterServiceDao courseMasterserviceDao;
	
	@Autowired
	LeadOwnerService leadOwnerservice;
	
	@Autowired
	LeadOwnerServiceDao leadOwnerserviceDao;
	
	
	
	@RequestMapping(value = "/addLead", method = RequestMethod.POST)
	public ResponseEntity<?> addLead(@RequestBody String details) {
		try {
		JSONObject jsnobj= new JSONObject(details);
		
		String leadName = jsnobj.getString("l_name");
		String email = jsnobj.getString("l_email");
		String contactNumber = jsnobj.getString("l_contactNumber");
		String country = jsnobj.getString("l_country");
		String comment = jsnobj.getString("l_comment");
		String source = jsnobj.getString("l_source");
		String createdDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String modifiedDate=createdDate;
		
		
		//now we need to check if the Lead with same emailID is present in database
		 
		String[] allLeademailIDs = null;
		allLeademailIDs = leadServicedao.getallleademailids();
		if(allLeademailIDs.length==0)//if no entry is present in lead table
		{
			Lead lead = new Lead(leadName,email,contactNumber,country,comment,source,createdDate,modifiedDate);
			leadService.save(lead);
			
			Long leadid = leadServicedao.Getleadid(email);
			JSONObject courseInterested = jsnobj.getJSONObject("l_courseInterested");
			JSONArray jsonarray = courseInterested.getJSONArray("record");
			String paymentDate;
			int c_price=0;
			for(int i=0;i<jsonarray.length();i++){
				if(jsonarray.getJSONObject(i).getInt("feePaid")==0){
					paymentDate = null;
				}
				else{
					paymentDate=createdDate;
				}
				JSONArray cmodeandprice = jsonarray.getJSONObject(i).getJSONArray("cmodeandprice");
				String c_mode = jsonarray.getJSONObject(i).getString("c_mode");
				for(int j=0;j<cmodeandprice.length();j++){
					String modeinCmodeandprice = cmodeandprice.getJSONObject(j).getString("mode");
					if(c_mode.equals(modeinCmodeandprice)){
						c_price = cmodeandprice.getJSONObject(j).getInt("price");
					}
				}
				
				Long c_id= courseMasterserviceDao.Getcourseid(jsonarray.getJSONObject(i).getString("c_name").toString());
				LeadCourseInterested leadCourseinterested = new LeadCourseInterested(leadid,c_id,
						jsonarray.getJSONObject(i).getString("c_name").toString(),
						jsonarray.getJSONObject(i).getString("c_mode").toString(),c_price,jsonarray.getJSONObject(i).getInt("feeOffered"),
						jsonarray.getJSONObject(i).getInt("feePaid"),paymentDate,
						jsonarray.getJSONObject(i).getInt("pendingPayment"),
						jsonarray.getJSONObject(i).getString("leadStatus").toString(),
						createdDate,modifiedDate);
				leadCourseinterestedService.save(leadCourseinterested);
				
				// inside assignedBy an array of {id,name,email} will be there
				// because for one course two Owners will be there(sales and marketing)
				JSONArray assignedBy = jsonarray.getJSONObject(i).getJSONArray("assignedBy");
				
				for(int k=0;k<assignedBy.length();k++){
			
					LeadOwner leadOwner = new LeadOwner(c_id,leadid,assignedBy.getJSONObject(k).getLong("id"),
							assignedBy.getJSONObject(k).getString("name"),assignedBy.getJSONObject(k).getString("email"),createdDate);
					leadOwnerservice.save(leadOwner);
					
			}
			
			}
			return new ResponseEntity<>(jsnobj.toString(), HttpStatus.CREATED);
			
		}
		
		
		else{
				for(int x=0;x<allLeademailIDs.length;x++){
					if(email.equals(allLeademailIDs[x])){
						//if lead with same emailID is present inside database
						Lead lead = leadServicedao.getLeadbyEmail(email);
						Long leadid = lead.getL_id();
					    int a = leadServicedao.Updateleadtable(leadName, email, contactNumber, country, comment,source, modifiedDate, leadid);
				 
						Long[] id = leadCourseinterestedServicedao.GetidofLCI(leadid);
						JSONObject courseInterested = jsnobj.getJSONObject("l_courseInterested");
						JSONArray jsonarray = courseInterested.getJSONArray("record");
						String paymentDate;
						int c_price = 0;
						String createddateoflead = lead.getL_createdDate();
				
						for(int j=0;j<jsonarray.length();j++){
					
							if(jsonarray.getJSONObject(j).getInt("feePaid")==0){
								paymentDate = null;
							}
							else{
								paymentDate=modifiedDate;
							}
							JSONArray cmodeandprice = jsonarray.getJSONObject(j).getJSONArray("cmodeandprice");
							String c_mode = jsonarray.getJSONObject(j).getString("c_mode");
							for(int k=0;k<cmodeandprice.length();k++){
								String modeinCmodeandprice = cmodeandprice.getJSONObject(k).getString("mode");
								if(c_mode.equals(modeinCmodeandprice)){
									c_price = cmodeandprice.getJSONObject(k).getInt("price");
								}
							}
					
					Long c_id= courseMasterserviceDao.Getcourseid(jsonarray.getJSONObject(j).getString("c_name").toString());
					LeadCourseInterested leadCourseinterested = new LeadCourseInterested(leadid,c_id,
							jsonarray.getJSONObject(j).getString("c_name").toString(),
							jsonarray.getJSONObject(j).getString("c_mode").toString(),c_price,jsonarray.getJSONObject(j).getInt("feeOffered"),
							jsonarray.getJSONObject(j).getInt("feePaid"),paymentDate,
							jsonarray.getJSONObject(j).getInt("pendingPayment"),
							jsonarray.getJSONObject(j).getString("leadStatus").toString(),
							createdDate,modifiedDate);
					leadCourseinterestedService.save(leadCourseinterested);
					// inside assignedBy an array of {id,name,email} will be there
					// because for one course two Owners will be there(sales and marketing)
					JSONArray assignedBy = jsonarray.getJSONObject(j).getJSONArray("assignedBy");
					
					for(int p=0;p<assignedBy.length();p++){
				
						LeadOwner leadOwner = new LeadOwner(c_id,leadid,assignedBy.getJSONObject(p).getLong("id"),
								assignedBy.getJSONObject(p).getString("name"),assignedBy.getJSONObject(p).getString("email"),createdDate);
						leadOwnerservice.save(leadOwner);
						
					
					
			}

				
				}
						return new ResponseEntity<>(jsnobj.toString(), HttpStatus.CREATED);
		}
			else{
				
		Lead lead = new Lead(leadName,email,contactNumber,country,comment,source,createdDate,modifiedDate);
		leadService.save(lead);
		
		Long leadid = leadServicedao.Getleadid(email);
		JSONObject courseInterested = jsnobj.getJSONObject("l_courseInterested");
		JSONArray jsonarray = courseInterested.getJSONArray("record");
		String paymentDate;
		int c_price=0;
		for(int i=0;i<jsonarray.length();i++){
			if(jsonarray.getJSONObject(i).getInt("feePaid")==0){
				paymentDate = null;
			}
			else{
				paymentDate=createdDate;
			}
			JSONArray cmodeandprice = jsonarray.getJSONObject(i).getJSONArray("cmodeandprice");
			String c_mode = jsonarray.getJSONObject(i).getString("c_mode");
			for(int j=0;j<cmodeandprice.length();j++){
				String modeinCmodeandprice = cmodeandprice.getJSONObject(j).getString("mode");
				if(c_mode.equals(modeinCmodeandprice)){
					c_price = cmodeandprice.getJSONObject(j).getInt("price");
				}
			}
			
			Long c_id= courseMasterserviceDao.Getcourseid(jsonarray.getJSONObject(i).getString("c_name").toString());
			LeadCourseInterested leadCourseinterested = new LeadCourseInterested(leadid,c_id,
					jsonarray.getJSONObject(i).getString("c_name").toString(),
					jsonarray.getJSONObject(i).getString("c_mode").toString(),c_price,jsonarray.getJSONObject(i).getInt("feeOffered"),
					jsonarray.getJSONObject(i).getInt("feePaid"),paymentDate,
					jsonarray.getJSONObject(i).getInt("pendingPayment"),
					jsonarray.getJSONObject(i).getString("leadStatus").toString(),
					createdDate,modifiedDate);
			leadCourseinterestedService.save(leadCourseinterested);
			// inside assignedBy an array of {id,name,email} will be there
			// because for one course two Owners will be there(sales and marketing)
			JSONArray assignedBy = jsonarray.getJSONObject(i).getJSONArray("assignedBy");
			
			for(int k=0;k<assignedBy.length();k++){
		
				LeadOwner leadOwner = new LeadOwner(c_id,leadid,assignedBy.getJSONObject(k).getLong("id"),
						assignedBy.getJSONObject(k).getString("name"),assignedBy.getJSONObject(k).getString("email"),createdDate);
				leadOwnerservice.save(leadOwner);
			}
			
			
		}
		return new ResponseEntity<>(jsnobj.toString(), HttpStatus.CREATED);
			}
	}
		
		
		}	
		return new ResponseEntity<>( HttpStatus.CREATED);
		}
		catch (JSONException e) {
			 //TODO Auto-generated catch block
		 		e.printStackTrace();
		 		return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		 	}
		
	}
	
	
	
	@RequestMapping(value = "/updateLead", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLead(@RequestBody String details) {
		
		
			JSONObject jsnobj;
			try {
			jsnobj = new JSONObject(details);
			Lead existingLead = leadService.getById(jsnobj.getLong("l_id"));
				if (existingLead == null) {
					logger.debug("Lead with id " +jsnobj.getLong("l_id")  + " does not exists");
					return new ResponseEntity<>("no lead found with that id", HttpStatus.NOT_FOUND);
				} 
			//else {
			
				String modifiedDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String name = jsnobj.getString("l_name");
				String email = jsnobj.getString("l_email");
				String contactNumber = jsnobj.getString("l_contactNumber");
				String country = jsnobj.getString("l_country");
				String comment = jsnobj.getString("l_comment");
				String source = jsnobj.getString("l_source");
				Long leadid = jsnobj.getLong("l_id");
				
				int del=leadCourseinterestedServicedao.deleteLCI(leadid); 
				int d = leadOwnerserviceDao.deleteLeadOwner(leadid);
				
				int a = leadServicedao.Updateleadtable(name, email, contactNumber, country, comment,source, modifiedDate, leadid);
				Long[] id = leadCourseinterestedServicedao.GetidofLCI(leadid);
				JSONObject courseInterested = jsnobj.getJSONObject("l_courseInterested");
				JSONArray jsonarray = courseInterested.getJSONArray("record");
				String paymentDate;
				int c_price = 0;
				String createddateoflead = existingLead.getL_createdDate();
				
			
				for(int i=0;i<jsonarray.length();i++){
					
					if(jsonarray.getJSONObject(i).getInt("feePaid")==0){
						paymentDate = null;
					}
					else{
						paymentDate=modifiedDate;
					}
					JSONArray cmodeandprice = jsonarray.getJSONObject(i).getJSONArray("cmodeandprice");
					String c_mode = jsonarray.getJSONObject(i).getString("c_mode");
					for(int j=0;j<cmodeandprice.length();j++){
						String modeinCmodeandprice = cmodeandprice.getJSONObject(j).getString("mode");
						if(c_mode.equals(modeinCmodeandprice)){
							c_price = cmodeandprice.getJSONObject(j).getInt("price");
						}
					}
					
				
					Long c_id= courseMasterserviceDao.Getcourseid(jsonarray.getJSONObject(i).getString("c_name").toString());
					LeadCourseInterested leadCourseinterested = new LeadCourseInterested(leadid,c_id,
							jsonarray.getJSONObject(i).getString("c_name").toString(),
							jsonarray.getJSONObject(i).getString("c_mode").toString(),c_price,jsonarray.getJSONObject(i).getInt("feeOffered"),
							jsonarray.getJSONObject(i).getInt("feePaid"),paymentDate,
							jsonarray.getJSONObject(i).getInt("pendingPayment"),
							jsonarray.getJSONObject(i).getString("leadStatus").toString(),
							createddateoflead,modifiedDate);
					leadCourseinterestedService.save(leadCourseinterested);
					
					
				
					
					// inside assignedBy an array of {id,name,email} will be there
					// because for one course two Owners will be there(sales and marketing)
					JSONArray assignedBy = jsonarray.getJSONObject(i).getJSONArray("assignedBy");
					
					for(int k=0;k<assignedBy.length();k++){
						
						LeadOwner leadOwner = new LeadOwner(c_id,leadid,assignedBy.getJSONObject(k).getLong("ownerId"),
								assignedBy.getJSONObject(k).getString("ownerName"),assignedBy.getJSONObject(k).getString("ownerEmail"),createddateoflead);
						leadOwnerservice.save(leadOwner);
				
				}
				}
				return new ResponseEntity<>(jsnobj.toString(),HttpStatus.OK);
			//}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(value = "/getLeadById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getLead(@PathVariable("id") Long l_id) {
		Lead lead = leadService.getById(l_id);
		if (lead == null) {
			logger.debug("Lead with id " + l_id + " does not exists");
			return new ResponseEntity<Lead>(HttpStatus.NOT_FOUND);
		}
		String l_name = lead.getL_name();
		String l_email = lead.getL_email();
		String l_contactNumber = lead.getL_contactNumber();
		String l_country = lead.getL_country();
		String l_comment = lead.getL_comment();
		String l_source = lead.getL_source();
		String l_createdDate = lead.getL_createdDate();
		String l_modifiedDate = lead.getL_modifiedDate();
		List<LeadCourseInterested> lci = new ArrayList<LeadCourseInterested>();
		lci = leadCourseinterestedServicedao.getLeadCourseInterested(l_id);
		List<LeadOwner> leadOwner = new ArrayList<LeadOwner>();
		
		List<CourseInterested> courseInterested = new ArrayList<CourseInterested>();
		for(int j=0;j<lci.size();j++){
			Long courseid = lci.get(j).getC_id();// to get first courseid among multiple courses lead is interested in
			leadOwner = leadOwnerserviceDao.getLeadowner(l_id, courseid);// to get employees who are assigned with this course
			CourseInterested ci = new CourseInterested(lci.get(j).getLeadId(),
													   lci.get(j).getC_id(),
													   lci.get(j).getC_name(),
													   lci.get(j).getMode(),
													   lci.get(j).getCourseFee(),
													   lci.get(j).getFeeOffered(),
													   lci.get(j).getFeePaid(),
													   lci.get(j).getPaymentDate(),
													   lci.get(j).getPendingPayment(),
													   lci.get(j).getLeadStatus(),
													   leadOwner);
			courseInterested.add(j, ci);
		}
		
		List<GetLeadDetails> getLeaddetails = new ArrayList<GetLeadDetails>();
		getLeaddetails.add(new GetLeadDetails(l_id,l_name,l_email,l_contactNumber,l_country,l_comment,l_source,courseInterested,l_createdDate,l_modifiedDate));
		return new ResponseEntity<>(getLeaddetails, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/getAllLeads",method = RequestMethod.GET)
	public ResponseEntity<?> getAllLeads() {
		List<Lead> leads = leadService.getAll();
		if (leads.isEmpty()) {
			logger.debug("Leads does not exists");
			return new ResponseEntity<List<Lead>>(HttpStatus.NO_CONTENT);
		}
		
		Long[] leadid = leadServicedao.getallleadid();
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
			courseInterested.add(j, ci);
		}
		
			getleaddetails.add(new GetLeadDetails(lead.getL_id(),lead.getL_name(),lead.getL_email(),lead.getL_contactNumber(),
					lead.getL_country(),lead.getL_comment(),lead.getL_source(),courseInterested,
					lead.getL_createdDate(),lead.getL_modifiedDate()));
		}
		return new ResponseEntity<>(getleaddetails, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/deleteLead/{id}", method = RequestMethod.DELETE)
	public boolean deleteLead(@PathVariable("id") Long leadid) {
		Lead lead = leadService.getById(leadid);
		if (lead == null) {
			logger.debug("Lead with id " + leadid + " does not exists");
			return false;
		} else {
			leadService.delete(leadid);
			int a=leadCourseinterestedServicedao.deleteLCI(leadid);
			int d = leadOwnerserviceDao.deleteLeadOwner(leadid);
			logger.debug("Lead with id " + leadid + " deleted");
			return true;
		}
  }
	
	
	
	
	
	
	
	
	// to check if Lead is already existing (can be one who is already enrolled
	//for a course or interested in some course)
	@RequestMapping(value = "/checkLeadbyEmail",method = RequestMethod.POST)
	public ResponseEntity<?> checkLeadbyEmail(@RequestBody String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String email = obj.getString("email");
			
			Lead lead = leadServicedao.getLeadbyEmail(email);
			if (lead == null) {
				logger.debug("Lead with "+ email + " does not exists");
				lead = null;
				return new ResponseEntity<>(lead,HttpStatus.NOT_FOUND);
			}
			List<GetLeadDetails> getleaddetails = new ArrayList<GetLeadDetails>();
			List<LeadCourseInterested> leadcourseinterested = leadCourseinterestedServicedao.getleadcourseinterestedbyid(lead.getL_id());
			
			List<LeadOwner> leadOwner = new ArrayList<LeadOwner>();
		
		List<CourseInterested> courseInterested = new ArrayList<CourseInterested>();
		for(int j=0;j<leadcourseinterested.size();j++){
			Long courseid = leadcourseinterested.get(j).getC_id();// to get first courseid among multiple courses lead is interested in
			leadOwner = leadOwnerserviceDao.getLeadowner(lead.getL_id(), courseid);// to get employees who are assigned with this course
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
			courseInterested.add(j, ci);
		}
		
			getleaddetails.add(new GetLeadDetails(lead.getL_id(),lead.getL_name(),lead.getL_email(),lead.getL_contactNumber(),
					lead.getL_country(),lead.getL_comment(),lead.getL_source(),courseInterested,
					lead.getL_createdDate(),lead.getL_modifiedDate()));
			
		
		return new ResponseEntity<>(getleaddetails, HttpStatus.OK);

			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
		
     



















