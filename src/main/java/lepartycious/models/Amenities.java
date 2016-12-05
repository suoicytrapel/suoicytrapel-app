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
@Table(name="amenities")
public class Amenities implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908521334343L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long amenitiesId;
	
	@Column(name = "amenity_type", nullable=false)
	private String amenityType;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amenity_dimensions")
	private String amenityDimensions;
	
	@Column(name = "is_indoor_amenity")
	private Boolean isIndoorAmenity;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="amenitiesId")
	private List<VenueAmenities> venueamenities;
	
	@Column(name = "is_filter")
	private Boolean isFilter;
	
	@Column(name = "is_detailed_amenity")
	private Boolean isDetailedAmenity;
	
	@Column(name = "category")
	private String category;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getIsDetailedAmenity() {
		return isDetailedAmenity;
	}

	public void setIsDetailedAmenity(Boolean isDetailedAmenity) {
		this.isDetailedAmenity = isDetailedAmenity;
	}

	public long getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(long amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	public String getAmenityType() {
		return amenityType;
	}

	public void setAmenityType(String amenityType) {
		this.amenityType = amenityType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmenityDimensions() {
		return amenityDimensions;
	}

	public void setAmenityDimensions(String amenityDimensions) {
		this.amenityDimensions = amenityDimensions;
	}

	public Boolean getIsIndoorAmenity() {
		return isIndoorAmenity;
	}

	public void setIsIndoorAmenity(Boolean isIndoorAmenity) {
		this.isIndoorAmenity = isIndoorAmenity;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public List<VenueAmenities> getVenueamenities() {
		return venueamenities;
	}

	public void setVenueamenities(List<VenueAmenities> venueamenities) {
		this.venueamenities = venueamenities;
	}

	public Boolean getIsFilter() {
		return isFilter;
	}

	public void setIsFilter(Boolean isFilter) {
		this.isFilter = isFilter;
	}
}
