package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;













import com.dbi.model.AllCourseDetails;
import com.dbi.model.CourseMaster;
import com.dbi.model.ToGetCourseDetails;
import com.dbi.repository.CourseMasterRepository;

@Service
public class CourseMasterServiceDao implements CourseMasterService {

	@Autowired
	private CourseMasterRepository courseMasterRepository;

	@Override
	public CourseMaster save(CourseMaster entity) {
		return courseMasterRepository.save(entity);
	}

	@Override
	public CourseMaster getById(Serializable id) {
		return courseMasterRepository.findOne((Long) id);
	}

	@Override
	public List<CourseMaster> getAll() {
		return courseMasterRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		courseMasterRepository.delete((Long) id);
	}
	
	public Long Getcourseid(String cname){
		return courseMasterRepository.getcourseid(cname);
	}
	
	
	
	public Long Getonlineid(){
		return courseMasterRepository.getonlineid();
	}
	
	public Long Getselfpacedid(){
		return courseMasterRepository.getselfpacedid();
	}
	
	public int updatetable(String name, String modifiedDate, Long courseid){
		return courseMasterRepository.updateTable(name, modifiedDate, courseid);
	}
	

	public int updatecoursedetails1(int courseprice, String duration, String modifiedDate, Long courseid, Long onlineid ){
		return courseMasterRepository.updateCd1(courseprice,duration,modifiedDate,courseid,onlineid);
	}
	public int updatecoursedetails2(int courseprice, String duration, String modifiedDate, Long courseid, Long selfpacedid ){
		return courseMasterRepository.updateCd2(courseprice,duration,modifiedDate,courseid,selfpacedid);
	}
	
	public List<ToGetCourseDetails> Getcoursedetails(Long courseid){
		return courseMasterRepository.getcoursedetails(courseid);
	}
	
	public int Getallcoursedetails(String c_name, String[] mode, int[] price, String duration,String modifiedDate, Long cid){
		return courseMasterRepository.getallcoursedetails( c_name, mode, price, duration, modifiedDate, cid);
		
	}
	
	public List<AllCourseDetails> getEntirecourseDetails(){
		return courseMasterRepository.getall();
	}
	
	public List<int[]> Getallprices(){
		return courseMasterRepository.getallprices();
	}
	
	public List<String[]> Getallmodes(){
		return  courseMasterRepository.getallmodes();
	}
	
	public int deletecoursemaster(Long cmid){
		return courseMasterRepository.deleteCM(cmid);
	}
	
	public int deletecoursedetails(Long cdid){
		return courseMasterRepository.deleteCD(cdid);
	}
	public int deleteAllCourseDetails(Long cdid){
		return courseMasterRepository.deleteACD(cdid);
	}
	
	
}
