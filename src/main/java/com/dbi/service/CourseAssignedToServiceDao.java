package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbi.model.CourseAssignedTo;
import com.dbi.repository.CourseAssignedToRepository;
@Service
public class CourseAssignedToServiceDao implements CourseAssignedToService {

	@Autowired
	private CourseAssignedToRepository courseassignedToRepository;

	@Override
	public CourseAssignedTo save(CourseAssignedTo entity) {
		return courseassignedToRepository.save(entity);
	}

	@Override
	public CourseAssignedTo getById(Serializable id) {
		return courseassignedToRepository.findOne((Long) id);
	}

	@Override
	public List<CourseAssignedTo> getAll() {
		return courseassignedToRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		courseassignedToRepository.delete((Long) id);
	}
	
	
	public List<String> Getcoursenames(Long empid){
		return courseassignedToRepository.getcoursenames(empid);
		
	}
	
	public int Deletecourseassignedto(Long empid){
		return courseassignedToRepository.deletecourseassignedto(empid);
	}
	
	public List<CourseAssignedTo> Getallcourseassignedto(){
		return courseassignedToRepository.getcourseassignedto();
	}
	
	public Long[] getcourseassignedtoid(Long empid){
		return courseassignedToRepository.getcourseassignedtoid(empid);
	}
	public List<CourseAssignedTo> getCourseassignedTobyEmpid(Long empid){
		return courseassignedToRepository.getCourseassignedTobyEmpid(empid);
	}
	
	public Long[] getemployeeid(Long cid){
		return courseassignedToRepository.getemployeeid(cid);
	}
}





