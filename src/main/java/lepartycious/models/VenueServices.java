package lepartycious.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="venue_services")
public class VenueServices implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1465690852378090L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="venue_id")
	private Venue venueId;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service serviceId;
	
	@Column(name="min_cost")
	private Float minCost;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	public Venue getVenueId() {
		return venueId;
	}

	public void setVenueId(Venue venueId) {
		this.venueId = venueId;
	}

	public Service getServiceId() {
		return serviceId;
	}

	public void setServiceId(Service serviceId) {
		this.serviceId = serviceId;
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
	
}
