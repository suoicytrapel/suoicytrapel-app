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
@Table(name="additional_entity_services")
public class AdditionalEntityServices implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1465690659378090L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="entity_id")
	private Long entityId;
	
	@ManyToOne
	@JoinColumn(name="additional_service_id")
	private AdditionalService additionalServiceId;
	
	@Column(name="min_service_cost")
	private Long minCost;
	
	@Column(name="max_service_cost")
	private Long maxCost;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "max_capacity")
	private String maxCapacity;

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

	public AdditionalService getAdditionalServiceId() {
		return additionalServiceId;
	}

	public void setAdditionalServiceId(AdditionalService additionalServiceId) {
		this.additionalServiceId = additionalServiceId;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public String getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Long getMinCost() {
		return minCost;
	}

	public void setMinCost(Long minCost) {
		this.minCost = minCost;
	}

	public Long getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(Long maxCost) {
		this.maxCost = maxCost;
	}
	
}
