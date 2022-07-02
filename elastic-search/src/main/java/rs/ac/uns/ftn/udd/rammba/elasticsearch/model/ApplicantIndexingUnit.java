package rs.ac.uns.ftn.udd.rammba.elasticsearch.model;

public class ApplicantIndexingUnit {

	private String name;
	private String surname;
	private String degree;
	private double latitude;
	private double longitude;

	public ApplicantIndexingUnit(String name, String surname, String degree, double latitude, double longitude) {
		super();
		this.name = name;
		this.surname = surname;
		this.degree = degree;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
