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
	
	@Column(name = "tent_availability")
	private Boolean tentAvailability;
	
	@Column(name = "min_tent_charges")
	private Float minTentCharges;
	
	@Column(name = "lighting_availability")
	private Boolean lightingAvailability;
	
	@Column(name = "min_lighting_charges")
	private Float minLightingCharges;
	
	@Column(name = "floral_works_availability")
	private Boolean floralWorksAvailability;
	
	@Column(name = "min_floral_works_charges")
	private Float minFloralWorksCharges;
	
	@Column(name="priority")
	private Long priority;

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

	public Boolean getTentAvailability() {
		return tentAvailability;
	}

	public void setTentAvailability(Boolean tentAvailability) {
		this.tentAvailability = tentAvailability;
	}

	public Float getMinTentCharges() {
		return minTentCharges;
	}

	public void setMinTentCharges(Float minTentCharges) {
		this.minTentCharges = minTentCharges;
	}

	public Boolean getLightingAvailability() {
		return lightingAvailability;
	}

	public void setLightingAvailability(Boolean lightingAvailability) {
		this.lightingAvailability = lightingAvailability;
	}

	public Float getMinLightingCharges() {
		return minLightingCharges;
	}

	public void setMinLightingCharges(Float minLightingCharges) {
		this.minLightingCharges = minLightingCharges;
	}

	public Boolean getFloralWorksAvailability() {
		return floralWorksAvailability;
	}

	public void setFloralWorksAvailability(Boolean floralWorksAvailability) {
		this.floralWorksAvailability = floralWorksAvailability;
	}

	public Float getMinFloralWorksCharges() {
		return minFloralWorksCharges;
	}

	public void setMinFloralWorksCharges(Float minFloralWorksCharges) {
		this.minFloralWorksCharges = minFloralWorksCharges;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}
}
