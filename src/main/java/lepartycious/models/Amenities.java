package lepartycious.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private boolean isIndoorAmenity;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Version
	private long version;

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

	public boolean isIndoorAmenity() {
		return isIndoorAmenity;
	}

	public void setIndoorAmenity(boolean isIndoorAmenity) {
		this.isIndoorAmenity = isIndoorAmenity;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

}
