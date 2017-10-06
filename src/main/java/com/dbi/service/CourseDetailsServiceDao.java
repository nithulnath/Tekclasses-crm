package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbi.model.CourseDetails;
import com.dbi.repository.CourseDetailsRepository;
@Service
public class CourseDetailsServiceDao implements CourseDetailsService {

	@Autowired
	private CourseDetailsRepository coursedetailsRepository;

	@Override
	public CourseDetails save(CourseDetails entity) {
		return coursedetailsRepository.save(entity);
	}

	@Override
	public CourseDetails getById(Serializable id) {
		return coursedetailsRepository.findOne((Long) id);
	}

	@Override
	public List<CourseDetails> getAll() {
		return coursedetailsRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		coursedetailsRepository.delete((Long) id);
	} 

}
