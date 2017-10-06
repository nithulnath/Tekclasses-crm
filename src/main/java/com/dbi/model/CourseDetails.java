package com.dbi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coursedetails")
public class CourseDetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_id", unique = true, nullable = false)
	private Long cd_id;
	
	@Column(name = "cd_courseId")
	private Long cd_courseId;
	
	@Column(name = "cd_modeId")
	private Long cd_modeId;
	
	@Column(name = "cd_coursePrice")
	private int cd_coursePrice;
	
	@Column(name = "cd_courseDuration")
	private String cd_courseDuration;
	
	@Column(name = "cd_createdDate",updatable = false,length=20)
	private String cd_createdDate;
	
	@Column(name = "cd_modifiedDate",length=20)
	private String cd_modifiedDate;

	public Long getCd_id() {
		return cd_id;
	}

	public void setCd_id(Long cd_id) {
		this.cd_id = cd_id;
	}

	public Long getCd_courseId() {
		return cd_courseId;
	}

	public void setCd_courseId(Long cd_courseId) {
		this.cd_courseId = cd_courseId;
	}

	public Long getCd_modeId() {
		return cd_modeId;
	}

	public void setCd_modeId(Long cd_modeId) {
		this.cd_modeId = cd_modeId;
	}

	public int getCd_coursePrice() {
		return cd_coursePrice;
	}

	public void setCd_coursePrice(int cd_coursePrice) {
		this.cd_coursePrice = cd_coursePrice;
	}

	public String getCd_courseDuration() {
		return cd_courseDuration;
	}

	public void setCd_courseDuration(String cd_courseDuration) {
		this.cd_courseDuration = cd_courseDuration;
	}

	public String getCd_createdDate() {
		return cd_createdDate;
	}

	public void setCd_createdDate(String cd_createdDate) {
		this.cd_createdDate = cd_createdDate;
	}

	public String getCd_modifiedDate() {
		return cd_modifiedDate;
	}

	public void setCd_modifiedDate(String cd_modifiedDate) {
		this.cd_modifiedDate = cd_modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CourseDetails() {
		super();
	}

	public CourseDetails(Long cd_courseId, Long cd_modeId, int cd_coursePrice,
			String cd_courseDuration, String cd_createdDate,
			String cd_modifiedDate) {
		super();
		this.cd_courseId = cd_courseId;
		this.cd_modeId = cd_modeId;
		this.cd_coursePrice = cd_coursePrice;
		this.cd_courseDuration = cd_courseDuration;
		this.cd_createdDate = cd_createdDate;
		this.cd_modifiedDate = cd_modifiedDate;
	}

	public CourseDetails(Long cd_id, Long cd_courseId, Long cd_modeId,
			int cd_coursePrice, String cd_courseDuration,
			String cd_createdDate, String cd_modifiedDate) {
		super();
		this.cd_id = cd_id;
		this.cd_courseId = cd_courseId;
		this.cd_modeId = cd_modeId;
		this.cd_coursePrice = cd_coursePrice;
		this.cd_courseDuration = cd_courseDuration;
		this.cd_createdDate = cd_createdDate;
		this.cd_modifiedDate = cd_modifiedDate;
	}
	
	
	
	

}
