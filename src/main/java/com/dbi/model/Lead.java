package com.dbi.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "lead")
public class Lead implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "l_id",unique=true, nullable = false)
	private Long l_id;
	
	@Column(name = "l_name", length = 50)
	private String l_name;
	
	@Column(name = "l_email", unique=true,length = 50)
	private String l_email;

	@Column(name = "l_contactNumber", length = 15)
	private String l_contactNumber;
	
	@Column(name = "l_country", length = 50)
	private String l_country;
	
	
	@Column(name = "l_comment")
	private String l_comment;
	
	@Column(name = "l_source")
	private String l_source;

	@Column(name = "l_createdDate",updatable = false,length=20)
	private String l_createdDate;
	
	@Column(name = "l_modifiedDate",length=20)
	private String l_modifiedDate;
	

	public Lead() {
	}
	
	public Lead(Long l_id) {
		super();
		this.l_id = l_id;
	}



	public Long getL_id() {
		return l_id;
	}



	public void setL_id(Long l_id) {
		this.l_id = l_id;
	}



	public String getL_name() {
		return l_name;
	}



	public void setL_name(String l_name) {
		this.l_name = l_name;
	}



	public String getL_email() {
		return l_email;
	}



	public void setL_email(String l_email) {
		this.l_email = l_email;
	}



	public String getL_contactNumber() {
		return l_contactNumber;
	}



	public void setL_contactNumber(String l_contactNumber) {
		this.l_contactNumber = l_contactNumber;
	}


	public String getL_createdDate() {
		return l_createdDate;
	}



	public void setL_createdDate(String l_createdDate) {
		this.l_createdDate = l_createdDate;
	}



	public String getL_modifiedDate() {
		return l_modifiedDate;
	}



	public void setL_modifiedDate(String l_modifiedDate) {
		this.l_modifiedDate = l_modifiedDate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getL_country() {
		return l_country;
	}

	public void setL_country(String l_country) {
		this.l_country = l_country;
	}


	public String getL_comment() {
		return l_comment;
	}

	public void setL_comment(String l_comment) {
		this.l_comment = l_comment;
	}
	
	
	
	public String getL_source() {
		return l_source;
	}

	public void setL_source(String l_source) {
		this.l_source = l_source;
	}

	@Override
	public int hashCode() {
		return l_id == null ? 0 : l_id.hashCode();
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (l_id == null || obj == null || getClass() != obj.getClass())
			return false;
		
			Lead toCompare = (Lead) obj;
			return l_id.equals(toCompare.l_id);
	}

	public Lead(Long l_id, String l_name, String l_email,
			String l_contactNumber, String l_country, String l_comment,
			String l_source, String l_createdDate, String l_modifiedDate) {
		super();
		this.l_id = l_id;
		this.l_name = l_name;
		this.l_email = l_email;
		this.l_contactNumber = l_contactNumber;
		this.l_country = l_country;
		this.l_comment = l_comment;
		this.l_source = l_source;
		this.l_createdDate = l_createdDate;
		this.l_modifiedDate = l_modifiedDate;
	}

	public Lead(String l_name, String l_email, String l_contactNumber,
			String l_country, String l_comment, String l_source,
			String l_createdDate, String l_modifiedDate) {
		super();
		this.l_name = l_name;
		this.l_email = l_email;
		this.l_contactNumber = l_contactNumber;
		this.l_country = l_country;
		this.l_comment = l_comment;
		this.l_source = l_source;
		this.l_createdDate = l_createdDate;
		this.l_modifiedDate = l_modifiedDate;
	}

	

	
}
	
	
	
	


	
	




	
	

	