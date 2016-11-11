package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class AdditionalEntityServicesDTO implements Serializable{
	
	private Long additionalServiceId;
	private Long minCost;
	private Long maxCost;
	private String maxCapacity;
	public Long getAdditionalServiceId() {
		return additionalServiceId;
	}
	public void setAdditionalServiceId(Long additionalServiceId) {
		this.additionalServiceId = additionalServiceId;
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
	public String getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
}
