package lepartycious.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="rental")
public class Rental implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656576573287423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long rentalId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "rating")
	private float rating;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="entityId")
	private List<Attachment> attachments;
	
	@OneToMany(mappedBy="entityId")
	private List<Address> addresses;
	
	@Column(name = "city_id")
	private City cityId;
	
	@Column(name = "sedan_availability")
	private boolean sedanAvailability;
	
	@Column(name = "min_sedan_charges_per_hour")
	private float minSedanChargesPerHour;
	
	@Column(name = "min_sedan_charges_per_km")
	private float minSedanChargesPerKm;
	
	@Column(name = "hatchback_availability")
	private boolean hatchbackAvailability;
	
	@Column(name = "min_hatchback_charges_per_hour")
	private float minHatchbackChargesPerHour;
	
	@Column(name = "min_hatchback_charges_per_km")
	private float minHatchbackChargesPerKm;
	
	@Column(name = "suv_availability")
	private boolean suvAvailability;
	
	@Column(name = "min_suv_charges_per_hour")
	private float minSuvChargesPerHour;
	
	@Column(name = "min_suv_charges_per_km")
	private float minSuvChargesPerKm;
	
	@Column(name = "toll_cahrges_included")
	private boolean tollCahrgesIncluded;
	
	@Column(name = "min_driver_night_rate")
	private float minDriverNightRate;
	
	@Column(name = "waiting_charges")
	private float waitingCharges;
	
	@Column(name = "parking_charges_included")
	private boolean parkingChargesIncluded;
	
	@Version
	private long version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	}

	public boolean isSedanAvailability() {
		return sedanAvailability;
	}

	public void setSedanAvailability(boolean sedanAvailability) {
		this.sedanAvailability = sedanAvailability;
	}

	public float getMinSedanChargesPerHour() {
		return minSedanChargesPerHour;
	}

	public void setMinSedanChargesPerHour(float minSedanChargesPerHour) {
		this.minSedanChargesPerHour = minSedanChargesPerHour;
	}

	public float getMinSedanChargesPerKm() {
		return minSedanChargesPerKm;
	}

	public void setMinSedanChargesPerKm(float minSedanChargesPerKm) {
		this.minSedanChargesPerKm = minSedanChargesPerKm;
	}

	public boolean isHatchbackAvailability() {
		return hatchbackAvailability;
	}

	public void setHatchbackAvailability(boolean hatchbackAvailability) {
		this.hatchbackAvailability = hatchbackAvailability;
	}

	public float getMinHatchbackChargesPerHour() {
		return minHatchbackChargesPerHour;
	}

	public void setMinHatchbackChargesPerHour(float minHatchbackChargesPerHour) {
		this.minHatchbackChargesPerHour = minHatchbackChargesPerHour;
	}

	public float getMinHatchbackChargesPerKm() {
		return minHatchbackChargesPerKm;
	}

	public void setMinHatchbackChargesPerKm(float minHatchbackChargesPerKm) {
		this.minHatchbackChargesPerKm = minHatchbackChargesPerKm;
	}

	public boolean isSuvAvailability() {
		return suvAvailability;
	}

	public void setSuvAvailability(boolean suvAvailability) {
		this.suvAvailability = suvAvailability;
	}

	public float getMinSuvChargesPerHour() {
		return minSuvChargesPerHour;
	}

	public void setMinSuvChargesPerHour(float minSuvChargesPerHour) {
		this.minSuvChargesPerHour = minSuvChargesPerHour;
	}

	public float getMinSuvChargesPerKm() {
		return minSuvChargesPerKm;
	}

	public void setMinSuvChargesPerKm(float minSuvChargesPerKm) {
		this.minSuvChargesPerKm = minSuvChargesPerKm;
	}

	public boolean isTollCahrgesIncluded() {
		return tollCahrgesIncluded;
	}

	public void setTollCahrgesIncluded(boolean tollCahrgesIncluded) {
		this.tollCahrgesIncluded = tollCahrgesIncluded;
	}

	public float getMinDriverNightRate() {
		return minDriverNightRate;
	}

	public void setMinDriverNightRate(float minDriverNightRate) {
		this.minDriverNightRate = minDriverNightRate;
	}

	public float getWaitingCharges() {
		return waitingCharges;
	}

	public void setWaitingCharges(float waitingCharges) {
		this.waitingCharges = waitingCharges;
	}

	public boolean isParkingChargesIncluded() {
		return parkingChargesIncluded;
	}

	public void setParkingChargesIncluded(boolean parkingChargesIncluded) {
		this.parkingChargesIncluded = parkingChargesIncluded;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
