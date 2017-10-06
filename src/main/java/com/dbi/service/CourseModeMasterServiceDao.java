package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbi.model.CourseModeMaster;
import com.dbi.repository.CourseModeMasterRepository;

@Service
public class CourseModeMasterServiceDao implements CourseModeMasterService {

	@Autowired
	private CourseModeMasterRepository courseModemasterRepository;

	@Override
	public CourseModeMaster save(CourseModeMaster entity) {
		return courseModemasterRepository.save(entity);
	}

	@Override
	public CourseModeMaster getById(Serializable id) {
		return courseModemasterRepository.findOne((Long) id);
	}

	@Override
	public List<CourseModeMaster> getAll() {
		return courseModemasterRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		courseModemasterRepository.delete((Long) id);
	}
	
	

}
