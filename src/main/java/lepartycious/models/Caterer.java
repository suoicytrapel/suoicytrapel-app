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
@Table(name="caterer")
public class Caterer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656929090876090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long caterer_id;
	
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
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "rating")
	private Float rating;
	
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

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public long getCaterer_id() {
		return caterer_id;
	}

	public void setCaterer_id(long caterer_id) {
		this.caterer_id = caterer_id;
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
	
	
}
