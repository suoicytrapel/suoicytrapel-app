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
	
	@Column(name="venue_id")
	private Venue venueId;
	
	@Column(name="amenities_id")
	private Amenities amenitiesId;
	
	@Column(name="min_cost")
	private float minCost;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Version
	private long version;

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

	public float getMinCost() {
		return minCost;
	}

	public void setMinCost(float minCost) {
		this.minCost = minCost;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
}
