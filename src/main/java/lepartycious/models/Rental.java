package lepartycious.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Float rating;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="entityId")
	private List<Attachment> attachments;
	
	@OneToMany(mappedBy="entityId")
	private List<Address> addresses;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "locality_id")
	private Locality locality;
	
	@Column(name = "sedan_availability")
	private Boolean sedanAvailability;
	
	@Column(name = "min_sedan_charges_per_hour")
	private Float minSedanChargesPerHour;
	
	@Column(name = "min_sedan_charges_per_km")
	private Float minSedanChargesPerKm;
	
	@Column(name = "hatchback_availability")
	private Boolean hatchbackAvailability;
	
	@Column(name = "min_hatchback_charges_per_hour")
	private Float minHatchbackChargesPerHour;
	
	@Column(name = "min_hatchback_charges_per_km")
	private Float minHatchbackChargesPerKm;
	
	@Column(name = "suv_availability")
	private Boolean suvAvailability;
	
	@Column(name = "min_suv_charges_per_hour")
	private Float minSuvChargesPerHour;
	
	@Column(name = "min_suv_charges_per_km")
	private Float minSuvChargesPerKm;
	
	@Column(name = "toll_cahrges_included")
	private Boolean tollCahrgesIncluded;
	
	@Column(name = "min_driver_night_rate")
	private Float minDriverNightRate;
	
	@Column(name = "waiting_charges")
	private Float waitingCharges;
	
	@Column(name = "parking_charges_included")
	private Boolean parkingChargesIncluded;

	public long getRentalId() {
		return rentalId;
	}

	public void setRentalId(long rentalId) {
		this.rentalId = rentalId;
	}

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

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Boolean getSedanAvailability() {
		return sedanAvailability;
	}

	public void setSedanAvailability(Boolean sedanAvailability) {
		this.sedanAvailability = sedanAvailability;
	}

	public Float getMinSedanChargesPerHour() {
		return minSedanChargesPerHour;
	}

	public void setMinSedanChargesPerHour(Float minSedanChargesPerHour) {
		this.minSedanChargesPerHour = minSedanChargesPerHour;
	}

	public Float getMinSedanChargesPerKm() {
		return minSedanChargesPerKm;
	}

	public void setMinSedanChargesPerKm(Float minSedanChargesPerKm) {
		this.minSedanChargesPerKm = minSedanChargesPerKm;
	}

	public Boolean getHatchbackAvailability() {
		return hatchbackAvailability;
	}

	public void setHatchbackAvailability(Boolean hatchbackAvailability) {
		this.hatchbackAvailability = hatchbackAvailability;
	}

	public Float getMinHatchbackChargesPerHour() {
		return minHatchbackChargesPerHour;
	}

	public void setMinHatchbackChargesPerHour(Float minHatchbackChargesPerHour) {
		this.minHatchbackChargesPerHour = minHatchbackChargesPerHour;
	}

	public Float getMinHatchbackChargesPerKm() {
		return minHatchbackChargesPerKm;
	}

	public void setMinHatchbackChargesPerKm(Float minHatchbackChargesPerKm) {
		this.minHatchbackChargesPerKm = minHatchbackChargesPerKm;
	}

	public Boolean getSuvAvailability() {
		return suvAvailability;
	}

	public void setSuvAvailability(Boolean suvAvailability) {
		this.suvAvailability = suvAvailability;
	}

	public Float getMinSuvChargesPerHour() {
		return minSuvChargesPerHour;
	}

	public void setMinSuvChargesPerHour(Float minSuvChargesPerHour) {
		this.minSuvChargesPerHour = minSuvChargesPerHour;
	}

	public Float getMinSuvChargesPerKm() {
		return minSuvChargesPerKm;
	}

	public void setMinSuvChargesPerKm(Float minSuvChargesPerKm) {
		this.minSuvChargesPerKm = minSuvChargesPerKm;
	}

	public Boolean getTollCahrgesIncluded() {
		return tollCahrgesIncluded;
	}

	public void setTollCahrgesIncluded(Boolean tollCahrgesIncluded) {
		this.tollCahrgesIncluded = tollCahrgesIncluded;
	}

	public Float getMinDriverNightRate() {
		return minDriverNightRate;
	}

	public void setMinDriverNightRate(Float minDriverNightRate) {
		this.minDriverNightRate = minDriverNightRate;
	}

	public Float getWaitingCharges() {
		return waitingCharges;
	}

	public void setWaitingCharges(Float waitingCharges) {
		this.waitingCharges = waitingCharges;
	}

	public Boolean getParkingChargesIncluded() {
		return parkingChargesIncluded;
	}

	public void setParkingChargesIncluded(Boolean parkingChargesIncluded) {
		this.parkingChargesIncluded = parkingChargesIncluded;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}
}
