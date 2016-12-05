package lepartycious.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="address")
public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908523093423L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long addressId;
	
	@Column(name="address_line_1")
	private String addressLine1;
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pincode")
	private Long pincode;
	
	@Column(name="primary_phone")
	private String primaryPhone;
	
	@Column(name="secondary_phone")
	private String secondaryPhone;
	
	@Column(name="entity_id")
	private Long entityId;
	
	@Column(name="latitude")
	private Float latitude;
	
	@Column(name="longitude")
	private Float longitude;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String addressLine1, String addressLine2, String city,
			String email, Long pincode, String primaryPhone,
			String secondaryPhone) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.email = email;
		this.pincode = pincode;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
}
