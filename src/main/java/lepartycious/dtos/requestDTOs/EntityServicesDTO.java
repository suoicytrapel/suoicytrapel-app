package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class EntityServicesDTO implements Serializable{
	
	private Long serviceId;
	private Float minCost;
	private Float maxCost;
	private String maxCapacity;
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public Float getMinCost() {
		return minCost;
	}
	public void setMinCost(Float minCost) {
		this.minCost = minCost;
	}
	public Float getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(Float maxCost) {
		this.maxCost = maxCost;
	}
	public String getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
}
