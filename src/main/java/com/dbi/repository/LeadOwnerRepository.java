package com.dbi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbi.model.LeadOwner;

@Repository
public interface LeadOwnerRepository extends JpaRepository<LeadOwner,Long> {
	
	@Query("DELETE LeadOwner WHERE leadId=?1")
	@Modifying
	@Transactional
	int deleteLeadOwner(Long leadid);

	@Query("Select(lo) From LeadOwner lo WHERE lo.leadId=?1 AND lo.courseId=?2")
	List<LeadOwner> getLeadowner(Long leadid, Long courseid);
	
	@Query(" SELECT DISTINCT leadId FROM LeadOwner where ownerEmail=?1")
	Long[] getleadid(String empemail);
}
