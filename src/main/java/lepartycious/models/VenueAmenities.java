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
}
