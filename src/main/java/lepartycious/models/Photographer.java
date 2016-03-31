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
@Table(name="photographer")
public class Photographer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908222309090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long photographerId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
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
	
	@Column(name = "starting_price")
	private String startingPrice;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityServices> photographerServices;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityFilters> photographerFilters;
	
	@Column(name = "max_capacity")
	private String maxCapacity;
	
	@Column(name = "travel_stay_expenses")
	private String travelStayExpenses;
	
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

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}
	
	public long getPhotographerId() {
		return photographerId;
	}

	public void setPhotographerId(long photographerId) {
		this.photographerId = photographerId;
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

	public List<EntityServices> getPhotographerServices() {
		return photographerServices;
	}

	public void setPhotographerServices(List<EntityServices> photographerServices) {
		this.photographerServices = photographerServices;
	}

	public List<EntityFilters> getPhotographerFilters() {
		return photographerFilters;
	}

	public void setPhotographerFilters(List<EntityFilters> photographerFilters) {
		this.photographerFilters = photographerFilters;
	}

	public String getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}

	public String getTravelStayExpenses() {
		return travelStayExpenses;
	}

	public void setTravelStayExpenses(String travelStayExpenses) {
		this.travelStayExpenses = travelStayExpenses;
	}
}
