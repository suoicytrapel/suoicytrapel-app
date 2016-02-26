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
@Table(name="entity_filters")
public class EntityFilters implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1465690888888090L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="entity_id")
	private Long entityId;
	
	@ManyToOne
	@JoinColumn(name="filter_id")
	private Filter filterId;
	
	@Column(name = "added_on")
	private Date addedOn;
	

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

	public Filter getFilterId() {
		return filterId;
	}

	public void setFilterId(Filter filterId) {
		this.filterId = filterId;
	}

}
