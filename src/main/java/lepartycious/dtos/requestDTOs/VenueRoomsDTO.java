package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class VenueRoomsDTO implements Serializable{
	
	private Long roomId;
	private Float minCost;
	private Boolean acAvailability;
	private Boolean fridgeAvailability;
	private Boolean lockerAvailability;
	private Boolean ledAvailability;
	private Boolean attachedBathroomAvailability;
	private Boolean hotWaterAvailability;
	private Integer numberOfRooms;
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Float getMinCost() {
		return minCost;
	}
	public void setMinCost(Float minCost) {
		this.minCost = minCost;
	}
	public Boolean getAcAvailability() {
		return acAvailability;
	}
	public void setAcAvailability(Boolean acAvailability) {
		this.acAvailability = acAvailability;
	}
	public Boolean getFridgeAvailability() {
		return fridgeAvailability;
	}
	public void setFridgeAvailability(Boolean fridgeAvailability) {
		this.fridgeAvailability = fridgeAvailability;
	}
	public Boolean getLockerAvailability() {
		return lockerAvailability;
	}
	public void setLockerAvailability(Boolean lockerAvailability) {
		this.lockerAvailability = lockerAvailability;
	}
	public Boolean getLedAvailability() {
		return ledAvailability;
	}
	public void setLedAvailability(Boolean ledAvailability) {
		this.ledAvailability = ledAvailability;
	}
	public Boolean getAttachedBathroomAvailability() {
		return attachedBathroomAvailability;
	}
	public void setAttachedBathroomAvailability(Boolean attachedBathroomAvailability) {
		this.attachedBathroomAvailability = attachedBathroomAvailability;
	}
	public Boolean getHotWaterAvailability() {
		return hotWaterAvailability;
	}
	public void setHotWaterAvailability(Boolean hotWaterAvailability) {
		this.hotWaterAvailability = hotWaterAvailability;
	}
	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
}
