package br.com.intertrack.backendchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Position {

	@GeneratedValue
	@Id
	private int id;

	@OneToMany(mappedBy = "vehicle")
	private Vehicle vehicle;

	@Column(name = "datetime")
	@JsonProperty(access = Access.READ_ONLY)
	private java.sql.Timestamp datetime;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "address")
	private String address;

	@Column(name = "ignition")
	private boolean ignition;

	@Column(name = "hodometro")
	private int hodometro;

	public Position() {

	}

	public Position(Vehicle vehicle, java.sql.Timestamp datetime, double latitude, double longitude, String address, boolean ignition, int hodometro) {
		
		super();
		this.vehicle = vehicle;
		this.datetime = datetime;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.ignition = ignition;
		this.hodometro = hodometro;

	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public java.sql.Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(java.sql.Timestamp datetime) {
		this.datetime = datetime;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isIgnition() {
		return ignition;
	}

	public void setIgnition(boolean ignition) {
		this.ignition = ignition;
	}

	public int getHodometro() {
		return hodometro;
	}

	public void setHodometro(int hodometro) {
		this.hodometro = hodometro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
