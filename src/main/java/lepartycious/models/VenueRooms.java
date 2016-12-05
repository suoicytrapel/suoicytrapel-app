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
	
	@ManyToOne
	@JoinColumn(name="venue_id")
	private Venue venueId;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room roomId;
	
	@Column(name="min_cost")
	private Float minCost;
	
	@Column(name="ac_availability")
	private Boolean acAvailability;
	
	@Column(name = "fridge_availability")
	private Boolean fridgeAvailability;
	
	@Column(name="locker_availability")
	private Boolean lockerAvailability;
	
	@Column(name = "led_availability")
	private Boolean ledAvailability;
	
	@Column(name = "attached_bathroom_availability")
	private Boolean attachedBathroomAvailability;
	
	@Column(name = "hot_water_availability")
	private Boolean hotWaterAvailability;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "number_of_rooms")
	private Integer numberOfRooms;
	
	public VenueRooms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VenueRooms(Room roomId, Float minCost, Boolean acAvailability,
			Boolean fridgeAvailability, Boolean lockerAvailability,
			Boolean ledAvailability, Boolean attachedBathroomAvailability,
			Boolean hotWaterAvailability, Integer numberOfRooms) {
		super();
		this.roomId = roomId;
		this.minCost = minCost;
		this.acAvailability = acAvailability;
		this.fridgeAvailability = fridgeAvailability;
		this.lockerAvailability = lockerAvailability;
		this.ledAvailability = ledAvailability;
		this.attachedBathroomAvailability = attachedBathroomAvailability;
		this.hotWaterAvailability = hotWaterAvailability;
		this.numberOfRooms = numberOfRooms;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
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
