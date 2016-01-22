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
	private float rating;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="entityId")
	private List<Attachment> attachments;
	
	@OneToMany(mappedBy="entityId")
	private List<Address> addresses;
	
	@Column(name = "city_id")
	private City cityId;
	
	@Column(name = "normal_photography")
	private boolean normalPhotography;
	
	@Column(name = "min_normal_photography_charges")
	private float minNormalPhotographyCharges;
	
	@Column(name = "candid_photography")
	private boolean candidPhotography;
	
	@Column(name = "min_candid_photography_charges")
	private float minCandidPhotographyCharges;
	
	@Column(name = "pre_wedding_shoot")
	private boolean preWeddingShoot;
	
	@Column(name = "pre_wedding_shoot_charges")
	private float preWeddingShootCharges;
	
	@Column(name = "album_making")
	private boolean albumMaking;
	
	@Column(name = "album_making_charges")
	private float albumMakingCharges;
	
	@Column(name = "dvd_making")
	private boolean dvdMaking;
	
	@Column(name = "dvd_making_charges")
	private float dvdMakingCharges;
	
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

	public boolean isNormalPhotography() {
		return normalPhotography;
	}

	public void setNormalPhotography(boolean normalPhotography) {
		this.normalPhotography = normalPhotography;
	}

	public float getMinNormalPhotographyCharges() {
		return minNormalPhotographyCharges;
	}

	public void setMinNormalPhotographyCharges(float minNormalPhotographyCharges) {
		this.minNormalPhotographyCharges = minNormalPhotographyCharges;
	}

	public boolean isCandidPhotography() {
		return candidPhotography;
	}

	public void setCandidPhotography(boolean candidPhotography) {
		this.candidPhotography = candidPhotography;
	}

	public float getMinCandidPhotographyCharges() {
		return minCandidPhotographyCharges;
	}

	public void setMinCandidPhotographyCharges(float minCandidPhotographyCharges) {
		this.minCandidPhotographyCharges = minCandidPhotographyCharges;
	}

	public boolean isPreWeddingShoot() {
		return preWeddingShoot;
	}

	public void setPreWeddingShoot(boolean preWeddingShoot) {
		this.preWeddingShoot = preWeddingShoot;
	}

	public float getPreWeddingShootCharges() {
		return preWeddingShootCharges;
	}

	public void setPreWeddingShootCharges(float preWeddingShootCharges) {
		this.preWeddingShootCharges = preWeddingShootCharges;
	}

	public boolean isAlbumMaking() {
		return albumMaking;
	}

	public void setAlbumMaking(boolean albumMaking) {
		this.albumMaking = albumMaking;
	}

	public float getAlbumMakingCharges() {
		return albumMakingCharges;
	}

	public void setAlbumMakingCharges(float albumMakingCharges) {
		this.albumMakingCharges = albumMakingCharges;
	}

	public boolean isDvdMaking() {
		return dvdMaking;
	}

	public void setDvdMaking(boolean dvdMaking) {
		this.dvdMaking = dvdMaking;
	}

	public float getDvdMakingCharges() {
		return dvdMakingCharges;
	}

	public void setDvdMakingCharges(float dvdMakingCharges) {
		this.dvdMakingCharges = dvdMakingCharges;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
