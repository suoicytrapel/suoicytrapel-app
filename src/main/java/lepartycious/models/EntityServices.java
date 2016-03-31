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
@Table(name="entity_services")
public class EntityServices implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1465690852378090L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="entity_id")
	private Long entityId;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service serviceId;
	
	@Column(name="min_service_cost")
	private Float minCost;
	
	@Column(name="max_service_cost")
	private Float maxCost;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "max_capacity")
	private String maxCapacity;
	
	public String getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Float getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(Float maxCost) {
		this.maxCost = maxCost;
	}
}
