package lepartycious.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private float rating;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@Column(name = "veg_menu")
	private String vegMenu;
	
	@Column(name = "non_veg_menu")
	private String nonVegMenu;
	
	@Column(name = "only_veg")
	private boolean onlyVeg;
	
	@Column(name = "waiters")
	private boolean waiters;
	
	@Column(name = "crockery")
	private boolean crockery;
	
	@Column(name = "counters")
	private boolean counters;
	
	@Column(name = "min_veg_plate_charges")
	private float minVegPlateCharges;
	
	@Column(name = "min_nonveg_plate_charges")
	private float minNonvegPlateCharges;
	
	@Column(name = "city_id")
	private City cityId;
	
	@Version
	private long version;

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
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
