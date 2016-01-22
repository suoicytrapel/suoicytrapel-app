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
@Table(name="room")
public class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908521333423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long roomId;
	
	@Column(name = "room_type", nullable=false)
	private String roomType;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "room_dimensions")
	private String roomDimensions;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Version
	private long version;

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomDimensions() {
		return roomDimensions;
	}

	public void setRoomDimensions(String roomDimensions) {
		this.roomDimensions = roomDimensions;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
