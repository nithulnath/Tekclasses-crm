package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbi.model.Lead;
import com.dbi.model.LeadOwner;
import com.dbi.repository.LeadOwnerRepository;

@Service
public class LeadOwnerServiceDao implements LeadOwnerService {
	
	@Autowired
	LeadOwnerRepository leadOwnerrepository;
	
	@Override
	public LeadOwner save(LeadOwner entity) {
		return leadOwnerrepository.save(entity);
	}

	@Override
	public LeadOwner getById(Serializable id) {
		return leadOwnerrepository.findOne((Long) id);
	}
	
	@Override
	public List<LeadOwner> getAll() {
		return leadOwnerrepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		leadOwnerrepository.delete((Long) id);
	}
	
	public int deleteLeadOwner(Long leadid){
		return leadOwnerrepository.deleteLeadOwner(leadid);
	}
	
	public List<LeadOwner> getLeadowner(Long leadid, Long courseid){
		return leadOwnerrepository.getLeadowner(leadid, courseid);
	}

	public Long[] getleadid(String empemail){
		return leadOwnerrepository.getleadid(empemail);
	}
}
