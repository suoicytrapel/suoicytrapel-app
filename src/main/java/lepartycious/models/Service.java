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
@Table(name="service")
public class Service implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908230933423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long serviceId;
	
	@Column(name = "service_type", nullable=false)
	private String serviceType;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="serviceId")
	private List<VenueServices> venueServices;
	
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public List<VenueServices> getVenueServices() {
		return venueServices;
	}

	public void setVenueServices(List<VenueServices> venueServices) {
		this.venueServices = venueServices;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	
}