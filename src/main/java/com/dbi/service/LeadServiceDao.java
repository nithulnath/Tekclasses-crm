package com.dbi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbi.model.EmailNameTarget;
import com.dbi.model.Lead;
import com.dbi.model.NameTarget;
import com.dbi.model.Performance;
import com.dbi.repository.AdminDashboardRepository;
import com.dbi.repository.LeadRepository;

	@Service
	public class LeadServiceDao implements LeadService {

		@Autowired
		private LeadRepository leadRepository;

		@Autowired
		private AdminDashboardRepository admindashboardRepository;
		

		@Override
		public Lead save(Lead entity) {
			return leadRepository.save(entity);
		}

		@Override
		public Lead getById(Serializable id) {
			return leadRepository.findOne((Long) id);
		}
		
		@Override
		public List<Lead> getAll() {
			return leadRepository.findAll();
		}

		@Override
		public void delete(Serializable id) {
			leadRepository.delete((Long) id);
		}
		
		public Long Getleadid(String email){
			return leadRepository.getleadid(email);
		}
		
	public	int Updateleadtable(String name,String email, String contactNumber,String country,String comment,String source, String modifiedDate, Long id){
		return leadRepository.updateleadtable(name,email,contactNumber,country,comment,source,modifiedDate,id);
	}
	
	
		
		
		public Long getleadcount(String from,String to) {
			return admindashboardRepository.leadcount(from,to);
		}
		
		public Long getdefaultleadcount() {
			return admindashboardRepository.defaultleadcount();
		}
		
		public Long getenrolledleadcount(String from, String to) {
			return admindashboardRepository.enrolledleadcount(from,to);
		}
		
		public Long defaultgetenrolledleadcount() {
			return admindashboardRepository.defaultenrolledleadcount();
		}

		
		
		public Long getRevenue(String from,String to) {
			return admindashboardRepository.revenue(from,to);
		}
		
		public Long defaultgetRevenue() {
			return admindashboardRepository.defaultrevenue();
		}
		
		
		
		public double Conversion(String from, String to){
			double totalLead= admindashboardRepository.leadcount(from, to);
			double enrolledLead=admindashboardRepository.enrolledleadcount(from,to);
			double div=enrolledLead/totalLead;
			double percentage= div*100;
			return percentage;
		}
		
		public double defaultConversion(){
			double totalLead= admindashboardRepository.defaultleadcount();
			double enrolledLead=admindashboardRepository.defaultenrolledleadcount();
			double div=enrolledLead/totalLead;
			double percentage= div*100;
			return percentage;
		}
		
		
		
		
		public List<Long> defaultAchieved(String email) {
			return admindashboardRepository.defaultAchieved(email);
		}
		
		
		public List<EmailNameTarget> defaultEmailNameTarget(){
			return admindashboardRepository.defaultEmailNameTarget();
			
		}
		
		public  List<NameTarget> nameAndtargetOfmarketing(){
			return admindashboardRepository.nameAndtargetOfmarketing();
		}
	
		
		public List<Long> defaultAchievedforSales(String email) {
			return admindashboardRepository.defaultAchievedforSales(email);
		}
		
		
		public List<EmailNameTarget> defaultEmailNameTargetforsales(){
			return admindashboardRepository.defaultEmailNameTargetforsales();
			
		}
		
		public  List<NameTarget> nameAndtargetOfsales(){
			return admindashboardRepository.nameAndtargetOfsales();
		}
		
		
		
	
		public Long getthismonthRevenue() {
			return admindashboardRepository.monthrevenue();
		}
		
		public Long getthisweekRevenue() {
			return admindashboardRepository.weekrevenue();
		}
		
		public Long getthisdayRevenue() {
			return admindashboardRepository.dayrevenue();
		}
		
		
		public Long getthismonthpaymentdue() {
			return admindashboardRepository.monthpaymentdue();
		}
		
		public Long getthisweekpaymentdue() {
			return admindashboardRepository.weekpaymentdue();
		}
		
		public Long getthisdaypaymentdue() {
			return admindashboardRepository.daypaymentdue();
		}
		
		
		public List<Lead> getsfullLead() {
			return admindashboardRepository.fullLead();
		}
		
		public List<Object[]> getgraphvalues() {
			return admindashboardRepository.graph();
		}
		
		public List<Object[]> gettargetgraphvalues() {
			return admindashboardRepository.targetgraph();
		}
		
		
		public Long[] getallleadid(){
			return leadRepository.getallleadid();
		}
		
		public Lead getallleadbyid(Long lid){
			return leadRepository.getallleadbyid(lid);
		}
		
		public String[] getallleademailids(){
			return leadRepository.getallleademailids();
		}
		
		public Lead getLeadbyEmail(String email){
			return leadRepository.getLeadbyEmail(email);
		}
		
}
