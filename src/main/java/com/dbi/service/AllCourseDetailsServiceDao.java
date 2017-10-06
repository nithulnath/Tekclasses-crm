package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbi.model.AllCourseDetails;
import com.dbi.repository.AllCourseDetailsRepository;

@Service
public class AllCourseDetailsServiceDao implements AllCourseDetailsService{
	@Autowired
	private AllCourseDetailsRepository allCoursedetailsRepository;

	@Override
	public  AllCourseDetails save( AllCourseDetails entity) {
		return  allCoursedetailsRepository.save(entity);
	}

	@Override
	public AllCourseDetails  getById(Serializable id) {
		return allCoursedetailsRepository.findOne((Long) id);
	}

	@Override
	public List<AllCourseDetails> getAll() {
		return allCoursedetailsRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		allCoursedetailsRepository.delete((Long) id);
	}

}
