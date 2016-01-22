package lepartycious.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="venue_rooms")
public class VenueRooms implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908521309090L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="venue_id")
	private Venue venueId;
	
	@Column(name="room_id")
	private Room roomId;
	
	@Column(name="min_cost")
	private float minCost;
	
	@Column(name="ac_availability")
	private boolean acAvailability;
	
	@Column(name = "fridge_availability")
	private boolean fridgeAvailability;
	
	@Column(name="locker_availability")
	private boolean lockerAvailability;
	
	@Column(name = "led_availability")
	private boolean ledAvailability;
	
	@Column(name = "attached_bathroom_availability")
	private boolean attachedBathroomAvailability;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Version
	private long version;

	public Venue getVenueId() {
		return venueId;
	}

	public void setVenueId(Venue venueId) {
		this.venueId = venueId;
	}

	public Room getRoomId() {
		return roomId;
	}

	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}

	public float getMinCost() {
		return minCost;
	}

	public void setMinCost(float minCost) {
		this.minCost = minCost;
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

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
}
