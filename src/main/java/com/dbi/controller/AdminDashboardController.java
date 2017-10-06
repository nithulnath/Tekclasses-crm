

package com.dbi.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;











import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbi.model.EmailNameTarget;
import com.dbi.model.Graph;
import com.dbi.model.GraphValues;
import com.dbi.model.Lead;
import com.dbi.model.NameTarget;
import com.dbi.model.Performance;
import com.dbi.service.LeadServiceDao;
import com.dbi.service.TargetServiceDao;

@CrossOrigin
@RestController
@RequestMapping("/lead")
public class AdminDashboardController {

	final static Logger logger = Logger.getLogger(AdminDashboardController.class);

	@Autowired
	LeadServiceDao leadServicedao;
	@Autowired
	TargetServiceDao targetServicedao;
	
	// to get number of leads between two selected dates
	@RequestMapping(value = "/getNewLeads",method = RequestMethod.POST)
	public ResponseEntity<?> getNumberofLeads(@RequestBody String json) {
		//the String json will be containing { "from":"2017-01-01","to":"2017-02-01"} this we have to convert to JSONObject
		//so that we can access "from" and "to" values separately 
		JSONObject jsonObject;
		Long leads;
		try {
			jsonObject = new JSONObject(json);
			leads=leadServicedao.getleadcount(jsonObject.get("from").toString(),jsonObject.get("to").toString());
			if (leads == null) {
				logger.debug("No leads to display");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			 
			logger.debug("Total leads " +leads);
			return new ResponseEntity<>(leads, HttpStatus.OK);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		 
	}
	
	
	
	
	
	//to get this month's lead count by default
	@RequestMapping(value = "/getDefaultLeadcount",method = RequestMethod.GET)
	public ResponseEntity<Long>getdefaultleadcount() {
		Long defaultleadcount=leadServicedao.getdefaultleadcount();
		if (defaultleadcount == null) {
			logger.debug("Nothing to display");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		logger.debug("this month defaults" + defaultleadcount);
		return new ResponseEntity<>(defaultleadcount, HttpStatus.OK);
		
	}
	
	
	
	
	
	//to get number of enrolled leads between two selected dates
	@RequestMapping(value = "/getEnrolledLeads",method = RequestMethod.POST)
	public ResponseEntity<?> getNumberofEnrolledleads(@RequestBody String json) {
		JSONObject jsonObject;
		Long leadscount;
		try {
			jsonObject = new JSONObject(json);
			leadscount = leadServicedao.getenrolledleadcount(jsonObject.get("from").toString(),jsonObject.get("to").toString());
			if (leadscount == null) {
				logger.debug("No leads to display");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Total leads " + leadscount);
			return new ResponseEntity<>(leadscount, HttpStatus.OK);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
    
	
	
	
	
	// to get this month's enrolled lead count by default
	@RequestMapping(value = "/getDefaultenrolledLeadcount",method = RequestMethod.GET)
	public ResponseEntity<Long>defaultgetenrolledleadcount() {
		Long defaultenrolledleadcount=leadServicedao.defaultgetenrolledleadcount();
		if (defaultenrolledleadcount == null) {
			logger.debug("Nothing to display");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.debug("this month defaults" + defaultenrolledleadcount);
		return new ResponseEntity<>(defaultenrolledleadcount, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = "/getRevenue",method = RequestMethod.POST)
	public ResponseEntity<?> getAllrevenue(@RequestBody String json) {
		Long revenue;
		try {
			JSONObject jsonObject = new JSONObject(json);
			revenue = leadServicedao.getRevenue(jsonObject.get("from").toString(),jsonObject.get("to").toString());
			if (revenue == null) {
				logger.debug("No revenue to display");
				return new ResponseEntity<>(0,HttpStatus.OK);
			}
			logger.debug(" Revenue " + revenue);
			return new ResponseEntity<>(revenue, HttpStatus.OK);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
	@RequestMapping(value = "/getDefaultrevenue",method = RequestMethod.GET)
	public ResponseEntity<?>defaultgetrevenue() {
		Long defaultrevenue=leadServicedao.defaultgetRevenue();
		if (defaultrevenue == null) {
			logger.debug("Nothing to display");
			return new ResponseEntity<>(0,HttpStatus.OK);
		}
		logger.debug("this month defaults" + defaultrevenue);
		return new ResponseEntity<>(defaultrevenue, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = "/getConversion",method = RequestMethod.POST)
	public ResponseEntity<Double> getConversion(@RequestBody String json) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			double result=leadServicedao.Conversion(jsonObject.get("from").toString(),jsonObject.get("to").toString());
			logger.debug(result);
			return new ResponseEntity<>(result,HttpStatus.OK);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
	@RequestMapping(value = "/getDefaultconversion",method = RequestMethod.GET)
	public ResponseEntity<Double> getDefaultConversion() {
			double result=leadServicedao.defaultConversion();
			if(result>=0){
			logger.debug(result);
			return new ResponseEntity<>(result,HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
	}
	
	
	
	
	
	//to get all marketing performance of this month, that is performance of all marketing employees
	
	/*
	@RequestMapping(value = "/getDefaultmarketingPerformance",method = RequestMethod.GET)
	public ResponseEntity<?>defaultgetmarketingperformence() {
		
		List<Performance> performance = new ArrayList<Performance>();
		List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
			emailnametarget = leadServicedao.defaultEmailNameTarget();
			if(emailnametarget.isEmpty()){
				
				List<NameTarget> nametarget = leadServicedao.nameAndtargetOfmarketing();
				for(int i=0;i<nametarget.size();i++){
					performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
				}
				
				return new ResponseEntity<>(performance, HttpStatus.OK);
			}
			
		List<Long> achievedtarget = new ArrayList<Long>();
		for(int i=0;i<emailnametarget.size();i++){
			achievedtarget.addAll(i,leadServicedao.defaultAchieved(emailnametarget.get(i).getEmail()));
			int achieved= achievedtarget.get(i).intValue();
			performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
		}
		
		Collections.sort(performance);
		return new ResponseEntity<>(performance, HttpStatus.OK);
		
	}
	*/
	
	
	
	
	
	
	//get default marketing performance of first employee, having more number of achieved 
	
	@RequestMapping(value = "/getDefaultmarketingPerformanceofFirst",method = RequestMethod.GET)
	public ResponseEntity<?>defaultgetmarketingperformence() {
		
		List<Performance> performance = new ArrayList<Performance>();
		List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
			emailnametarget = leadServicedao.defaultEmailNameTarget();
			if(emailnametarget.isEmpty()){
				
				List<NameTarget> nametarget = leadServicedao.nameAndtargetOfmarketing();
				if(nametarget.isEmpty()){
					performance.add(null);
					return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
				}
				else{
				for(int i=0;i<nametarget.size();i++){
					performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
				}
				
				return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
				}
			}
			
		List<Long> achievedtarget = new ArrayList<Long>();
		for(int i=0;i<emailnametarget.size();i++){
			achievedtarget.addAll(i,leadServicedao.defaultAchieved(emailnametarget.get(i).getEmail()));
			int achieved= achievedtarget.get(i).intValue();
			performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
		}
		
		Collections.sort(performance);
		return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
		
	}
	
	
	
	
	
	
	//get default marketing performance of Second employee
	
		@RequestMapping(value = "/getDefaultmarketingPerformanceofSecond",method = RequestMethod.GET)
		public ResponseEntity<?>defaultgetmarketingperformencesecond() {
			
			List<Performance> performance = new ArrayList<Performance>();
			List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
				emailnametarget = leadServicedao.defaultEmailNameTarget();
			List<NameTarget> nametarget = leadServicedao.nameAndtargetOfmarketing();
			if(emailnametarget.isEmpty()){
				
				if(nametarget.isEmpty()){
					performance.add(null);
					return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
					}
				else{
						for(int i=0;i<nametarget.size();i++){
							performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
							}
									
						return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
					}
				}
				
					List<Long> achievedtarget = new ArrayList<Long>();
					for(int i=0;i<emailnametarget.size();i++){
						achievedtarget.addAll(i,leadServicedao.defaultAchieved(emailnametarget.get(i).getEmail()));
						int achieved= achievedtarget.get(i).intValue();
						performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
						}
										
						Collections.sort(performance);
						if(performance.size()>1)
						return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
						
						else{

							for(int i=0;i<nametarget.size();i++){
								performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
								}
										
							return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
							
						}
						 
						 
						}
									
			
		
		
		
		
		
		
		//get default marketing performance of Third employee
		
			@RequestMapping(value = "/getDefaultmarketingPerformanceofThird",method = RequestMethod.GET)
			public ResponseEntity<?>defaultgetmarketingperformencethird() {
				
				List<Performance> performance = new ArrayList<Performance>();
				List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
					emailnametarget = leadServicedao.defaultEmailNameTarget();
						List<NameTarget> nametarget = leadServicedao.nameAndtargetOfmarketing();
						
						
						if(emailnametarget.isEmpty()){
							
							if(nametarget.isEmpty()){
								performance.add(null);
								return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
								}
							else{
									for(int i=0;i<nametarget.size();i++){
										if(nametarget.size()<3){
											performance.add(null);
											return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
										}
										else{
										performance.add(new Performance(nametarget.get(2).getName(),nametarget.get(2).getTarget(),0));
										return new ResponseEntity<>(performance, HttpStatus.OK);
										}
												
									
								}
							}
						}
								List<Long> achievedtarget = new ArrayList<Long>();
								for(int i=0;i<emailnametarget.size();i++){
									achievedtarget.addAll(i,leadServicedao.defaultAchieved(emailnametarget.get(i).getEmail()));
									int achieved= achievedtarget.get(i).intValue();
									performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
									}
													
									Collections.sort(performance);
									if(performance.size()>2)
									return new ResponseEntity<>(performance.get(2), HttpStatus.OK);
									
									else{

										for(int i=0;i<nametarget.size();i++){
											performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
											}
													
										return new ResponseEntity<>(performance.get(2), HttpStatus.OK);
										
									}
									 
									 
			}
			
		
			
			
			
	
	// get marketing performance of all employees
	/*
	
	@RequestMapping(value = "/getMarketingperformance",method = RequestMethod.POST)
	public ResponseEntity<?> getMarketingperformance(@RequestBody String json) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			
			List<Performance> performance = new ArrayList<Performance>();
			List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
				emailnametarget = targetServicedao.EmailNameTarget_betweenSelectedmonths(jsonObject.getString("from").toString(),
						jsonObject.getString("to").toString());
				
				if(emailnametarget.isEmpty()){
					List<NameTarget> nametarget = targetServicedao.nameAndtargetOfmarketing_betweenSelectedmonths(jsonObject.getString("from").toString(),
							jsonObject.getString("to").toString());
					for(int i=0;i<nametarget.size();i++){
						performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
					}
					
					return new ResponseEntity<>(performance, HttpStatus.OK);
				}
				
			List<Long> achievedtarget = new ArrayList<Long>();
			for(int i=0;i<emailnametarget.size();i++){
				achievedtarget.addAll(i,targetServicedao.Achieved_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
						jsonObject.getString("to").toString()));
				int achieved= achievedtarget.get(i).intValue();
				performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
			}
			
			Collections.sort(performance);
			return new ResponseEntity<>(performance, HttpStatus.OK);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	*/
			
			
			
			
			// marketing performance of first employee
			@RequestMapping(value = "/getMarketingperformanceOffirst",method = RequestMethod.POST)
			public ResponseEntity<?> getMarketingperformance(@RequestBody String json) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);
					
					List<Performance> performance = new ArrayList<Performance>();
					List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
						emailnametarget = targetServicedao.EmailNameTarget_betweenSelectedmonths(jsonObject.getString("from").toString(),
								jsonObject.getString("to").toString());
						
						if(emailnametarget.isEmpty()){
							List<NameTarget> nametarget = targetServicedao.nameAndtargetOfmarketing_betweenSelectedmonths(jsonObject.getString("from").toString(),
									jsonObject.getString("to").toString());
							if(nametarget.size()>0){
								for(int i=0;i<nametarget.size();i++){
									performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
								}
								
								return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
							}
							else{
								performance.add(null);
								return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
							}
						}
						
					List<Long> achievedtarget = new ArrayList<Long>();
					for(int i=0;i<emailnametarget.size();i++){
						achievedtarget.addAll(i,targetServicedao.Achieved_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
								jsonObject.getString("to").toString()));
						int achieved= achievedtarget.get(i).intValue();
						performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
					}
					
					Collections.sort(performance);
					return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity<String>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
				}
				
			}
			
			
			
			
			
	// marketing performance of second employee
	@RequestMapping(value = "/getMarketingperformanceOfsecond",method = RequestMethod.POST)
	public ResponseEntity<?> getMarketingperformancesecond(@RequestBody String json) {
	JSONObject jsonObject;
	try {
			jsonObject = new JSONObject(json);
								
			List<Performance> performance = new ArrayList<Performance>();
			List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
			emailnametarget = targetServicedao.EmailNameTarget_betweenSelectedmonths(jsonObject.getString("from").toString(),
			jsonObject.getString("to").toString());
									
			if(emailnametarget.size()<2){
				List<NameTarget> nametarget = targetServicedao.nameAndtargetOfmarketing_betweenSelectedmonths(jsonObject.getString("from").toString(),
				jsonObject.getString("to").toString());
				if(nametarget.size()>1){
										
					if(emailnametarget.get(0).getName().equals(nametarget.get(0).getName())){
						
						performance.add(new Performance(nametarget.get(1).getName(),nametarget.get(1).getTarget(),0));
						return new ResponseEntity<>(performance, HttpStatus.OK);
						
					}
					else {
						performance.add(new Performance(nametarget.get(0).getName(),nametarget.get(0).getTarget(),0));
						return new ResponseEntity<>(performance, HttpStatus.OK);
					}
						
										
			}
				else{
					 performance.add(null);
					return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
					}
			}
									
			List<Long> achievedtarget = new ArrayList<Long>();
			for(int i=0;i<emailnametarget.size();i++){
			achievedtarget.addAll(i,targetServicedao.Achieved_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
			jsonObject.getString("to").toString()));
			int achieved= achievedtarget.get(i).intValue();
			performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
		}
								
			Collections.sort(performance);
			return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
								
							
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			return new ResponseEntity<String>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
			}
							
	}
					
	
						
						
						
						
						
						
// marketing performance of third employee
@RequestMapping(value = "/getMarketingperformanceOfthird",method = RequestMethod.POST)
public ResponseEntity<?> getMarketingperformancethird(@RequestBody String json) {
JSONObject jsonObject;
try {
		jsonObject = new JSONObject(json);
								
		List<Performance> performance = new ArrayList<Performance>();
		List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
		emailnametarget = targetServicedao.EmailNameTarget_betweenSelectedmonths(jsonObject.getString("from").toString(),
						jsonObject.getString("to").toString());
									
			if(emailnametarget.size()<3){
			  List<NameTarget> nametarget = targetServicedao.nameAndtargetOfmarketing_betweenSelectedmonths(jsonObject.getString("from").toString(),
				jsonObject.getString("to").toString());
				if(nametarget.size()>2){
					
					if((emailnametarget.get(0).getName().equals(nametarget.get(0).getName())) 
							|| 
							(emailnametarget.get(0).getName().equals(nametarget.get(1).getName()))
							||
							(emailnametarget.get(1).getName().equals(nametarget.get(0).getName()))
							|| 
							(emailnametarget.get(1).getName().equals(nametarget.get(1).getName()))){
						
						performance.add(new Performance(nametarget.get(2).getName(),nametarget.get(2).getTarget(),0));
						return new ResponseEntity<>(performance, HttpStatus.OK);
						
					}
					
					else if((emailnametarget.get(0).getName().equals(nametarget.get(1).getName())) 
							|| 
							(emailnametarget.get(0).getName().equals(nametarget.get(2).getName()))
							||
							(emailnametarget.get(1).getName().equals(nametarget.get(1).getName()))
							|| 
							(emailnametarget.get(1).getName().equals(nametarget.get(2).getName()))){
						
						performance.add(new Performance(nametarget.get(0).getName(),nametarget.get(0).getTarget(),0));
						return new ResponseEntity<>(performance, HttpStatus.OK);
						
					}
					
					else if((emailnametarget.get(0).getName().equals(nametarget.get(0).getName())) 
							|| 
							(emailnametarget.get(0).getName().equals(nametarget.get(2).getName()))
							||
							(emailnametarget.get(1).getName().equals(nametarget.get(0).getName()))
							|| 
							(emailnametarget.get(1).getName().equals(nametarget.get(2).getName()))){
						
						performance.add(new Performance(nametarget.get(1).getName(),nametarget.get(1).getTarget(),0));
						return new ResponseEntity<>(performance, HttpStatus.OK);
						
					}
					
					
						}
					else{
							performance.add(null);
							return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
								}
						}
									
				List<Long> achievedtarget = new ArrayList<Long>();
				for(int i=0;i<emailnametarget.size();i++){
				achievedtarget.addAll(i,targetServicedao.Achieved_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
											jsonObject.getString("to").toString()));
				int achieved= achievedtarget.get(i).intValue();
				performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
			}
								
			Collections.sort(performance);
			return new ResponseEntity<>(performance.get(2), HttpStatus.OK);
								
								
			} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity<String>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
				}
							
	}
						
						
			
			
		
						
	//get default sales performance of all employees
	/*					
	@RequestMapping(value = "/getDefaultsalesPerformance",method = RequestMethod.GET)
	public ResponseEntity<?>defaultgetSalesperformence() {
		List<Performance> performance = new ArrayList<Performance>();
		List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
			emailnametarget = leadServicedao.defaultEmailNameTargetforsales();
			if(emailnametarget.isEmpty()){
				
				List<NameTarget> nametarget = leadServicedao.nameAndtargetOfsales();
				for(int i=0;i<nametarget.size();i++){
					performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
				}
				
				return new ResponseEntity<>(performance, HttpStatus.OK);
			}
			
		List<Long> achievedtarget = new ArrayList<Long>();
		for(int i=0;i<emailnametarget.size();i++){
			achievedtarget.addAll(i,leadServicedao.defaultAchievedforSales(emailnametarget.get(i).getEmail()));
			int achieved= achievedtarget.get(i).intValue();
			performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
		}
		
		Collections.sort(performance);
		return new ResponseEntity<>(performance, HttpStatus.OK);
		
	}
	*/
						
					
// default sales performance of first employee	
						
	@RequestMapping(value = "/getDefaultsalesPerformanceofFirst",method = RequestMethod.GET)
	public ResponseEntity<?>defaultgetSalesperformence() {
	List<Performance> performance = new ArrayList<Performance>();
	List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
	emailnametarget = leadServicedao.defaultEmailNameTargetforsales();
	if(emailnametarget.isEmpty() || emailnametarget==null){
									
		List<NameTarget> nametarget = leadServicedao.nameAndtargetOfsales();
		if(nametarget.isEmpty() || nametarget==null ){
					performance.add(null);
					return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
							}
		else{
				for(int i=0;i<nametarget.size();i++){
					performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
						}
									
				return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
					}
			}
								
				List<Long> achievedtarget = new ArrayList<Long>();
				for(int i=0;i<emailnametarget.size();i++){
					achievedtarget.addAll(i,leadServicedao.defaultAchievedforSales(emailnametarget.get(i).getEmail()));
					int achieved= achievedtarget.get(i).intValue();
					performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
				}
							
		Collections.sort(performance);
		return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
							
	}
	

	
	
	
	
	
	
	// default sales performance of second employee	
							
	@RequestMapping(value = "/getDefaultsalesPerformanceofSecond",method = RequestMethod.GET)
	public ResponseEntity<?>defaultgetSalesperformencesecond() {
	List<Performance> performance = new ArrayList<Performance>();
	List<EmailNameTarget> emailnametarget = leadServicedao.defaultEmailNameTargetforsales();
	List<NameTarget> nametarget = leadServicedao.nameAndtargetOfsales();
	if(emailnametarget.isEmpty() || emailnametarget==null){
		
		if(nametarget.isEmpty() || nametarget==null){
			performance.add(null);
			return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
			}
		else if(nametarget.size()<=1){
			performance.add(null);
			return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
			
		}
		else{
				for(int i=0;i<nametarget.size();i++){
					performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
					}
							
				return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
			}
		}
		
			List<Long> achievedtarget = new ArrayList<Long>();
			for(int i=0;i<emailnametarget.size();i++){
				achievedtarget.addAll(i,leadServicedao.defaultAchievedforSales(emailnametarget.get(i).getEmail()));
				int achieved= achievedtarget.get(i).intValue();
				performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
				}
								
				Collections.sort(performance);
				if(performance.size()>1)
				return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
				
				else{

					for(int i=0;i<nametarget.size();i++){
						performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
						}
								
					return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
					
				}
				 
				 
				}
							
	
			
	
	
	
	
	
	// default sales performance of third employee	
								
  @RequestMapping(value = "/getDefaultsalesPerformanceofThird",method = RequestMethod.GET)
  public ResponseEntity<?>defaultgetSalesperformencethird() {
  List<Performance> performance = new ArrayList<Performance>();
  List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
  emailnametarget = leadServicedao.defaultEmailNameTargetforsales();
  List<NameTarget> nametarget = leadServicedao.nameAndtargetOfsales();
  if(emailnametarget.isEmpty() || emailnametarget==null){
		
		if(nametarget.isEmpty() || nametarget==null){
			performance.add(null);
			return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
			}
		else{
				for(int i=0;i<nametarget.size();i++){
					if(nametarget.size()<3){
						performance.add(null);
						return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
					}
					else{
					performance.add(new Performance(nametarget.get(2).getName(),nametarget.get(2).getTarget(),0));
					return new ResponseEntity<>(performance, HttpStatus.OK);
					}
							
				
			}
		}
	}
			List<Long> achievedtarget = new ArrayList<Long>();
			for(int i=0;i<emailnametarget.size();i++){
				achievedtarget.addAll(i,leadServicedao.defaultAchievedforSales(emailnametarget.get(i).getEmail()));
				int achieved= achievedtarget.get(i).intValue();
				performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
				}
								
				Collections.sort(performance);
				if(performance.size()>2)
				return new ResponseEntity<>(performance.get(2), HttpStatus.OK);
				
				else{

					for(int i=0;i<nametarget.size();i++){
						performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
						}
								
					return new ResponseEntity<>(performance.get(2), HttpStatus.OK);
					
				}
				 
				 
}
			

								
								
	// get sales performance of all employees
/*								
	@RequestMapping(value = "/getSalesperformance",method = RequestMethod.POST)
	public ResponseEntity<?> getSalesperformance(@RequestBody String json) {
		 JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			
			List<Performance> performance = new ArrayList<Performance>();
			List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
				emailnametarget = targetServicedao.EmailNameTargetforsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
						jsonObject.getString("to").toString());
				
				if(emailnametarget.isEmpty()){
					List<NameTarget> nametarget = targetServicedao.nameAndtargetOfsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
							jsonObject.getString("to").toString());
					for(int i=0;i<nametarget.size();i++){
						performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
					}
					
					return new ResponseEntity<>(performance, HttpStatus.OK);
				}
				
			List<Long> achievedtarget = new ArrayList<Long>();
			for(int i=0;i<emailnametarget.size();i++){
				achievedtarget.addAll(i,targetServicedao.AchievedforSales_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
						jsonObject.getString("to").toString()));
				int achieved= achievedtarget.get(i).intValue();
				performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
			}
			
			Collections.sort(performance);
			return new ResponseEntity<>(performance, HttpStatus.OK);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	*/

 
 
 
 
 

 // sales performance of first employee
								
	@RequestMapping(value = "/getSalesperformanceOffirst",method = RequestMethod.POST)
	public ResponseEntity<?> getSalesperformance(@RequestBody String json) {
	JSONObject jsonObject;
	try {
			jsonObject = new JSONObject(json);
										
			List<Performance> performance = new ArrayList<Performance>();
			List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
			emailnametarget = targetServicedao.EmailNameTargetforsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
			jsonObject.getString("to").toString());
											
			if(emailnametarget.isEmpty()){
				List<NameTarget> nametarget = targetServicedao.nameAndtargetOfsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
												jsonObject.getString("to").toString());
				if(nametarget.isEmpty()){
					performance.add(null);
					return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
				}
				else{
				for(int i=0;i<nametarget.size();i++){
					performance.add(new Performance(nametarget.get(i).getName(),nametarget.get(i).getTarget(),0));
				}
				
				return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
				}
					}
											
						List<Long> achievedtarget = new ArrayList<Long>();
						for(int i=0;i<emailnametarget.size();i++){
						achievedtarget.addAll(i,targetServicedao.AchievedforSales_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
												jsonObject.getString("to").toString()));
						int achieved= achievedtarget.get(i).intValue();
						performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
										}
										
						Collections.sort(performance);
						return new ResponseEntity<>(performance.get(0), HttpStatus.OK);
										
										
						} catch (JSONException e) {
							// TODO Auto-generated catch block
										e.printStackTrace();
										return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
				     }
									
		}							
								

	
	
	
	
	
	
	
	// sales performance of second employee
	
		@RequestMapping(value = "/getSalesperformanceOfsecond",method = RequestMethod.POST)
		public ResponseEntity<?> getSalesperformancesecond(@RequestBody String json) {
		JSONObject jsonObject;
		try {
				jsonObject = new JSONObject(json);
											
				List<Performance> performance = new ArrayList<Performance>();
				List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
				emailnametarget = targetServicedao.EmailNameTargetforsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
				jsonObject.getString("to").toString());
												
				if(emailnametarget.size()<2){
					List<NameTarget> nametarget = targetServicedao.nameAndtargetOfsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
													jsonObject.getString("to").toString());
					if(nametarget.size()>1){
						
							if(emailnametarget.get(0).getName().equals(nametarget.get(0).getName())){
								
								performance.add(new Performance(nametarget.get(1).getName(),nametarget.get(1).getTarget(),0));
								return new ResponseEntity<>(performance, HttpStatus.OK);
								
							}
							else {
								performance.add(new Performance(nametarget.get(0).getName(),nametarget.get(0).getTarget(),0));
								return new ResponseEntity<>(performance, HttpStatus.OK);
							}
								
						
					
					}
					else{
						performance.add(null);
						return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
					}
						}
												
							List<Long> achievedtarget = new ArrayList<Long>();
							for(int i=0;i<emailnametarget.size();i++){
							achievedtarget.addAll(i,targetServicedao.AchievedforSales_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
													jsonObject.getString("to").toString()));
							int achieved= achievedtarget.get(i).intValue();
							performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
											}
											
							Collections.sort(performance);
							return new ResponseEntity<>(performance.get(1), HttpStatus.OK);
											
											
							} catch (JSONException e) {
								// TODO Auto-generated catch block
											e.printStackTrace();
											return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
					     }
										
			}							
							
	
		
		
		
		
		
		
		
		// sales performance of third employee
		
		@RequestMapping(value = "/getSalesperformanceOfthird",method = RequestMethod.POST)
		public ResponseEntity<?> getSalesperformancethird(@RequestBody String json) {
		JSONObject jsonObject;
		try {
				jsonObject = new JSONObject(json);
											
				List<Performance> performance = new ArrayList<Performance>();
				List<EmailNameTarget> emailnametarget = new ArrayList<EmailNameTarget>();
				emailnametarget = targetServicedao.EmailNameTargetforsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
				jsonObject.getString("to").toString());
												
				if(emailnametarget.size()<3){
					List<NameTarget> nametarget = targetServicedao.nameAndtargetOfsales_betweenSelectedmonths(jsonObject.getString("from").toString(),
													jsonObject.getString("to").toString());
					if(nametarget.size()>2){
						if((emailnametarget.get(0).getName().equals(nametarget.get(0).getName())) 
								|| 
								(emailnametarget.get(0).getName().equals(nametarget.get(1).getName()))
								||
								(emailnametarget.get(1).getName().equals(nametarget.get(0).getName()))
								|| 
								(emailnametarget.get(1).getName().equals(nametarget.get(1).getName()))){
							
							performance.add(new Performance(nametarget.get(2).getName(),nametarget.get(2).getTarget(),0));
							return new ResponseEntity<>(performance, HttpStatus.OK);
							
						}
						
						else if((emailnametarget.get(0).getName().equals(nametarget.get(1).getName())) 
								|| 
								(emailnametarget.get(0).getName().equals(nametarget.get(2).getName()))
								||
								(emailnametarget.get(1).getName().equals(nametarget.get(1).getName()))
								|| 
								(emailnametarget.get(1).getName().equals(nametarget.get(2).getName()))){
							
							performance.add(new Performance(nametarget.get(0).getName(),nametarget.get(0).getTarget(),0));
							return new ResponseEntity<>(performance, HttpStatus.OK);
							
						}
						
						else if((emailnametarget.get(0).getName().equals(nametarget.get(0).getName())) 
								|| 
								(emailnametarget.get(0).getName().equals(nametarget.get(2).getName()))
								||
								(emailnametarget.get(1).getName().equals(nametarget.get(0).getName()))
								|| 
								(emailnametarget.get(1).getName().equals(nametarget.get(2).getName()))){
							
							performance.add(new Performance(nametarget.get(1).getName(),nametarget.get(1).getTarget(),0));
							return new ResponseEntity<>(performance, HttpStatus.OK);
							
						}
						
						
					}
					else{
						performance.add(null);
						return new ResponseEntity<>(performance, HttpStatus.NO_CONTENT);
					}
						}
												
							List<Long> achievedtarget = new ArrayList<Long>();
							for(int i=0;i<emailnametarget.size();i++){
							achievedtarget.addAll(i,targetServicedao.AchievedforSales_betweenSelectedmonths(emailnametarget.get(i).getEmail(),jsonObject.getString("from").toString(),
													jsonObject.getString("to").toString()));
							int achieved= achievedtarget.get(i).intValue();
							performance.add(new Performance(emailnametarget.get(i).getName(),emailnametarget.get(i).getTarget(),achieved));	
											}
											
							Collections.sort(performance);
							return new ResponseEntity<>(performance.get(2), HttpStatus.OK);
											
											
							} catch (JSONException e) {
								// TODO Auto-generated catch block
											e.printStackTrace();
											return new ResponseEntity<>("Exception - "+e.getMessage(), HttpStatus.BAD_REQUEST);
					     }
										
			}							
												
								
	
		
		
		
		
	//Month revenue
	@RequestMapping(value = "/getThismonthEarnings",method = RequestMethod.GET)
	public ResponseEntity<?> getThismonthEarnings() {
		Long monthtotal=leadServicedao.getthismonthRevenue();
		if (monthtotal == null) {
			logger.debug("No revenue to display");
			return new ResponseEntity<>(0,HttpStatus.NO_CONTENT);
		}
		logger.debug("This Month's Revenue " + monthtotal);
		return new ResponseEntity<>(monthtotal, HttpStatus.OK);
	}
	
	
	
	
	
   //week revenue
	@RequestMapping(value = "/getThisweekEarnings",method = RequestMethod.GET)
	public ResponseEntity<?> getThisweekEarnings() {
		Long weektotal=leadServicedao.getthisweekRevenue();
		if (weektotal == null) {
			logger.debug("No revenue to display");
			return new ResponseEntity<>(0,HttpStatus.OK);
		}
		logger.debug("This Week's Revenue " + weektotal);
		return new ResponseEntity<>(weektotal, HttpStatus.OK);
		
	}
	
	
	
	
    //day revenue
	@RequestMapping(value = "/getThisdayEarnings",method = RequestMethod.GET)
	public ResponseEntity<?> getThisdayEarnings() {
		Long daytotal=null;
		daytotal = leadServicedao.getthisdayRevenue();
		if (daytotal == null) {
			logger.debug("No revenue to display");
			return new ResponseEntity<>(0,HttpStatus.OK);
		}
		logger.debug("Today's Revenue " + daytotal);
		return new ResponseEntity<>(daytotal, HttpStatus.OK);
	}

	
	
	
	//Month payment due
		@RequestMapping(value = "/getThismonthsDue",method = RequestMethod.GET)
		public ResponseEntity<?> getThismonthpaymentdue() {
			Long monthtotal=leadServicedao.getthismonthpaymentdue();
			if (monthtotal == null) {
				logger.debug("No dues to display");
				return new ResponseEntity<>(0,HttpStatus.NO_CONTENT);
			}
			logger.debug("This Month's Due " + monthtotal);
			return new ResponseEntity<>(monthtotal, HttpStatus.OK);
		}
	
		
		
		
	   //week payment due
		@RequestMapping(value = "/getThisweeksDue",method = RequestMethod.GET)
		public ResponseEntity<?> getThisweekdue() {
			Long weektotal=leadServicedao.getthisweekpaymentdue();
			if (weektotal == null) {
				logger.debug("No dues to display");
				return new ResponseEntity<>(0,HttpStatus.OK);
			}
			logger.debug("This Week's Due " + weektotal);
			return new ResponseEntity<>(weektotal, HttpStatus.OK);
			
		}
	    
		
		
		
		//day payment due
		@RequestMapping(value = "/getThisdaysDue",method = RequestMethod.GET)
		public ResponseEntity<?> getThisdaysdue() {
			Long daytotal=leadServicedao.getthisdaypaymentdue();
			if (daytotal == null) {
				logger.debug("No due to display");
				return new ResponseEntity<>(0,HttpStatus.OK);
			}
			logger.debug("Today's Dues " + daytotal);
			return new ResponseEntity<>(daytotal, HttpStatus.OK);
		}

		
	
	
		
	@RequestMapping(value = "/getfullLeads",method = RequestMethod.GET)
	public ResponseEntity<?> getfullLeads() {
		List<Lead> leads = leadServicedao.getsfullLead();
		if (leads.isEmpty()) {
			logger.debug("Leads does not exists");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		logger.debug("Found " + leads.size() + " Leads");
		logger.debug(leads);
		logger.debug(Arrays.toString(leads.toArray()));
		return new ResponseEntity<>(leads, HttpStatus.OK);
	}
	
	
	
	

	@RequestMapping(value = "/getGraphvalues",method = RequestMethod.GET)
	public ResponseEntity<?> getGraphvalues() {
		List<Object[]> leads = leadServicedao.getgraphvalues();
		if (leads.isEmpty()) {
			logger.debug("Leads does not exists");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	
		//to obtain achieved revenue line graph
		 Map<String , String> sortedobj = new LinkedHashMap<String,String>();
		for(Object[] obj : leads){
			
				sortedobj.put("Jan", " "+(obj[0]).toString());
				sortedobj.put("Feb", " "+(obj[1]).toString());
				sortedobj.put("Mar", " "+(obj[2]).toString());
				sortedobj.put("Apr", " "+(obj[3]).toString());
				sortedobj.put("May", " "+(obj[4]).toString());
				sortedobj.put("Jun", " "+(obj[5]).toString());
				sortedobj.put("Jul", " "+(obj[6]).toString());
				sortedobj.put("Aug", " "+(obj[7]).toString());
				sortedobj.put("Sep", " "+(obj[8]).toString());
				sortedobj.put("Oct", " "+(obj[9]).toString());
				sortedobj.put("Nov", " "+(obj[10]).toString());
				sortedobj.put("Dec", " "+(obj[11]).toString());
			}
	
		
		JSONObject graph = new JSONObject(sortedobj);
		
		
		JSONObject Linegraph = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray targetarray = new JSONArray();
		
		List<GraphValues> achievedValues = new ArrayList<GraphValues>();
		
		List<GraphValues> targetValues = new ArrayList<GraphValues>();
		
		Graph Finalgraph = new Graph();
		
		
		try {
			
			/*
			// to get achieved target month wise
			 * 
			array.put(0,graph.get("Jan"));
			array.put(1,graph.get("Feb"));
			array.put(2,graph.get("Mar"));
			array.put(3,graph.get("Apr"));
			array.put(4,graph.get("May"));
			array.put(5,graph.get("Jun"));
			array.put(6,graph.get("Jul"));
			array.put(7,graph.get("Aug"));
			array.put(8,graph.get("Sep"));
			array.put(9,graph.get("Oct"));
			array.put(10,graph.get("Nov"));
			array.put(11,graph.get("Dec"));
			
			*/
			
			
			
			
			// target till august 2017 is not available in database, so 
						// by it should be passed in here directly
			
			array.put(0,769285);
			array.put(1,710747 );
			array.put(2,754577);
			array.put(3,624736);
			array.put(4,1130101);
			array.put(5,894798);
			array.put(6,1214455);
			array.put(7,862313);
			array.put(8,graph.get("Sep"));
			array.put(9,graph.get("Oct"));
			array.put(10,graph.get("Nov"));
			array.put(11,graph.get("Dec"));
			
			
			
			
			//to obtain the target line graph values
			List<Object[]> target = leadServicedao.gettargetgraphvalues();
			 Map<String , String> sortedtarget = new LinkedHashMap<String,String>();
				for(Object[] tg : target){
					
					
					// target till august 2017 is not available in database, so 
					// it should be passed in here directly
					sortedtarget.put("Jan", "900000");
					sortedtarget.put("Feb", "900000");
					sortedtarget.put("Mar", "900000");
					sortedtarget.put("Apr", "1200000");
					sortedtarget.put("May", "1200000");
					sortedtarget.put("Jun", "1200000");
					sortedtarget.put("Jul", "1200000");
					sortedtarget.put("Aug", "1200000");
					sortedtarget.put("Sep", " "+(tg[8]).toString());
					sortedtarget.put("Oct", " "+(tg[9]).toString());
					sortedtarget.put("Nov", " "+(tg[10]).toString());
					sortedtarget.put("Dec", " "+(tg[11]).toString());
					
					
					
					
					
					/*
					   // to get month wise target
					
						sortedtarget.put("Jan", " "+(tg[0]).toString());
						sortedtarget.put("Feb", " "+(tg[1]).toString());
						sortedtarget.put("Mar", " "+(tg[2]).toString());
						sortedtarget.put("Apr", " "+(tg[3]).toString());
						sortedtarget.put("May", " "+(tg[4]).toString());
						sortedtarget.put("Jun", " "+(tg[5]).toString());
						sortedtarget.put("Jul", " "+(tg[6]).toString());
						sortedtarget.put("Aug", " "+(tg[7]).toString());
						sortedtarget.put("Sep", " "+(tg[8]).toString());
						sortedtarget.put("Oct", " "+(tg[9]).toString());
						sortedtarget.put("Nov", " "+(tg[10]).toString());
						sortedtarget.put("Dec", " "+(tg[11]).toString());
						
						*/
					}
				
				JSONObject targetgraph = new JSONObject(sortedtarget);
				
				targetarray.put(0,targetgraph.get("Jan"));
				targetarray.put(1,targetgraph.get("Feb"));
				targetarray.put(2,targetgraph.get("Mar"));
				targetarray.put(3,targetgraph.get("Apr"));
				targetarray.put(4,targetgraph.get("May"));
				targetarray.put(5,targetgraph.get("Jun"));
				targetarray.put(6,targetgraph.get("Jul"));
				targetarray.put(7,targetgraph.get("Aug"));
				targetarray.put(8,targetgraph.get("Sep"));
				targetarray.put(9,targetgraph.get("Oct"));
				targetarray.put(10,targetgraph.get("Nov"));
				targetarray.put(11,targetgraph.get("Dec"));
				
				
				Linegraph.put("Achieved",array);
			Linegraph.put("Target", targetarray);
	
			
			
			for(int i=0;i<12;i++){
				GraphValues gv = new GraphValues(Linegraph.getJSONArray("Achieved").get(i).toString());
				achievedValues.add(i, gv);
				
			}
			
			for(int j=0;j<12;j++){
				GraphValues tg = new GraphValues(Linegraph.getJSONArray("Target").get(j).toString());
				targetValues.add(j, tg);
				
			}
			
		
			Finalgraph = new Graph(achievedValues,targetValues);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		logger.debug(graph.toString());
		return new ResponseEntity<>(Finalgraph, HttpStatus.OK); 
	}
}


