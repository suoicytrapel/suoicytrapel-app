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
	
	@Column(name = "simple_band_availability")
	private Boolean simpleBandAvailability;
	
	@Column(name = "min_simple_band_charges")
	private Float minSimpleBandCharges;
	
	@Column(name = "crew_band_availability")
	private Boolean crewBandAvailability;
	
	@Column(name = "min_crew_band_charges")
	private Float minCrewBandCharges;
	
	@Column(name = "military_band_availability")
	private Boolean militaryBandAvailability;
	
	@Column(name = "min_military_band_charges")
	private Float minMilitaryBandCharges;
	
	@Column(name = "dj_sound_band_availability")
	private Boolean djSoundBandAvailability;
	
	@Column(name = "min_dj_sound_band_charges")
	private Float minDjSoundBandCharges;
	
	@Column(name = "bhaggi_availability")
	private Boolean bhaggiAvailability;
	
	@Column(name = "min_bhaggi_charges")
	private Float minBhaggiCharges;
	
	@Column(name = "ghodi_availability")
	private Boolean ghodiAvailability;
	
	@Column(name = "min_ghodi_charges")
	private Float minGhodiCharges;
	
	@Column(name = "rath_availability")
	private Boolean rathAvailability;
	
	@Column(name = "min_rath_charges")
	private Float minRathCharges;

	public long getBandId() {
		return bandId;
	}

	public void setBandId(long bandId) {
		this.bandId = bandId;
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

	public Boolean getSimpleBandAvailability() {
		return simpleBandAvailability;
	}

	public void setSimpleBandAvailability(Boolean simpleBandAvailability) {
		this.simpleBandAvailability = simpleBandAvailability;
	}

	public Float getMinSimpleBandCharges() {
		return minSimpleBandCharges;
	}

	public void setMinSimpleBandCharges(Float minSimpleBandCharges) {
		this.minSimpleBandCharges = minSimpleBandCharges;
	}

	public Boolean getCrewBandAvailability() {
		return crewBandAvailability;
	}

	public void setCrewBandAvailability(Boolean crewBandAvailability) {
		this.crewBandAvailability = crewBandAvailability;
	}

	public Float getMinCrewBandCharges() {
		return minCrewBandCharges;
	}

	public void setMinCrewBandCharges(Float minCrewBandCharges) {
		this.minCrewBandCharges = minCrewBandCharges;
	}

	public Boolean getMilitaryBandAvailability() {
		return militaryBandAvailability;
	}

	public void setMilitaryBandAvailability(Boolean militaryBandAvailability) {
		this.militaryBandAvailability = militaryBandAvailability;
	}

	public Float getMinMilitaryBandCharges() {
		return minMilitaryBandCharges;
	}

	public void setMinMilitaryBandCharges(Float minMilitaryBandCharges) {
		this.minMilitaryBandCharges = minMilitaryBandCharges;
	}

	public Boolean getDjSoundBandAvailability() {
		return djSoundBandAvailability;
	}

	public void setDjSoundBandAvailability(Boolean djSoundBandAvailability) {
		this.djSoundBandAvailability = djSoundBandAvailability;
	}

	public Float getMinDjSoundBandCharges() {
		return minDjSoundBandCharges;
	}

	public void setMinDjSoundBandCharges(Float minDjSoundBandCharges) {
		this.minDjSoundBandCharges = minDjSoundBandCharges;
	}

	public Boolean getBhaggiAvailability() {
		return bhaggiAvailability;
	}

	public void setBhaggiAvailability(Boolean bhaggiAvailability) {
		this.bhaggiAvailability = bhaggiAvailability;
	}

	public Float getMinBhaggiCharges() {
		return minBhaggiCharges;
	}

	public void setMinBhaggiCharges(Float minBhaggiCharges) {
		this.minBhaggiCharges = minBhaggiCharges;
	}

	public Boolean getGhodiAvailability() {
		return ghodiAvailability;
	}

	public void setGhodiAvailability(Boolean ghodiAvailability) {
		this.ghodiAvailability = ghodiAvailability;
	}

	public Float getMinGhodiCharges() {
		return minGhodiCharges;
	}

	public void setMinGhodiCharges(Float minGhodiCharges) {
		this.minGhodiCharges = minGhodiCharges;
	}

	public Boolean getRathAvailability() {
		return rathAvailability;
	}

	public void setRathAvailability(Boolean rathAvailability) {
		this.rathAvailability = rathAvailability;
	}

	public Float getMinRathCharges() {
		return minRathCharges;
	}

	public void setMinRathCharges(Float minRathCharges) {
		this.minRathCharges = minRathCharges;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}
}
