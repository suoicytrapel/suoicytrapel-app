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
import javax.persistence.Version;

@Entity
@Table(name="entertainment")
public class Entertainment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656576573287423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long entertainmentId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="entertainment_type")
	private String entertainmentType;
	
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
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "travel_stay_expenses")
	private String travelStayExpenses;
	
	@Column(name = "is_group")
	private Boolean isGroup;
	
	@Column(name = "additional_info")
	private String additionalInfo;
	
	@Column(name = "serving_since")
	private String servingSince;
	
	@Column(name = "policies")
	private String policies;

	@Column(name = "min_capacity")
	private String minCapacity;
	
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

	public long getEntertainmentId() {
		return entertainmentId;
	}

	public void setEntertainmentId(long entertainmentId) {
		this.entertainmentId = entertainmentId;
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

	public String getEntertainmentType() {
		return entertainmentType;
	}

	public void setEntertainmentType(String entertainmentType) {
		this.entertainmentType = entertainmentType;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTravelStayExpenses() {
		return travelStayExpenses;
	}

	public void setTravelStayExpenses(String travelStayExpenses) {
		this.travelStayExpenses = travelStayExpenses;
	}

	public Boolean isGroup() {
		return isGroup;
	}

	public void setGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}
