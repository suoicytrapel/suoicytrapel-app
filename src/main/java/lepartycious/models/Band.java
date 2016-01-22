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
@Table(name="band")
public class Band implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908573233423L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bandId;
	
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
	
	@Column(name = "simple_band_availability")
	private boolean simpleBandAvailability;
	
	@Column(name = "min_simple_band_charges")
	private float minSimpleBandCharges;
	
	@Column(name = "crew_band_availability")
	private boolean crewBandAvailability;
	
	@Column(name = "min_crew_band_charges")
	private float minCrewBandCharges;
	
	@Column(name = "military_band_availability")
	private boolean militaryBandAvailability;
	
	@Column(name = "min_military_band_charges")
	private float minMilitaryBandCharges;
	
	@Column(name = "dj_sound_band_availability")
	private boolean djSoundBandAvailability;
	
	@Column(name = "min_dj_sound_band_charges")
	private float minDjSoundBandCharges;
	
	@Column(name = "bhaggi_availability")
	private boolean bhaggiAvailability;
	
	@Column(name = "min_bhaggi_charges")
	private float minBhaggiCharges;
	
	@Column(name = "ghodi_availability")
	private boolean ghodiAvailability;
	
	@Column(name = "min_ghodi_charges")
	private float minGhodiCharges;
	
	@Column(name = "rath_availability")
	private boolean rathAvailability;
	
	@Column(name = "min_rath_charges")
	private float minRathCharges;
	
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

	public boolean isSimpleBandAvailability() {
		return simpleBandAvailability;
	}

	public void setSimpleBandAvailability(boolean simpleBandAvailability) {
		this.simpleBandAvailability = simpleBandAvailability;
	}

	public float getMinSimpleBandCharges() {
		return minSimpleBandCharges;
	}

	public void setMinSimpleBandCharges(float minSimpleBandCharges) {
		this.minSimpleBandCharges = minSimpleBandCharges;
	}

	public boolean isCrewBandAvailability() {
		return crewBandAvailability;
	}

	public void setCrewBandAvailability(boolean crewBandAvailability) {
		this.crewBandAvailability = crewBandAvailability;
	}

	public float getMinCrewBandCharges() {
		return minCrewBandCharges;
	}

	public void setMinCrewBandCharges(float minCrewBandCharges) {
		this.minCrewBandCharges = minCrewBandCharges;
	}

	public boolean isMilitaryBandAvailability() {
		return militaryBandAvailability;
	}

	public void setMilitaryBandAvailability(boolean militaryBandAvailability) {
		this.militaryBandAvailability = militaryBandAvailability;
	}

	public float getMinMilitaryBandCharges() {
		return minMilitaryBandCharges;
	}

	public void setMinMilitaryBandCharges(float minMilitaryBandCharges) {
		this.minMilitaryBandCharges = minMilitaryBandCharges;
	}

	public boolean isDjSoundBandAvailability() {
		return djSoundBandAvailability;
	}

	public void setDjSoundBandAvailability(boolean djSoundBandAvailability) {
		this.djSoundBandAvailability = djSoundBandAvailability;
	}

	public float getMinDjSoundBandCharges() {
		return minDjSoundBandCharges;
	}

	public void setMinDjSoundBandCharges(float minDjSoundBandCharges) {
		this.minDjSoundBandCharges = minDjSoundBandCharges;
	}

	public boolean isBhaggiAvailability() {
		return bhaggiAvailability;
	}

	public void setBhaggiAvailability(boolean bhaggiAvailability) {
		this.bhaggiAvailability = bhaggiAvailability;
	}

	public float getMinBhaggiCharges() {
		return minBhaggiCharges;
	}

	public void setMinBhaggiCharges(float minBhaggiCharges) {
		this.minBhaggiCharges = minBhaggiCharges;
	}

	public boolean isGhodiAvailability() {
		return ghodiAvailability;
	}

	public void setGhodiAvailability(boolean ghodiAvailability) {
		this.ghodiAvailability = ghodiAvailability;
	}

	public float getMinGhodiCharges() {
		return minGhodiCharges;
	}

	public void setMinGhodiCharges(float minGhodiCharges) {
		this.minGhodiCharges = minGhodiCharges;
	}

	public boolean isRathAvailability() {
		return rathAvailability;
	}

	public void setRathAvailability(boolean rathAvailability) {
		this.rathAvailability = rathAvailability;
	}

	public float getMinRathCharges() {
		return minRathCharges;
	}

	public void setMinRathCharges(float minRathCharges) {
		this.minRathCharges = minRathCharges;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
