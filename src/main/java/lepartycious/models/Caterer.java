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
@Table(name="caterer")
public class Caterer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656929090876090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long catererId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy="entityId")
	private List<Attachment> attachments;
	
	@OneToMany(mappedBy="entityId")
	private List<Address> addresses;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityServices> catererServices;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityFilters> catererFilters;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "cuisine_offered")
	private String cuisineOffered;
	
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
	
	@Column(name = "max_capacity")
	private String maxCapacity;
	
	@Column(name = "min_capacity")
	private String minCapacity;
	
	@Column(name = "serving_since")
	private String servingSince;
	
	@Column(name = "starting_price_nonveg")
	private String startingPriceNonVeg;
	
	@Column(name = "policies")
	private String policies;
	
	@Column(name = "is_pure_veg")
	private Boolean isPureVeg;
	
	@OneToMany(mappedBy="entityId")
	private List<EntityReview> comments;
	
	@Column(name="created_by")
	private Long user;
	
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

	public long getCaterer_id() {
		return catererId;
	}

	public void setCaterer_id(long caterer_id) {
		this.catererId = caterer_id;
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

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
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

	public String getCuisineOffered() {
		return cuisineOffered;
	}

	public void setCuisineOffered(String cuisineOffered) {
		this.cuisineOffered = cuisineOffered;
	}

	public List<EntityServices> getCatererServices() {
		return catererServices;
	}

	public void setCatererServices(List<EntityServices> catererServices) {
		this.catererServices = catererServices;
	}

	public List<EntityFilters> getCatererFilters() {
		return catererFilters;
	}

	public void setCatererFilters(List<EntityFilters> catererFilters) {
		this.catererFilters = catererFilters;
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

	public String getServingSince() {
		return servingSince;
	}

	public void setServingSince(String servingSince) {
		this.servingSince = servingSince;
	}

	public String getStartingPriceNonVeg() {
		return startingPriceNonVeg;
	}

	public void setStartingPriceNonVeg(String startingPriceNonVeg) {
		this.startingPriceNonVeg = startingPriceNonVeg;
	}

	public Boolean getIsPureVeg() {
		return isPureVeg;
	}

	public void setIsPureVeg(Boolean isPureVeg) {
		this.isPureVeg = isPureVeg;
	}
}
