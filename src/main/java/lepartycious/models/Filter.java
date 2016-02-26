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
@Table(name="filters")
public class Filter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656665540933423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long filterId;
	
	@Column(name = "filter_type", nullable=false)
	private String filterType;
	
	@Column(name = "filter_name", nullable=false)
	private String filterName;
	
	@Column(name = "filter_for_entity")
	private String filterForEntity;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="filterId")
	private List<EntityFilters> enityFilters;

	public long getFilterid() {
		return filterId;
	}

	public void setFilterid(long filterid) {
		this.filterId = filterid;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterForEntity() {
		return filterForEntity;
	}

	public void setFilterForEntity(String filterForEntity) {
		this.filterForEntity = filterForEntity;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public List<EntityFilters> getEnityFilters() {
		return enityFilters;
	}

	public void setEnityFilters(List<EntityFilters> enityFilters) {
		this.enityFilters = enityFilters;
	}
	
	
}
