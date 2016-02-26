package lepartycious.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="venue")
public class Venue implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14326788521309090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long venueId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "type", nullable=false)
	private String type;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "rating")
	private Float rating;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="entityId")
	private List<Attachment> attachments;
	
	@OneToMany(mappedBy="entityId")
	private List<Address> addresses;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "locality_id")
	private Locality locality;
	
	@OneToMany(mappedBy="venueId")
	private List<VenueAmenities> venueamenities;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityServices> venueServices;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityFilters> venueFilters;
	
	@OneToMany(mappedBy="venueId")
	private List<VenueRooms> venueRooms;
	
	@Column(name="priority")
	private Long priority;

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<VenueAmenities> getVenueamenities() {
		return venueamenities;
	}

	public void setVenueamenities(List<VenueAmenities> venueamenities) {
		this.venueamenities = venueamenities;
	}

	public List<EntityServices> getVenueServices() {
		return venueServices;
	}

	public void setVenueServices(List<EntityServices> venueServices) {
		this.venueServices = venueServices;
	}

	public List<VenueRooms> getVenueRooms() {
		return venueRooms;
	}

	public void setVenueRooms(List<VenueRooms> venueRooms) {
		this.venueRooms = venueRooms;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public List<EntityFilters> getVenueFilters() {
		return venueFilters;
	}

	public void setVenueFilters(List<EntityFilters> venueFilters) {
		this.venueFilters = venueFilters;
	}
	
}
