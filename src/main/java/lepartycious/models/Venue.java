package lepartycious.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="entityId", cascade=CascadeType.ALL)
	private List<Attachment> attachments;
	
	@OneToMany(mappedBy="entityId", cascade=CascadeType.ALL)
	private List<Address> addresses;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "locality_id")
	private Locality locality;
	
	@OneToMany(mappedBy="venueId", cascade=CascadeType.ALL)
	private List<VenueAmenities> venueamenities;
	
	@OneToMany(mappedBy="entityId", cascade=CascadeType.ALL)
	private List<EntityServices> venueServices;
	
	@OneToMany(mappedBy="entityId", cascade=CascadeType.ALL)
	private List<EntityFilters> venueFilters;
	
	@OneToMany(mappedBy="venueId", cascade=CascadeType.ALL)
	private List<VenueRooms> venueRooms;
	
	@Column(name="priority")
	private Long priority;
	
	@Column(name = "starting_price")
	private String startingPrice;
	
	@Column(name = "max_capacity")
	private String maxCapacity;
	
	@Column(name = "policies")
	private String policies;
	
	@Column(name = "serving_since")
	private String servingSince;
	
	@Column(name = "min_capacity")
	private String minCapacity;
	
	@OneToMany(mappedBy="venue")
	private List<VenuePackages> venuePackages;
	
	@OneToMany(mappedBy="entityId")
	private List<AdditionalEntityServices> additionalVenueServices;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityReview> comments;
	
	@Column(name="created_by")
	private Long user;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name = "booking_policy")
	private String bookingPolicy;
	
	@Column(name = "info_provider_contact")
	private String infoProviderContact;
	
	@Column(name = "info_provider_name")
	private String infoProviderName;
	
	@Column(name = "website")
	private String websiteName;
	
	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	
	public String getBookingPolicy() {
		return bookingPolicy;
	}

	public void setBookingPolicy(String bookingPolicy) {
		this.bookingPolicy = bookingPolicy;
	}

	public String getInfoProviderContact() {
		return infoProviderContact;
	}

	public void setInfoProviderContact(String infoProviderContact) {
		this.infoProviderContact = infoProviderContact;
	}

	public String getInfoProviderName() {
		return infoProviderName;
	}

	public void setInfoProviderName(String infoProviderName) {
		this.infoProviderName = infoProviderName;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public List<EntityReview> getComments() {
		return comments;
	}

	public void setComments(List<EntityReview> comments) {
		this.comments = comments;
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

	public String getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}

	public String getPolicies() {
		return policies;
	}

	public void setPolicies(String policies) {
		this.policies = policies;
	}

	public String getServingSince() {
		return servingSince;
	}

	public void setServingSince(String servingSince) {
		this.servingSince = servingSince;
	}

	public List<VenuePackages> getVenuePackages() {
		return venuePackages;
	}

	public void setVenuePackages(List<VenuePackages> venuePackages) {
		this.venuePackages = venuePackages;
	}

	public List<AdditionalEntityServices> getAdditionalVenueServices() {
		return additionalVenueServices;
	}

	public void setAdditionalVenueServices(
			List<AdditionalEntityServices> additionalVenueServices) {
		this.additionalVenueServices = additionalVenueServices;
	}

}
