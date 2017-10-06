package com.dbi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name = "coursemaster")
	public class CourseMaster implements java.io.Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "cm_id", unique = true, nullable = false)
		private Long cm_id;
		
		@Column(name = "cm_name", length = 50)
		private String cm_name;
		
		@Column(name= "cm_createdDate",updatable = false,length=20)
		private String cm_createdDate;

		@Column(name= "cm_modifiedDate")
		private String cm_modifiedDate;

		
		public Long getCm_id() {
			return cm_id;
		}

		public void setCm_id(Long cm_id) {
			this.cm_id = cm_id;
		}

		public String getCm_name() {
			return cm_name;
		}

		public void setCm_name(String cm_name) {
			this.cm_name = cm_name;
		}

		public String getCm_createdDate() {
			return cm_createdDate;
		}

		public void setCm_createdDate(String cm_createdDate) {
			this.cm_createdDate = cm_createdDate;
		}

		
		public String getCm_modifiedDate() {
			return cm_modifiedDate;
		}

		public void setCm_modifiedDate(String cm_modifiedDate) {
			this.cm_modifiedDate = cm_modifiedDate;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public CourseMaster(String cm_name, String cm_createdDate, String cm_modifiedDate) {
			super();
			this.cm_name = cm_name;
			this.cm_createdDate = cm_createdDate;
			this.cm_modifiedDate = cm_modifiedDate;
		}

		public CourseMaster(Long cm_id, String cm_name, String cm_createdDate, String cm_modifiedDate) {
			super();
			this.cm_id = cm_id;
			this.cm_name = cm_name;
			this.cm_createdDate = cm_createdDate;
			this.cm_modifiedDate = cm_modifiedDate;
		}

		public CourseMaster() {
			super();
		}
		

}
