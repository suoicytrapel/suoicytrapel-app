package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

import lepartycious.models.Amenities;

public class VenueAmenitiesDTO implements Serializable{
	
	private Long amenitiesId;
	private Float minCost;
	private Float maxCost;
	private String amenityName;
	private String maxAccomodationCapacity;
	private String minAccomodation;
	private Boolean isFullyAc;
	private String additionalInfo;
	private String minVegCost;
	private String maxVegCost;
	private String minNonVegCost;
	private String maxNonVegCost;
	private String amenityType;
	
	public String getAmenityType() {
		return amenityType;
	}
	public void setAmenityType(String amenityType) {
		this.amenityType = amenityType;
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
	public String getAmenityName() {
		return amenityName;
	}
	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}
	public String getMaxAccomodationCapacity() {
		return maxAccomodationCapacity;
	}
	public void setMaxAccomodationCapacity(String maxAccomodationCapacity) {
		this.maxAccomodationCapacity = maxAccomodationCapacity;
	}
	public String getMinAccomodation() {
		return minAccomodation;
	}
	public void setMinAccomodation(String minAccomodation) {
		this.minAccomodation = minAccomodation;
	}
	public Boolean getIsFullyAc() {
		return isFullyAc;
	}
	public void setIsFullyAc(Boolean isFullyAc) {
		this.isFullyAc = isFullyAc;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getMinVegCost() {
		return minVegCost;
	}
	public void setMinVegCost(String minVegCost) {
		this.minVegCost = minVegCost;
	}
	public String getMaxVegCost() {
		return maxVegCost;
	}
	public void setMaxVegCost(String maxVegCost) {
		this.maxVegCost = maxVegCost;
	}
	public String getMinNonVegCost() {
		return minNonVegCost;
	}
	public void setMinNonVegCost(String minNonVegCost) {
		this.minNonVegCost = minNonVegCost;
	}
	public String getMaxNonVegCost() {
		return maxNonVegCost;
	}
	public void setMaxNonVegCost(String maxNonVegCost) {
		this.maxNonVegCost = maxNonVegCost;
	}
	public Long getAmenitiesId() {
		return amenitiesId;
	}
	public void setAmenitiesId(Long amenitiesId) {
		this.amenitiesId = amenitiesId;
	}
}
