package com.dbi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbi.model.CourseAssignedTo;

@Repository
public interface CourseAssignedToRepository extends JpaRepository<CourseAssignedTo, Long> {

	
	@Query("SELECT cat_cname FROM CourseAssignedTo WHERE cat_empid=?1 ")
	List<String> getcoursenames(Long empid);
	
	@Query("DELETE CourseAssignedTo WHERE cat_empid=?1")
	@Modifying
	@Transactional
	int deletecourseassignedto(Long empid);
	
	@Query("SELECT(cat) FROM CourseAssignedTo cat ")
	List<CourseAssignedTo> getcourseassignedto();
	
	@Query("SELECT cat_id FROM CourseAssignedTo WHERE cat_empid=?1 ")
	Long[] getcourseassignedtoid(Long empid);
	
	@Query("SELECT cat FROM CourseAssignedTo cat WHERE cat_empid=?1 ")
	List<CourseAssignedTo> getCourseassignedTobyEmpid(Long empid);
	
	@Query("SELECT cat_empid FROM CourseAssignedTo WHERE cat_cid=?1 ")
	Long[] getemployeeid(Long cid);
	
}
