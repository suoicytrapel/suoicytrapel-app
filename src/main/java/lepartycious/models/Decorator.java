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
@Table(name="decorator")
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
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "rating")
	private float rating;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="entityId")
	private List<Attachment> attachments;
	
	@OneToMany(mappedBy="entityId")
	private List<Address> addresses;
	
	@Column(name = "city_id")
	private City cityId;
	
	@Column(name = "tent_availability")
	private boolean tentAvailability;
	
	@Column(name = "min_tent_charges")
	private float minTentCharges;
	
	@Column(name = "lighting_availability")
	private boolean lightingAvailability;
	
	@Column(name = "min_lighting_charges")
	private float minLightingCharges;
	
	@Column(name = "floral_works_availability")
	private boolean floralWorksAvailability;
	
	@Column(name = "min_floral_works_charges")
	private float minFloralWorksCharges;
	
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

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	}

	public boolean isTentAvailability() {
		return tentAvailability;
	}

	public void setTentAvailability(boolean tentAvailability) {
		this.tentAvailability = tentAvailability;
	}

	public float getMinTentCharges() {
		return minTentCharges;
	}

	public void setMinTentCharges(float minTentCharges) {
		this.minTentCharges = minTentCharges;
	}

	public boolean isLightingAvailability() {
		return lightingAvailability;
	}

	public void setLightingAvailability(boolean lightingAvailability) {
		this.lightingAvailability = lightingAvailability;
	}

	public float getMinLightingCharges() {
		return minLightingCharges;
	}

	public void setMinLightingCharges(float minLightingCharges) {
		this.minLightingCharges = minLightingCharges;
	}

	public boolean isFloralWorksAvailability() {
		return floralWorksAvailability;
	}

	public void setFloralWorksAvailability(boolean floralWorksAvailability) {
		this.floralWorksAvailability = floralWorksAvailability;
	}

	public float getMinFloralWorksCharges() {
		return minFloralWorksCharges;
	}

	public void setMinFloralWorksCharges(float minFloralWorksCharges) {
		this.minFloralWorksCharges = minFloralWorksCharges;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
