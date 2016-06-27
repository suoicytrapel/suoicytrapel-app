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
@Table(name="additional_service")
public class AdditionalService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656915450933423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long additionalServiceId;
	
	@Column(name = "service_type", nullable=false)
	private String serviceType;
	
	@Column(name = "service_for_entity")
	private String serviceForEntity;
	
	@Column(name = "tab_data_name")
	private String tabDataName;
	
	@Column(name = "filter_data_name")
	private String filterDataName;
	
	@Column(name = "is_filter")
	private Boolean isFilter;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="additionalServiceId")
	private List<AdditionalEntityServices> addiionalEntityServices;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceForEntity() {
		return serviceForEntity;
	}

	public void setServiceForEntity(String serviceForEntity) {
		this.serviceForEntity = serviceForEntity;
	}

	public String getTabDataName() {
		return tabDataName;
	}

	public void setTabDataName(String tabDataName) {
		this.tabDataName = tabDataName;
	}

	public String getFilterDataName() {
		return filterDataName;
	}

	public void setFilterDataName(String filterDataName) {
		this.filterDataName = filterDataName;
	}

	public Boolean getIsFilter() {
		return isFilter;
	}

	public void setIsFilter(Boolean isFilter) {
		this.isFilter = isFilter;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public long getAdditionalServiceId() {
		return additionalServiceId;
	}

	public void setAdditionalServiceId(long additionalServiceId) {
		this.additionalServiceId = additionalServiceId;
	}

	public List<AdditionalEntityServices> getAddiionalEntityServices() {
		return addiionalEntityServices;
	}

	public void setAddiionalEntityServices(
			List<AdditionalEntityServices> addiionalEntityServices) {
		this.addiionalEntityServices = addiionalEntityServices;
	}
	
}
