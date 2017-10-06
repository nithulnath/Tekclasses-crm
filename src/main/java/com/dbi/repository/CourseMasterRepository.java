package com.dbi.repository;


import java.util.List;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;





import com.dbi.model.AllCourseDetails;
import com.dbi.model.CourseMaster;
import com.dbi.model.ToGetCourseDetails;

@Repository
public interface CourseMasterRepository extends JpaRepository<CourseMaster, Long> {
	
	
	@Query("SELECT cm_id FROM CourseMaster WHERE cm_name = ?1 ")
	Long getcourseid(String cname);
	
	
	@Query("SELECT m_id FROM CourseModeMaster WHERE LOWER(m_type) = 'online' ")
	Long getonlineid();
	
	@Query("SELECT m_id FROM CourseModeMaster WHERE LOWER(m_type) = 'SelfPaced' ")
	Long getselfpacedid();
	
	@Query("UPDATE CourseMaster SET cm_name=?1, cm_modifiedDate=?2 WHERE cm_id=?3")
	@Modifying
	@Transactional
	int updateTable(String name,String modifiedDate, Long courseid);
	
	
	@Query("UPDATE CourseDetails SET cd_coursePrice=?1, cd_courseDuration=?2, cd_modifiedDate=?3 WHERE cd_courseId=?4 AND cd_modeId=?5")
	@Modifying
	@Transactional
	int updateCd1(int courseprice, String duration,String modifiedDate, Long courseid,Long onlineid);
	
	
	@Query("UPDATE CourseDetails SET cd_coursePrice=?1, cd_courseDuration=?2, cd_modifiedDate=?3 WHERE cd_courseId=?4 AND cd_modeId=?5")
	@Modifying
	@Transactional
	int updateCd2(int courseprice, String duration,String modifiedDate, Long courseid,Long selfpacedid);
	
	@Query("SELECT new com.dbi.model.ToGetCourseDetails(cd_modeId,cd_coursePrice,cd_courseDuration) FROM CourseDetails where cd_courseId=?1 ")
	List<ToGetCourseDetails> getcoursedetails(Long courseid);
	
	@Query("UPDATE AllCourseDetails SET c_name=?1, c_mode=?2, c_price=?3, c_duration=?4, c_modifiedDate=?5 WHERE c_id=?6 ")
	@Modifying
	@Transactional
	int getallcoursedetails(String c_name, String[] mode, int[] price, String duration,String modifiedDate, Long cid);
	
	@Query("SELECT new com.dbi.model.AllCourseDetails(c_id,c_name,c_duration,c_createdDate,c_modifiedDate) FROM AllCourseDetails ")
	List<AllCourseDetails> getall();
	
	@Query("SELECT c_price FROM AllCourseDetails ")
	List<int[]> getallprices();
	
	@Query("SELECT c_mode FROM AllCourseDetails ")
	List<String[]> getallmodes();
	
	@Query("DELETE CourseMaster WHERE cm_id=?1")
	@Modifying
	@Transactional
	int deleteCM(Long cmid);
	
	@Query("DELETE CourseDetails WHERE cd_courseId=?1")
	@Modifying
	@Transactional
	int deleteCD(Long cdid);
	
	@Query("DELETE AllCourseDetails WHERE c_id=?1")
	@Modifying
	@Transactional
	int deleteACD(Long cdid);
	
	
	

}
