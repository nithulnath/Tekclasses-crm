
package com.dbi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dbi.model.EmailNameTarget;
import com.dbi.model.Lead;
import com.dbi.model.NameTarget;



@Repository
public interface AdminDashboardRepository extends JpaRepository<Lead, Long> {
	

	@Query("SELECT COUNT(*) FROM LeadCourseInterested WHERE createdDate BETWEEN ?1 AND ?2" )
	Long leadcount(String from, String to);
	
	@Query("SELECT COUNT(*) FROM LeadCourseInterested WHERE MONTH(createdDate)=MONTH(NOW()) AND YEAR(createdDate) = YEAR(CURDATE())" )
	Long defaultleadcount();
	
	
	
	@Query("SELECT COUNT(*) FROM LeadCourseInterested WHERE createdDate BETWEEN ?1 AND ?2 AND LOWER(leadStatus)='enrolled'" )
	Long enrolledleadcount(String from,String to);
	
	@Query("SELECT COUNT(*) FROM LeadCourseInterested WHERE MONTH(createdDate)=MONTH(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'" )
	Long defaultenrolledleadcount();
	
	
	
	@Query("SELECT SUM(feeOffered) FROM LeadCourseInterested WHERE paymentDate BETWEEN ?1 AND ?2 AND LOWER(leadStatus)='enrolled'" )
	Long revenue(String from, String to);			
	
	@Query("SELECT SUM(feeOffered) FROM LeadCourseInterested WHERE MONTH(paymentDate)=MONTH(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'" )
	Long defaultrevenue();			
	
		
	@Query(" SELECT  new com.dbi.model.EmailNameTarget(emp.e_email, emp.e_name, emp.e_target) FROM Employee emp, LeadCourseInterested lci, LeadOwner lo  "
			+ " WHERE LOWER(emp.e_role)='marketing' AND lci.leadId=lo.leadId AND lo.ownerEmail=emp.e_email "
			+" group by emp.e_email,emp.e_name,emp.e_target ")
	List<EmailNameTarget> defaultEmailNameTarget();
	
	 @Query(" SELECT count(lo.ownerEmail) FROM LeadOwner lo "
			+ " WHERE lo.ownerEmail= ?1 AND MONTH(lo.createdDate)=MONTH(NOW()) AND YEAR(lo.createdDate) = YEAR(CURDATE())")
	List<Long> defaultAchieved(String email);	
	  
	 @Query(" SELECT new com.dbi.model.NameTarget(e_name,e_target) FROM Employee WHERE LOWER(e_role)='marketing' ")
	 List<NameTarget> nameAndtargetOfmarketing();
	  

	 @Query(" SELECT  new com.dbi.model.EmailNameTarget(emp.e_email, emp.e_name, emp.e_target) FROM Employee emp, LeadCourseInterested lci, LeadOwner lo "
				+ " WHERE LOWER(emp.e_role)='sales' AND lci.leadId=lo.leadId AND lo.ownerEmail=emp.e_email "
				+ " AND LOWER(lci.leadStatus)='enrolled' "
				+" group by emp.e_email,emp.e_name,emp.e_target ")
		List<EmailNameTarget> defaultEmailNameTargetforsales();
	
		 @Query(" SELECT SUM(lci.feeOffered) FROM LeadCourseInterested lci, LeadOwner lo "
				+ " WHERE lo.ownerEmail=?1 AND lci.leadId=lo.leadId AND LOWER(lci.leadStatus)='enrolled' AND MONTH(lci.createdDate)=MONTH(NOW()) AND YEAR(lci.createdDate) = YEAR(CURDATE()) ")
		List<Long> defaultAchievedforSales(String email);	
		  
		 @Query(" SELECT new com.dbi.model.NameTarget(e_name,e_target) FROM Employee WHERE LOWER(e_role)='sales' ")
		 List<NameTarget> nameAndtargetOfsales();

	
	
	@Query(" SELECT SUM(feeOffered) FROM LeadCourseInterested where MONTH(createdDate)=MONTH(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'")
	Long monthrevenue();
	
	@Query(" SELECT SUM(pendingPayment) FROM LeadCourseInterested where MONTH(createdDate)=MONTH(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'")
	Long monthpaymentdue();
	
	@Query(" SELECT SUM(feeOffered) FROM LeadCourseInterested where WEEKOFYEAR(createdDate)=WEEKOFYEAR(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'")
	Long weekrevenue();
	
	@Query(" SELECT SUM(pendingPayment) FROM LeadCourseInterested where WEEKOFYEAR(createdDate)=WEEKOFYEAR(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'")
	Long weekpaymentdue();
	
	@Query(" SELECT SUM(feeOffered) FROM LeadCourseInterested where DAY(createdDate)=DAY(NOW()) AND MONTH(createdDate)=MONTH(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'")
	Long dayrevenue();
	
	@Query(" SELECT SUM(pendingPayment) FROM LeadCourseInterested where DAY(createdDate)=DAY(NOW()) AND MONTH(createdDate)=MONTH(NOW()) AND YEAR(createdDate) = YEAR(CURDATE()) AND LOWER(leadStatus)='enrolled'")
	Long daypaymentdue();
		
	@Query("SELECT(ld) FROM Lead ld")
	List<Lead> fullLead();	
	
	
	//to get achieved revenue per month
	@Query("SELECT "
			+" sum(case when substr(paymentDate,6,2) = '01' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '02' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '03' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '04' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '05' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '06' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '07' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '08' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '09' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '10' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '11' then feeOffered else 0 end) , "
			+" sum(case when substr(paymentDate,6,2) = '12' then feeOffered else 0 end)  "
			+" FROM LeadCourseInterested WHERE LOWER(leadStatus)='enrolled' ") 
	List<Object[]> graph();
	
	
	// to get total target per month
	@Query("SELECT "
			+" sum(case when substr(t_createdDate,6,2) = '01' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '02' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '03' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '04' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '05' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '06' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '07' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '08' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '09' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '10' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '11' then t_emptarget else 0 end) , "
			+" sum(case when substr(t_createdDate,6,2) = '12' then t_emptarget else 0 end)  "
			+" FROM Target WHERE LOWER(t_emprole)='sales' ") 
	List<Object[]> targetgraph();
	
	
	
}
