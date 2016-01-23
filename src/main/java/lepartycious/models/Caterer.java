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
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "rating")
	private Float rating;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "veg_menu")
	private String vegMenu;
	
	@Column(name = "non_veg_menu")
	private String nonVegMenu;
	
	@Column(name = "only_veg")
	private Boolean onlyVeg;
	
	@Column(name = "waiters")
	private Boolean waiters;
	
	@Column(name = "crockery")
	private Boolean crockery;
	
	@Column(name = "counters")
	private Boolean counters;
	
	@Column(name = "min_veg_plate_charges")
	private Float minVegPlateCharges;
	
	@Column(name = "min_nonveg_plate_charges")
	private Float minNonvegPlateCharges;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

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

	public String getVegMenu() {
		return vegMenu;
	}

	public void setVegMenu(String vegMenu) {
		this.vegMenu = vegMenu;
	}

	public String getNonVegMenu() {
		return nonVegMenu;
	}

	public void setNonVegMenu(String nonVegMenu) {
		this.nonVegMenu = nonVegMenu;
	}

	public Boolean getOnlyVeg() {
		return onlyVeg;
	}

	public void setOnlyVeg(Boolean onlyVeg) {
		this.onlyVeg = onlyVeg;
	}

	public Boolean getWaiters() {
		return waiters;
	}

	public void setWaiters(Boolean waiters) {
		this.waiters = waiters;
	}

	public Boolean getCrockery() {
		return crockery;
	}

	public void setCrockery(Boolean crockery) {
		this.crockery = crockery;
	}

	public Boolean getCounters() {
		return counters;
	}

	public void setCounters(Boolean counters) {
		this.counters = counters;
	}

	public Float getMinVegPlateCharges() {
		return minVegPlateCharges;
	}

	public void setMinVegPlateCharges(Float minVegPlateCharges) {
		this.minVegPlateCharges = minVegPlateCharges;
	}

	public Float getMinNonvegPlateCharges() {
		return minNonvegPlateCharges;
	}

	public void setMinNonvegPlateCharges(Float minNonvegPlateCharges) {
		this.minNonvegPlateCharges = minNonvegPlateCharges;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
