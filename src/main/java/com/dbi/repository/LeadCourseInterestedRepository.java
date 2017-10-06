package com.dbi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbi.model.LeadCourseInterested;

@Repository
public interface LeadCourseInterestedRepository extends JpaRepository<LeadCourseInterested,Long> {

	
	@Query(" SELECT id FROM LeadCourseInterested where leadId=?1")
	Long[] getidofLCI(Long leadid);
	
	
	@Query(" SELECT(lci) FROM LeadCourseInterested lci WHERE leadId=?1 ")
	List<LeadCourseInterested> getLeadCourseInterested(Long leadid);
	
	@Query(" SELECT(lci1) FROM LeadCourseInterested lci1 ")
	List<LeadCourseInterested> getallvalue();
	
	@Query("DELETE LeadCourseInterested WHERE leadId=?1")
	@Modifying
	@Transactional
	int deletelci(Long leadid);
	
	@Query(" SELECT(lci) FROM LeadCourseInterested lci where lci.leadId=?1")
	List<LeadCourseInterested> getleadcourseinterestedbyid(Long leadid);
	
	
	
	
}
