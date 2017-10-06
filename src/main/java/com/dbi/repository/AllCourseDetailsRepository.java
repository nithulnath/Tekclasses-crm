package com.dbi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbi.model.AllCourseDetails;



@Repository
public interface AllCourseDetailsRepository extends JpaRepository<AllCourseDetails, Long>{

}
