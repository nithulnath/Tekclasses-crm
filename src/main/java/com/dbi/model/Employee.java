package com.dbi.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "employee",uniqueConstraints={@UniqueConstraint(columnNames={"e_email"})})
public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "e_id", unique = true, nullable = false)
	private Long e_id;
	
	@Column(name = "e_name", length = 50)
	private String e_name;
	
	@Column(name = "e_email",unique=true, length = 50)
	private String e_email;

	@Column(name = "e_password", length = 25)
	private String e_password;
	
	@Column(name = "e_role", length = 50)
	private String e_role;
	
	@Column(name = "e_target")
	private int e_target;

	@Column(name = "e_createdDate",updatable = false,length=20)
	private String e_createdDate;
	
	
	@Column(name = "e_modifiedDate",length=20)
	private String e_modifiedDate;


	
	
	public Long getE_id() {
		return e_id;
	}

	public void setE_id(Long e_id) {
		this.e_id = e_id;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	public String getE_email() {
		return e_email;
	}

	public void setE_email(String e_email) {
		this.e_email = e_email;
	}

	public String getE_password() {
		return e_password;
	}

	public void setE_password(String e_password) {
		this.e_password = e_password;
	}

	public String getE_role() {
		return e_role;
	}

	public void setE_role(String e_role) {
		this.e_role = e_role;
	}

	public int getE_target() {
		return e_target;
	}

	public void setE_target(int e_target) {
		this.e_target = e_target;
	}

	public String getE_createdDate() {
		return e_createdDate;
	}

	public void setE_createdDate(String e_createdDate) {
		this.e_createdDate = e_createdDate;
	}

	public String getE_modifiedDate() {
		return e_modifiedDate;
	}

	public void setE_modifiedDate(String e_modifiedDate) {
		this.e_modifiedDate = e_modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Employee() {
	}
	
	public Employee(Long e_id) {
		super();
		this.e_id = e_id;
	}



	public Employee(String e_name, String e_email, String e_password,
			 String e_role, int e_target,
			String e_createdDate, String e_modifiedDate) {
		super();
		this.e_name = e_name;
		this.e_email = e_email;
		this.e_password = e_password;
		this.e_role = e_role;
		this.e_target = e_target;
		this.e_createdDate = e_createdDate;
		this.e_modifiedDate = e_modifiedDate;
	}

	public Employee(Long e_id, String e_name, String e_email, String e_password,
			 String e_role, int e_target,
			String e_createdDate, String e_modifiedDate) {
		super();
		this.e_id = e_id;
		this.e_name = e_name;
		this.e_email = e_email;
		this.e_password = e_password;
		this.e_role = e_role;
		this.e_target = e_target;
		this.e_createdDate = e_createdDate;
		this.e_modifiedDate = e_modifiedDate;
	}

	@Override
	public int hashCode() {
		return e_id == null ? 0 : e_id.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (e_id == null || obj == null || getClass() != obj.getClass())
			return false;
		
			Employee toCompare = (Employee) obj;
			return e_id.equals(toCompare.e_id);
	}

}
