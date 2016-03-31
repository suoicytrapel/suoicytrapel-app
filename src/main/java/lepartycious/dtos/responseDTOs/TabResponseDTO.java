package lepartycious.dtos.responseDTOs;

import java.io.Serializable;
import java.util.List;

public class TabResponseDTO implements Serializable{
	
	private String name;
	private Boolean acAvailability;
	private Boolean fridgeAvailability;
	private Boolean lockerAvailability;
	private Boolean ledAvailability;
	private Boolean attachedBathroomAvailability;
	private Boolean hotWaterAvailability;
	private Float minCost;
	private String minCapacity;
	private String maxCapacity;
	private String minVegPrice;
	private String minNonVegPrice;
	private String maxCost;
	private String experience;
	private String outstationExpenses;
	private String genre;
	private Boolean tentingAvlbl;
	private Boolean floralAvlbl;
	private Boolean lightingAvlbl;
	private Boolean balloonsAvlbl;
	private Boolean candlesAvlbl;
	private List<AttachmentResponseDTO> attachments;
	private String amenityName;
	
	public TabResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TabResponseDTO(String name, Boolean acAvailability,
			Boolean fridgeAvailability, Boolean lockerAvailability,
			Boolean ledAvailability, Boolean attachedBathroomAvailability,
			Boolean hotWaterAvailability, Float minCost) {
		super();
		this.name = name;
		this.acAvailability = acAvailability;
		this.fridgeAvailability = fridgeAvailability;
		this.lockerAvailability = lockerAvailability;
		this.ledAvailability = ledAvailability;
		this.attachedBathroomAvailability = attachedBathroomAvailability;
		this.hotWaterAvailability = hotWaterAvailability;
		this.minCost = minCost;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean isAcAvailability() {
		return acAvailability;
	}
	public void setAcAvailability(Boolean acAvailability) {
		this.acAvailability = acAvailability;
	}
	public Boolean isFridgeAvailability() {
		return fridgeAvailability;
	}
	public void setFridgeAvailability(Boolean fridgeAvailability) {
		this.fridgeAvailability = fridgeAvailability;
	}
	public Boolean isLockerAvailability() {
		return lockerAvailability;
	}
	public void setLockerAvailability(Boolean lockerAvailability) {
		this.lockerAvailability = lockerAvailability;
	}
	public Boolean isLedAvailability() {
		return ledAvailability;
	}
	public void setLedAvailability(Boolean ledAvailability) {
		this.ledAvailability = ledAvailability;
	}
	public Boolean isAttachedBathroomAvailability() {
		return attachedBathroomAvailability;
	}
	public void setAttachedBathroomAvailability(Boolean attachedBathroomAvailability) {
		this.attachedBathroomAvailability = attachedBathroomAvailability;
	}
	public Boolean isHotWaterAvailability() {
		return hotWaterAvailability;
	}
	public void setHotWaterAvailability(Boolean hotWaterAvailability) {
		this.hotWaterAvailability = hotWaterAvailability;
	}
	public Float getMinCost() {
		return minCost;
	}
	public void setMinCost(Float minCost) {
		this.minCost = minCost;
	}

	public String getMinCapacity() {
		return minCapacity;
	}

	public void setMinCapacity(String minCapacity) {
		this.minCapacity = minCapacity;
	}

	public String getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getMinVegPrice() {
		return minVegPrice;
	}

	public void setMinVegPrice(String minVegPrice) {
		this.minVegPrice = minVegPrice;
	}

	public String getMinNonVegPrice() {
		return minNonVegPrice;
	}

	public void setMinNonVegPrice(String minNonVegPrice) {
		this.minNonVegPrice = minNonVegPrice;
	}

	public String getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(String maxCost) {
		this.maxCost = maxCost;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getOutstationExpenses() {
		return outstationExpenses;
	}

	public void setOutstationExpenses(String outstationExpenses) {
		this.outstationExpenses = outstationExpenses;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Boolean getTentingAvlbl() {
		return tentingAvlbl;
	}

	public void setTentingAvlbl(Boolean tentingAvlbl) {
		this.tentingAvlbl = tentingAvlbl;
	}

	public Boolean getFloralAvlbl() {
		return floralAvlbl;
	}

	public void setFloralAvlbl(Boolean floralAvlbl) {
		this.floralAvlbl = floralAvlbl;
	}

	public Boolean getLightingAvlbl() {
		return lightingAvlbl;
	}

	public void setLightingAvlbl(Boolean lightingAvlbl) {
		this.lightingAvlbl = lightingAvlbl;
	}

	public Boolean getBalloonsAvlbl() {
		return balloonsAvlbl;
	}

	public void setBalloonsAvlbl(Boolean balloonsAvlbl) {
		this.balloonsAvlbl = balloonsAvlbl;
	}

	public Boolean getCandlesAvlbl() {
		return candlesAvlbl;
	}

	public void setCandlesAvlbl(Boolean candlesAvlbl) {
		this.candlesAvlbl = candlesAvlbl;
	}

	public Boolean getAcAvailability() {
		return acAvailability;
	}

	public Boolean getFridgeAvailability() {
		return fridgeAvailability;
	}

	public Boolean getLockerAvailability() {
		return lockerAvailability;
	}

	public Boolean getLedAvailability() {
		return ledAvailability;
	}

	public Boolean getAttachedBathroomAvailability() {
		return attachedBathroomAvailability;
	}

	public Boolean getHotWaterAvailability() {
		return hotWaterAvailability;
	}

	public List<AttachmentResponseDTO> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentResponseDTO> attachments) {
		this.attachments = attachments;
	}

	public String getAmenityName() {
		return amenityName;
	}

	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}
}
