
package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbi.model.EmailNameTarget;
import com.dbi.model.NameTarget;
import com.dbi.model.Performance;
import com.dbi.model.Target;
import com.dbi.repository.TargetRepository;

@Service
public class TargetServiceDao implements TargetService {
	
	@Autowired
	TargetRepository targetRepository;
	
	@Override
	public Target save(Target entity) {
		return targetRepository.save(entity);
	}

	@Override
	public Target getById(Serializable id) {
		return targetRepository.findOne((Long) id);
	}

	@Override
	public List<Target> getAll() {
		return targetRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		targetRepository.delete((Long) id);
	}
		
	public int updateTarget(String name,String email, String role, Long id){
		return targetRepository.updatetarget(name, email,role,id);
	}
	
	
	 public List<EmailNameTarget> EmailNameTarget_betweenSelectedmonths(String from,String to){
		 return targetRepository.EmailNameTarget_betweenSelectedmonths(from,to);
	 }
	
	 public List<Long> Achieved_betweenSelectedmonths(String email,String from,String to){
		 return targetRepository.Achieved_betweenSelectedmonths(email,from,to);
	 }
	 
	 public List<NameTarget> nameAndtargetOfmarketing_betweenSelectedmonths(String from, String to){
		 return targetRepository.nameAndtargetOfmarketing_betweenSelectedmonths(from, to);
	 }
	
	 
	 public List<EmailNameTarget> EmailNameTargetforsales_betweenSelectedmonths(String from,String to){
		 return targetRepository.EmailNameTargetforsales_betweenSelectedmonths(from, to);
	 }
	 
	 public List<Long> AchievedforSales_betweenSelectedmonths(String email,String from, String to){
		 return targetRepository.AchievedforSales_betweenSelectedmonths(email, from, to);
	 }
	 
	 public  List<NameTarget> nameAndtargetOfsales_betweenSelectedmonths(String from,String to){
		 return targetRepository.nameAndtargetOfsales_betweenSelectedmonths(from,to);
	 }
	 
	 
	public int deleteTarget(Long empid){
		return targetRepository.deletetarget(empid);
	}
	
}
