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
@Table(name="venue_amenities")
public class VenueAmenities implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1465349098378090L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="venue_id")
	private Venue venueId;
	
	@ManyToOne
	@JoinColumn(name="amenities_id")
	private Amenities amenitiesId;
	
	@Column(name="min_amenity_cost")
	private Float minCost;
	
	@Column(name="max_amenity_cost")
	private Float maxCost;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "amenity_name")
	private String amenityName;
	
	@Column(name = "max_accomodation_capacity")
	private String maxAccomodationCapacity;
	
	@Column(name = "min_accomodation")
	private String minAccomodation;
	
	@Column(name = "is_fully_ac")
	private Boolean isFullyAc;
	
	@Column(name = "additional_info")
	private String additionalInfo;
	
	@Column(name="min_veg_cost")
	private String minVegCost;
	
	@Column(name="max_veg_cost")
	private String maxVegCost;
	
	@Column(name="min_nonveg_cost")
	private String minNonVegCost;
	
	@Column(name="max_nonveg_cost")
	private String maxNonVegCost;
	
	@Column(name = "tent_avlbl")
	private Boolean tentingAvlbl;
	
	@Column(name = "floral_avlbl")
	private Boolean floralAvlbl;
	
	@Column(name = "lighting_avlbl")
	private Boolean lightingAvlbl;
	
	@Column(name = "balloons_avlbl")
	private Boolean balloonsAvlbl;
	
	@Column(name = "candles_avlbl")
	private Boolean candlesAvlbl;
	
	public Venue getVenueId() {
		return venueId;
	}

	public void setVenueId(Venue venueId) {
		this.venueId = venueId;
	}

	public Amenities getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(Amenities amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	public Float getMinCost() {
		return minCost;
	}

	public void setMinCost(Float minCost) {
		this.minCost = minCost;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Float getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(Float maxCost) {
		this.maxCost = maxCost;
	}

	public String getAmenityName() {
		return amenityName;
	}

	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}

	public Boolean isFullyAc() {
		return isFullyAc;
	}

	public void setFullyAc(Boolean isFullyAc) {
		this.isFullyAc = isFullyAc;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getMaxAccomodationCapacity() {
		return maxAccomodationCapacity;
	}

	public void setMaxAccomodationCapacity(String maxAccomodationCapacity) {
		this.maxAccomodationCapacity = maxAccomodationCapacity;
	}

	public String getMinAccomodation() {
		return minAccomodation;
	}

	public void setMinAccomodation(String minAccomodation) {
		this.minAccomodation = minAccomodation;
	}

	public String getMinVegCost() {
		return minVegCost;
	}

	public void setMinVegCost(String minVegCost) {
		this.minVegCost = minVegCost;
	}

	public String getMaxVegCost() {
		return maxVegCost;
	}

	public void setMaxVegCost(String maxVegCost) {
		this.maxVegCost = maxVegCost;
	}

	public String getMinNonVegCost() {
		return minNonVegCost;
	}

	public void setMinNonVegCost(String minNonVegCost) {
		this.minNonVegCost = minNonVegCost;
	}

	public String getMaxNonVegCost() {
		return maxNonVegCost;
	}

	public void setMaxNonVegCost(String maxNonVegCost) {
		this.maxNonVegCost = maxNonVegCost;
	}

	public Boolean getIsFullyAc() {
		return isFullyAc;
	}

	public void setIsFullyAc(Boolean isFullyAc) {
		this.isFullyAc = isFullyAc;
	}

	public Boolean getTentingAvlbl() {
		return tentingAvlbl;
	}

	public void setTentingAvlbl(Boolean tentingAvlbl) {
		this.tentingAvlbl = tentingAvlbl;
	}

	public Boolean getFloralAvlbl() {
		return floralAvlbl;
	}

	public void setFloralAvlbl(Boolean floralAvlbl) {
		this.floralAvlbl = floralAvlbl;
	}

	public Boolean getLightingAvlbl() {
		return lightingAvlbl;
	}

	public void setLightingAvlbl(Boolean lightingAvlbl) {
		this.lightingAvlbl = lightingAvlbl;
	}

	public Boolean getBalloonsAvlbl() {
		return balloonsAvlbl;
	}

	public void setBalloonsAvlbl(Boolean balloonsAvlbl) {
		this.balloonsAvlbl = balloonsAvlbl;
	}

	public Boolean getCandlesAvlbl() {
		return candlesAvlbl;
	}

	public void setCandlesAvlbl(Boolean candlesAvlbl) {
		this.candlesAvlbl = candlesAvlbl;
	}
}
