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
	
	@Column(name = "normal_photography")
	private Boolean normalPhotography;
	
	@Column(name = "min_normal_photography_charges")
	private Float minNormalPhotographyCharges;
	
	@Column(name = "candid_photography")
	private Boolean candidPhotography;
	
	@Column(name = "min_candid_photography_charges")
	private Float minCandidPhotographyCharges;
	
	@Column(name = "pre_wedding_shoot")
	private Boolean preWeddingShoot;
	
	@Column(name = "pre_wedding_shoot_charges")
	private Float preWeddingShootCharges;
	
	@Column(name = "album_making")
	private Boolean albumMaking;
	
	@Column(name = "album_making_charges")
	private Float albumMakingCharges;
	
	@Column(name = "dvd_making")
	private Boolean dvdMaking;
	
	@Column(name = "dvd_making_charges")
	private Float dvdMakingCharges;
	
	@Column(name="priority")
	private Long priority;

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

	public Boolean getNormalPhotography() {
		return normalPhotography;
	}

	public void setNormalPhotography(Boolean normalPhotography) {
		this.normalPhotography = normalPhotography;
	}

	public Float getMinNormalPhotographyCharges() {
		return minNormalPhotographyCharges;
	}

	public void setMinNormalPhotographyCharges(Float minNormalPhotographyCharges) {
		this.minNormalPhotographyCharges = minNormalPhotographyCharges;
	}

	public Boolean getCandidPhotography() {
		return candidPhotography;
	}

	public void setCandidPhotography(Boolean candidPhotography) {
		this.candidPhotography = candidPhotography;
	}

	public Float getMinCandidPhotographyCharges() {
		return minCandidPhotographyCharges;
	}

	public void setMinCandidPhotographyCharges(Float minCandidPhotographyCharges) {
		this.minCandidPhotographyCharges = minCandidPhotographyCharges;
	}

	public Boolean getPreWeddingShoot() {
		return preWeddingShoot;
	}

	public void setPreWeddingShoot(Boolean preWeddingShoot) {
		this.preWeddingShoot = preWeddingShoot;
	}

	public Float getPreWeddingShootCharges() {
		return preWeddingShootCharges;
	}

	public void setPreWeddingShootCharges(Float preWeddingShootCharges) {
		this.preWeddingShootCharges = preWeddingShootCharges;
	}

	public Boolean getAlbumMaking() {
		return albumMaking;
	}

	public void setAlbumMaking(Boolean albumMaking) {
		this.albumMaking = albumMaking;
	}

	public Float getAlbumMakingCharges() {
		return albumMakingCharges;
	}

	public void setAlbumMakingCharges(Float albumMakingCharges) {
		this.albumMakingCharges = albumMakingCharges;
	}

	public Boolean getDvdMaking() {
		return dvdMaking;
	}

	public void setDvdMaking(Boolean dvdMaking) {
		this.dvdMaking = dvdMaking;
	}

	public Float getDvdMakingCharges() {
		return dvdMakingCharges;
	}

	public void setDvdMakingCharges(Float dvdMakingCharges) {
		this.dvdMakingCharges = dvdMakingCharges;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}
}
