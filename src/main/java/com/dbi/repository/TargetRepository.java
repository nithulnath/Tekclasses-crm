

package com.dbi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbi.model.EmailNameTarget;
import com.dbi.model.NameTarget;
import com.dbi.model.Performance;
import com.dbi.model.Target;
@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {

	
	@Query("UPDATE Target SET t_empname=?1, t_empemail=?2, t_emprole=?3 WHERE t_empid=?4")
	@Modifying
	@Transactional
	int updatetarget(String name,String email, String role, Long id);
	
	@Query(" SELECT  new com.dbi.model.EmailNameTarget(tg.t_empemail, tg.t_empname, tg.t_emptarget) FROM Target tg, LeadCourseInterested lci, LeadOwner lo  "
			+ " WHERE LOWER(tg.t_emprole)='marketing' AND lo.ownerEmail=tg.t_empemail AND lci.leadId=lo.leadId AND lci.createdDate BETWEEN ?1 AND ?2 "
			+" group by tg.t_empemail,tg.t_empname,tg.t_emptarget ")
	List<EmailNameTarget> EmailNameTarget_betweenSelectedmonths(String from, String to);
	
	 @Query(" SELECT count(lo.ownerEmail) FROM LeadOwner lo, LeadCourseInterested lci  "
			+ " WHERE lo.ownerEmail= ?1 AND lci.leadId=lo.leadId AND lci.createdDate BETWEEN ?2 AND ?3 ")
	List<Long> Achieved_betweenSelectedmonths(String email,String from, String to);	
	  
	 @Query(" SELECT new com.dbi.model.NameTarget(t_empname,t_emptarget) FROM Target WHERE LOWER(t_emprole)='marketing'"
	 		+ " AND t_createdDate BETWEEN ?1 AND ?2 ")
	 List<NameTarget> nameAndtargetOfmarketing_betweenSelectedmonths(String from, String to);
	  
	 @Query(" SELECT  new com.dbi.model.EmailNameTarget(tg.t_empemail, tg.t_empname,tg.t_emptarget) FROM Target tg, LeadCourseInterested lci, LeadOwner lo  "
				+ " WHERE LOWER(tg.t_emprole)='sales' AND lo.ownerEmail=tg.t_empemail AND lci.leadId=lo.leadId AND lci.createdDate BETWEEN ?1 AND ?2 "
				+" group by tg.t_empemail,tg.t_empname,tg.t_emptarget ")
		List<EmailNameTarget> EmailNameTargetforsales_betweenSelectedmonths(String from,String to);
	
		 @Query(" SELECT count(lci.feeOffered) FROM LeadOwner lo, LeadCourseInterested lci "
				+ " WHERE lo.ownerEmail= ?1 AND lci.leadId=lo.leadId AND LOWER(lci.leadStatus)='enrolled' AND lcicreatedDate BETWEEN ?2 AND ?3 ")
		List<Long> AchievedforSales_betweenSelectedmonths(String email,String from, String to);	
		  
		 @Query(" SELECT new com.dbi.model.NameTarget(t_empname,t_emptarget) FROM Target WHERE LOWER(t_emprole)='sales' "
		 		+ " AND t_createdDate BETWEEN ?1 AND ?2")
		 List<NameTarget> nameAndtargetOfsales_betweenSelectedmonths(String from,String to);

	 
	 
	 
	@Query("DELETE Target WHERE t_empid=?1")
	@Modifying
	@Transactional
	int deletetarget(Long empid);
		
}


