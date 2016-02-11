package lepartycious.dtos.responseDTOs;

import java.io.Serializable;

public class TabResponseDTO implements Serializable{
	
	private String name;
	private boolean acAvailability;
	private boolean fridgeAvailability;
	private boolean lockerAvailability;
	private boolean ledAvailability;
	private boolean attachedBathroomAvailability;
	private boolean hotWaterAvailability;
	private float minCost;
	
	public TabResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TabResponseDTO(String name, boolean acAvailability,
			boolean fridgeAvailability, boolean lockerAvailability,
			boolean ledAvailability, boolean attachedBathroomAvailability,
			boolean hotWaterAvailability, float minCost) {
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
	public boolean isAcAvailability() {
		return acAvailability;
	}
	public void setAcAvailability(boolean acAvailability) {
		this.acAvailability = acAvailability;
	}
	public boolean isFridgeAvailability() {
		return fridgeAvailability;
	}
	public void setFridgeAvailability(boolean fridgeAvailability) {
		this.fridgeAvailability = fridgeAvailability;
	}
	public boolean isLockerAvailability() {
		return lockerAvailability;
	}
	public void setLockerAvailability(boolean lockerAvailability) {
		this.lockerAvailability = lockerAvailability;
	}
	public boolean isLedAvailability() {
		return ledAvailability;
	}
	public void setLedAvailability(boolean ledAvailability) {
		this.ledAvailability = ledAvailability;
	}
	public boolean isAttachedBathroomAvailability() {
		return attachedBathroomAvailability;
	}
	public void setAttachedBathroomAvailability(boolean attachedBathroomAvailability) {
		this.attachedBathroomAvailability = attachedBathroomAvailability;
	}
	public boolean isHotWaterAvailability() {
		return hotWaterAvailability;
	}
	public void setHotWaterAvailability(boolean hotWaterAvailability) {
		this.hotWaterAvailability = hotWaterAvailability;
	}
	public float getMinCost() {
		return minCost;
	}
	public void setMinCost(float minCost) {
		this.minCost = minCost;
	}
}
