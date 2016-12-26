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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="miscellaneous")
public class Others implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656576599997423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long othersId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="miscellaneous_type")
	private String othersType;
	
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
	
	@Column(name="priority")
	private Long priority;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityServices> services;
	
	@Column(name = "addition_details")
	private String additionalDetails;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityFilters> filters;
	
	@Column(name = "starting_price")
	private String startingPrice;
	
	@Column(name = "max_capacity")
	private String maxCapacity;
	
	@Column(name = "serving_since")
	private String servingSince;
	
	@Column(name = "min_capacity")
	private String minCapacity;
	
	@Column(name = "policies")
	private String policies;
	
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

	public String getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(String maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public long getOthersId() {
		return othersId;
	}

	public void setOthersId(long othersId) {
		this.othersId = othersId;
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

	public String getOthersType() {
		return othersType;
	}

	public void setOthersType(String othersType) {
		this.othersType = othersType;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public List<EntityServices> getServices() {
		return services;
	}

	public void setServices(List<EntityServices> services) {
		this.services = services;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public List<EntityFilters> getFilters() {
		return filters;
	}

	public void setFilters(List<EntityFilters> filters) {
		this.filters = filters;
	}

	public String getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}

	public String getMinCapacity() {
		return minCapacity;
	}

	public void setMinCapacity(String minCapacity) {
		this.minCapacity = minCapacity;
	}
	
}
