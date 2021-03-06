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
@Table(name="decorater")
public class Decorator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656576573233423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long decoratorId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
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
	
	@OneToMany(mappedBy="entityId")
	private List<EntityServices> decoratorServices;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityFilters> decoratorFilters;
	
	@Column(name="priority")
	private Long priority;
	
	@Column(name = "starting_price")
	private String startingPrice;
	
	@Column(name = "max_capacity")
	private String maxCapacity;
	
	@Column(name = "serving_since")
	private String servingSince;
	
	@Column(name = "policies")
	private String policies;
	
	@Column(name = "min_capacity")
	private String minCapacity;
	
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

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public long getDecoratorId() {
		return decoratorId;
	}

	public void setDecoratorId(long decoratorId) {
		this.decoratorId = decoratorId;
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

	public List<EntityServices> getDecoratorServices() {
		return decoratorServices;
	}

	public void setDecoratorServices(List<EntityServices> decoratorServices) {
		this.decoratorServices = decoratorServices;
	}

	public List<EntityFilters> getDecoratorFilters() {
		return decoratorFilters;
	}

	public void setDecoratorFilters(List<EntityFilters> decoratorFilters) {
		this.decoratorFilters = decoratorFilters;
	}

	public String getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}
	
}
