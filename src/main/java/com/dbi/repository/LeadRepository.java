package com.dbi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbi.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
	
	
	@Query("SELECT l_id FROM Lead WHERE l_email=?1 ")
	Long getleadid(String email);
	
	@Query("UPDATE Lead SET l_name=?1, l_email=?2, l_contactNumber=?3, l_country=?4, l_comment=?5, l_source=?6, l_modifiedDate=?7 WHERE l_id=?8")
	@Modifying
	@Transactional
	int updateleadtable(String name,String email, String contactNumber,String country,String comment,String source, String modifiedDate, Long id);
	
	
	@Query("SELECT l.l_id FROM Lead l where MONTH(l.l_createdDate)=MONTH(NOW()) AND YEAR(l.l_createdDate) = YEAR(CURDATE()) ")
	Long[] getallleadid();
	
	@Query("SELECT(l) FROM Lead l WHERE l.l_id=?1 ")
	Lead getallleadbyid(Long lid);
	
	@Query("SELECT l_email FROM Lead ")
	String[] getallleademailids();
	
	@Query("SELECT(l) FROM Lead l WHERE l.l_email= ?1 ")
	Lead getLeadbyEmail(String email);
}
