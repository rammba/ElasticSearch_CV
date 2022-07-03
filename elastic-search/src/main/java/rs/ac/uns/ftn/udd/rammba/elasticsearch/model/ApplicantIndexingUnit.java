package rs.ac.uns.ftn.udd.rammba.elasticsearch.model;

public class ApplicantIndexingUnit {

	private String name;
	private String surname;
	private String degree;
	private double latitude;
	private double longitude;
	private double locationLatitude;
	private double locationLongitude;
	private String cvContent;

	public ApplicantIndexingUnit(String name, String surname, String degree, double latitude, double longitude,
			double locationLatitude, double locationLongitude, String cvContent) {
		super();
		this.name = name;
		this.surname = surname;
		this.degree = degree;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationLatitude = locationLatitude;
		this.locationLongitude = locationLongitude;
		this.cvContent = cvContent;
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

	public double getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public double getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	public String getCvContent() {
		return cvContent;
	}

	public void setCvContent(String cvContent) {
		this.cvContent = cvContent;
	}

}
