package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.dbi.model.LeadCourseInterested;
import com.dbi.repository.LeadCourseInterestedRepository;


@Service
public class LeadCourseInterestedServiceDao implements LeadCourseInterestedService{
	
	@Autowired
	private LeadCourseInterestedRepository leadCourseinterestedRepository;

	

	@Override
	public LeadCourseInterested save(LeadCourseInterested entity) {
		return leadCourseinterestedRepository.save(entity);
	}

	@Override
	public LeadCourseInterested getById(Serializable id) {
		return leadCourseinterestedRepository.findOne((Long) id);
	}
	
	@Override
	public List<LeadCourseInterested> getAll() {
		return leadCourseinterestedRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		leadCourseinterestedRepository.delete((Long) id);
	}
	
	public Long[] GetidofLCI(Long leadid){
		return leadCourseinterestedRepository.getidofLCI(leadid);
	}
	
	
	public List<LeadCourseInterested> getLeadCourseInterested(Long leadid){
		return leadCourseinterestedRepository.getLeadCourseInterested(leadid);
	}
	
	public List<LeadCourseInterested> Getallvalue(){
		return leadCourseinterestedRepository.getallvalue();
	}
	
	public int deleteLCI(Long leadid){
		return leadCourseinterestedRepository.deletelci(leadid);
	}
	
	public List<LeadCourseInterested> getleadcourseinterestedbyid(Long leadid){
		return leadCourseinterestedRepository.getleadcourseinterestedbyid(leadid);
	}
	
	
	
}
