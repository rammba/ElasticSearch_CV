package rs.ac.uns.ftn.udd.rammba.elasticsearch.controller.dto;

public class JobApplicationDto {
	
	private String name;
	private String surname;
	private String mail;
	private String address;
	private String degree;
	
	public JobApplicationDto(String name, String surname, String mail, String address, String degree) {
		super();
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.address = address;
		this.degree = degree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
}
